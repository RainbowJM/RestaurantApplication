package com.restaurant.OrderService.adapters.incoming.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@JsonTypeName(MenuChangedEvent.KEY)
public class MenuChangedEvent extends MenuEvent {
    public static final String KEY = "menu.event.changed";

    @Override
    public String getEventKey() { return KEY; }

    @Getter
    private final String menuId;

    @Getter
    private final List<String> dishIds;

    @Getter
    private final String restaurantId;
}
