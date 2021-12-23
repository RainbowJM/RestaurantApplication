package com.restaurant.TableService.adapter.ingoing.rest;

import com.restaurant.TableService.adapter.ingoing.rest.requestDTO.CreateTableRequest;
import com.restaurant.TableService.adapter.ingoing.rest.requestDTO.ModifyTableRequest;
import com.restaurant.TableService.core.application.TableCommandService;
import com.restaurant.TableService.core.application.TableQueryService;
import com.restaurant.TableService.core.application.command.AddTableCommand;
import com.restaurant.TableService.core.application.command.DeleteTableCommand;
import com.restaurant.TableService.core.application.command.ModifyTableCommand;
import com.restaurant.TableService.core.application.query.ListRestaurantTablesQuery;
import com.restaurant.TableService.core.application.query.ListTablesQuery;
import com.restaurant.TableService.core.domain.Table;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public List<Table> getTables(@RequestParam(required = false) String userId) {
        return this.tableQueryService.handle(new ListTablesQuery(userId));
    }
    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public List<Table> getTablesFromRestaurant(@PathVariable String restaurantId){
        return this.tableQueryService.handle(new ListRestaurantTablesQuery(restaurantId));
    }

    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"Staff", "Management", "OtherService"})
    public Table addTable(@Valid @RequestBody CreateTableRequest tableRequest) {
        return this.tableCommandService.handle(new AddTableCommand(tableRequest.restaurantName,
                tableRequest.location, tableRequest.numberOfSeats));
    }

    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RolesAllowed({"Staff", "Management", "OtherService"})
    public Table modifyTable(@Valid @RequestBody ModifyTableRequest modifyTableRequest){
        return this.tableCommandService.handle(new ModifyTableCommand(modifyTableRequest.tableId,
                modifyTableRequest.restaurantId, modifyTableRequest.location, modifyTableRequest.numberOfSeats));
    }

    @DeleteMapping(path="/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"Staff", "Management", "OtherService"})
    public void deleteTable(@PathVariable String id) {
        this.tableCommandService.handle(new DeleteTableCommand(id));
    }
}
