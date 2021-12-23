package com.restaurant.OrderService.core.port;

import com.restaurant.OrderService.core.domain.external.Table;

import java.util.List;

public interface TableRepository {
    List<Table> getAllTables();
}
