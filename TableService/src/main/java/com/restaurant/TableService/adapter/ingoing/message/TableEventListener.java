package com.restaurant.TableService.adapter.ingoing.message;

import com.restaurant.TableService.core.application.TableCommandService;
import org.springframework.stereotype.Component;

@Component
public class TableEventListener {
    private final TableCommandService  tableCommandService;


    public TableEventListener(TableCommandService tableCommandService) {
        this.tableCommandService = tableCommandService;
    }
}
