package com.restaurant.TableService.core.domain.event;

public class TableReadyEvent extends TableEvent {
    public static final String KEY = "table.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}
