package com.restaurant.TableService.adapter.rest;

import com.restaurant.TableService.adapter.rest.requestDTO.CreateTableRequest;
import com.restaurant.TableService.core.application.TableCommandService;
import com.restaurant.TableService.core.application.TableQueryService;
import com.restaurant.TableService.core.application.command.AddTableCommand;
import com.restaurant.TableService.core.application.command.DeleteTableCommand;
import com.restaurant.TableService.core.application.query.ListTablesQuery;
import com.restaurant.TableService.core.domain.Table;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/table")
public class TableRestController {
    public final TableQueryService tableQueryService;
    public final TableCommandService tableCommandService;

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public List<Table> getTables(@RequestParam(required = false) String userId) {
        return this.tableQueryService.handle(new ListTablesQuery(userId));
    }

    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.CREATED)
    public Table addTable(@Valid @RequestBody CreateTableRequest tableRequest) {
        return this.tableCommandService.handle(new AddTableCommand(tableRequest.restaurantName, tableRequest.location, tableRequest.numberOfSeats));
    }

    @DeleteMapping(path="/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable String id) {
        this.tableCommandService.handle(new DeleteTableCommand(id));
    }
}
