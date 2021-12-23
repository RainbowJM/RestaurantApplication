package com.restaurant.OrderService.adapters.incoming.message.event.menu;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(MenuRemovedEvent.KEY)
public class MenuRemovedEvent extends MenuEvent {
    public static final String KEY = "menu.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String tableId;
}
