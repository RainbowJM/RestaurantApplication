package com.restaurant.TableService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TableRemovedEvent extends TableEvent{
    public static final String KEY = "table.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String tableId;
}
