package com.restaurant.TableService.adapter.rest;

import com.restaurant.TableService.core.application.TableCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/table")
public class TableRestController {
    public final TableCommandService tableCommandService;

    public TableRestController(TableCommandService tableCommandService) {
        this.tableCommandService = tableCommandService;
    }

//    @GetMapping
//    public String placeholder() {
//        // Everything should be done in the application domain. Adapters shouldn't have any dependencies on the domain package.
//        return tableCommandService.placeholder();
//    }
}
