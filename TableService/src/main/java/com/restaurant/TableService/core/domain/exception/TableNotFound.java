package com.restaurant.TableService.core.domain.exception;

public class TableNotFound extends RuntimeException{
    public TableNotFound(String id){
        super(String.format("Table not found %s", id));
    }
}
