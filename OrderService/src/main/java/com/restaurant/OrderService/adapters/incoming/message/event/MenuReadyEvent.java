package com.restaurant.OrderService.adapters.incoming.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(MenuReadyEvent.KEY)
public class MenuReadyEvent extends MenuEvent {
    public static final String KEY = "menu.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
