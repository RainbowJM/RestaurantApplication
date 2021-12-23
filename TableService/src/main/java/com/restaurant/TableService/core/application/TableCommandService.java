package com.restaurant.TableService.core.application;

import com.restaurant.TableService.adapter.outgoing.message.EventPublisher;
import com.restaurant.TableService.core.application.command.AddTableCommand;
import com.restaurant.TableService.core.application.command.DeleteTableCommand;
import com.restaurant.TableService.core.application.command.ModifyTableCommand;
import com.restaurant.TableService.core.domain.Table;
import com.restaurant.TableService.core.domain.event.TableAddedEvent;
import com.restaurant.TableService.core.domain.event.TableModifiedEvent;
import com.restaurant.TableService.core.domain.event.TableRemovedEvent;
import com.restaurant.TableService.core.domain.exception.FailedToDeleteTable;
import com.restaurant.TableService.core.domain.exception.RestaurantNotFound;
import com.restaurant.TableService.core.domain.exception.TableNotFound;
import com.restaurant.TableService.core.port.RestaurantRepository;
import com.restaurant.TableService.core.port.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TableCommandService {
    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final EventPublisher eventPublisher;

    public TableCommandService(TableRepository tableRepository, RestaurantRepository restaurantRepository, EventPublisher event) {
        this.tableRepository = tableRepository;
        this.restaurantRepository = restaurantRepository;
        this.eventPublisher = event;
    }

    public Table handle(AddTableCommand addTableCommand) {
        // todo: check whether restaurant exists
        if (this.restaurantRepository.getRestaurantFromTable(addTableCommand.restaurantId()).isEmpty()) {
            throw new RestaurantNotFound(addTableCommand.restaurantId());
        }

        // todo: publish events when a table has been deleted
        Table createdTable = this.tableRepository.save(new Table(addTableCommand.restaurantId(), addTableCommand.location(), addTableCommand.numberOfSeats()));

        if (createdTable != null) {
            this.eventPublisher.publish(
                    new TableAddedEvent(
                            createdTable.getId(),
                            addTableCommand.restaurantId(),
                            addTableCommand.numberOfSeats(),
                            addTableCommand.location()));
        }
        return createdTable;
    }

    public Table handle(ModifyTableCommand modifyTableCommand) {
        Table table = this.tableRepository.findTableById(modifyTableCommand.tableId());

        if (table == null) {
            throw new TableNotFound(modifyTableCommand.tableId());
        }

        if (modifyTableCommand.location() != null) {
            table.setLocation(modifyTableCommand.location());
        }
        if (modifyTableCommand.numberOfSeats() != null) {
            table.setNumberOfSeats(modifyTableCommand.numberOfSeats());
        }

        Table updateTable = this.tableRepository.save(table);

        eventPublisher.publish(new TableModifiedEvent(
                updateTable.getId(),
                updateTable.getRestaurantId(),
                updateTable.getNumberOfSeats(),
                updateTable.getLocation()));
        return updateTable;
    }

    public void handle(DeleteTableCommand deleteTableCommand) {
        Table deletedTable = this.tableRepository.deleteTableById(deleteTableCommand.id());
        if (deletedTable != null) {
            this.eventPublisher.publish(new TableRemovedEvent(deleteTableCommand.id()));
        }else{
            throw new FailedToDeleteTable(deleteTableCommand.id());
        }
    }
}
