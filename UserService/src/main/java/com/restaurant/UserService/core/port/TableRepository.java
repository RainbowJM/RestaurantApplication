package com.restaurant.UserService.core.port;

import com.restaurant.UserService.adapter.outgoing.rest.TableResult;

import java.util.List;

public interface TableRepository {
    List<TableResult> getAllTablesFromUser(String id);
}
