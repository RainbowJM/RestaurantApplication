package com.restaurant.TableService.core.application;

import com.restaurant.TableService.core.application.command.AddTableCommand;
import com.restaurant.TableService.core.application.command.DeleteTableCommand;
import com.restaurant.TableService.core.application.command.ModifyTableCommand;
import com.restaurant.TableService.core.domain.Table;
import com.restaurant.TableService.core.domain.exception.FailedToDeleteTable;
import com.restaurant.TableService.core.domain.exception.TableNotFound;
import com.restaurant.TableService.core.port.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TableCommandService {
    private final TableRepository tableRepository;

    public TableCommandService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Table handle(AddTableCommand addTableCommand) {
        // todo: check whether restaurant exists
        // todo: publish events when a table has been deleted
        return tableRepository.save(new Table(addTableCommand.restaurantId(), addTableCommand.location(), addTableCommand.numberOfSeats()));
    }

    public Table handle(ModifyTableCommand modifyTableCommand){
        Optional<Table> optTable = this.tableRepository.findById(modifyTableCommand.tableId());

        if (optTable.isEmpty()){
            throw new TableNotFound(modifyTableCommand.tableId());
        }

        Table table = optTable.get();
        table.changeTable(modifyTableCommand);
        return table;
    }
    public void handle(DeleteTableCommand deleteTableCommand) {
        if (this.tableRepository.deleteTableById(deleteTableCommand.id()).isEmpty()) {
            throw new FailedToDeleteTable(deleteTableCommand.id());
        }
    }
}
