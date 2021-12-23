package com.restaurant.OrderService.adapters.incoming.message;

import com.restaurant.OrderService.adapters.incoming.message.event.menu.*;
import com.restaurant.OrderService.core.domain.external.Menu;
import com.restaurant.OrderService.core.port.MenuRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuEventListener {
    private final MenuRepository menuRepository;

    private static List<Menu> menus = new ArrayList<>();
    public static boolean initialized = false;

    public MenuEventListener(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // Initialization is done at service initialization and when send a ready event from MenuService, so that order of startup doesn't matter
    private void initializeMenus() {
        if (!initialized) {
            try {
                menus = new ArrayList<>(menuRepository.getAllMenus());
            }
            catch (ResourceAccessException exception) {
                System.out.println("Failed to connect to UserService because it probably hasn't started yet. UserReadyEvent will need to be used to initialize this server.");
                return;
            }
            initialized = true;
        }
    }

    @EventListener
    public void onReadyEvent(ContextRefreshedEvent event) {
        initializeMenus();
    }

    @RabbitListener(queues = "#{'${message.queue.user-event}'}")
    private void listen(MenuEvent event) {
        switch (event.getEventKey()) {
            case MenuReadyEvent.KEY -> this.initializeMenus();
            case MenuChangedEvent.KEY -> this.handle((MenuChangedEvent) event);
            case MenuCreatedEvent.KEY -> this.handle((MenuCreatedEvent) event);
            case MenuRemovedEvent.KEY -> this.handle((MenuRemovedEvent) event);
        }
    }

    private void handle(MenuRemovedEvent event) {
        for (Menu menu : menus) {
            if (menu.getId().equals(event.getTableId())) {
                menus.remove(menu);
                break;
            }
        }
    }

    private void handle(MenuCreatedEvent event) {
        menus.add(new Menu(event.getMenuId(), event.getDishIds(), event.getRestaurantId()));
    }

    public void handle(MenuChangedEvent event) {
        for (Menu menu : menus) {
            if (menu.getId().equals(event.getMenuId())) {
                menu.setDishIds(event.getDishIds());
                menu.setRestaurantId(event.getRestaurantId());
                return;
            }
        }
    }

    public static List<Menu> getMenus() {
        return menus;
    }

    public static boolean menuExists(String id) {
        return menus.stream().anyMatch(menu -> menu.getId().equals(id));
    }
}
