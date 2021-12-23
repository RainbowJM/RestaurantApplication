package com.restaurant.OrderService.adapters.incoming.message.event.menu;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@JsonTypeName(MenuCreatedEvent.KEY)
public class MenuCreatedEvent extends MenuEvent {
    public static final String KEY = "menu.event.created";

    @Override
    public String getEventKey() { return KEY; }

    @Getter
    private final String menuId;

    @Getter
    private final List<String> dishIds;

    @Getter
    private final String restaurantId;
}
