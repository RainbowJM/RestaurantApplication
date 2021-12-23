package com.restaurant.UserService.core.port;

import com.restaurant.UserService.core.domain.external.Table;

import java.util.List;

public interface TableRepository {
    List<Table> getAllTablesFromUser(String id);
}
