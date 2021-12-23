package com.restaurant.OrderService.adapters.incoming.message;

import com.restaurant.OrderService.adapters.incoming.message.event.table.TableCreatedEvent;
import com.restaurant.OrderService.adapters.incoming.message.event.table.TableEvent;
import com.restaurant.OrderService.adapters.incoming.message.event.table.TableRemovedEvent;
import com.restaurant.OrderService.core.application.OrderCommandService;
import com.restaurant.OrderService.core.application.OrderQueryService;
import com.restaurant.OrderService.core.domain.external.Table;
import com.restaurant.OrderService.core.port.TableRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

public class TableEventListener {
    private final OrderQueryService queryService;
    private final OrderCommandService commandService;
    private final TableRepository tableRepository;

    private static List<Table> tables = new ArrayList<>();
    private static boolean initialized = false;

    public TableEventListener(OrderQueryService queryService, OrderCommandService commandService, TableRepository tableRepository){
        this.queryService = queryService;
        this.commandService = commandService;
        this.tableRepository = tableRepository;
    }

    @EventListener
    private void onReadyEvent(ContextRefreshedEvent event) { initializeTables(); }

    private void initializeTables(){
        if(!initialized){
            try{
                tables = new ArrayList<>(tableRepository.getAllTables());
            } catch (ResourceAccessException exception) {
                System.out.println("Failed to connect to Table because it probably hasn't started yet. UserReadyEvent will need to be used to initialize this server.");
                return;
            }
            initialized = true;
        }
    }

    @RabbitListener(queues = "#{'${message.queue.restaurant-event}'}")
    private void listen(TableEvent event){
        switch (event.getEventKey()) {
//            case RestaurantReadyEvent.KEY -> this.initializeRestaurants();
            case TableCreatedEvent.KEY -> this.handle((TableCreatedEvent)event);
            case TableRemovedEvent.KEY -> this.handle((TableRemovedEvent)event);
        }
    }

    private void handle(TableCreatedEvent event){
        tables.add(new Table(event.getName()));
    }

    private void handle(TableRemovedEvent event){
        tables.removeIf(table -> table.getName().equals(event.getName()));
    }

    public static List<Table> getTables(){
        return tables;
    }

    public static boolean tableExists(String nameId){
        return tables.stream().anyMatch(table -> table.getName().equals(nameId));
    }
}
