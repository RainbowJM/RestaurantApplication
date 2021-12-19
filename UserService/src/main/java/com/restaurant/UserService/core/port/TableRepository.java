package com.restaurant.UserService.core.port;

import com.restaurant.UserService.adapter.outgoing.message.TableResult;

import java.util.List;

public interface TableRepository {
    List<TableResult> getAllTablesFromUser(String id);
}
