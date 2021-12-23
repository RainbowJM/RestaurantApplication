package com.restaurant.OrderService.adapters.incoming.message.event.table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.restaurant.OrderService.adapters.incoming.message.event.RestaurantReadyEvent;

@JsonTypeName(TableReadyEvent.KEY)
public class TableReadyEvent extends TableEvent{
    public static final String KEY = "table.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
