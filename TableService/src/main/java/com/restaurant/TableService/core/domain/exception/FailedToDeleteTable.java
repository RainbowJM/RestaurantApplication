package com.restaurant.TableService.core.domain.exception;

public class FailedToDeleteTable extends RuntimeException {
    public FailedToDeleteTable(String id) {
        super(String.format("Failed to delete table with id %s. The table might already be deleted or not exist!", id));
    }
}
