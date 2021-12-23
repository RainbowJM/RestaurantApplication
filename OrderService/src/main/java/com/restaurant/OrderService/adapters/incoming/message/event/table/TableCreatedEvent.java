package com.restaurant.OrderService.adapters.incoming.message.event.table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.restaurant.OrderService.adapters.incoming.message.event.RestaurantCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(RestaurantCreatedEvent.KEY)
public class TableCreatedEvent extends TableEvent{
    public static final String KEY = "table.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    public final String name;
}
