package com.restaurant.TableService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TableAddedEvent extends TableEvent {
    public static final String KEY = "table.event.added";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String tableId;

    @Getter
    private final String restaurantId;

    @Getter
    private final Long numberOfSeats;

    @Getter
    private final String location;

}
