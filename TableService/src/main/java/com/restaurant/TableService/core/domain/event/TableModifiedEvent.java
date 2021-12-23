package com.restaurant.TableService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TableModifiedEvent extends TableEvent {
    public static final String KEY = "table.event.modified";

    @Override
    public String getEventKey() {
        return KEY;
    }
    @Getter
    private final String tableId;

    @Getter
    private final String restaurantId;

    @Getter
    private final String numberOfSeats;

    @Getter
    private final String location;

}
