package com.restaurant.OrderService.adapters.incoming.message.event.table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(TableRemovedEvent.KEY)
public class TableRemovedEvent extends TableEvent{
    public static final String KEY = "table.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    public final String tableId;

}
