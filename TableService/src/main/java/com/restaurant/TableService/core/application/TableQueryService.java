package com.restaurant.TableService.core.application;

import com.restaurant.TableService.core.application.query.ListRestaurantTablesQuery;
import com.restaurant.TableService.core.application.query.ListTablesQuery;
import com.restaurant.TableService.core.application.query.TableQuery;
import com.restaurant.TableService.core.domain.Table;
import com.restaurant.TableService.core.port.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TableQueryService {
    private final TableRepository tableRepository;

    public TableQueryService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Table handle(TableQuery tableQuery) {
        Optional<Table> optionalTable = this.tableRepository.findById(tableQuery.tableId());

        if (optionalTable.isEmpty()) {
            return null;
        } else {
            return optionalTable.get();
        }
    }

    public List<Table> handle(ListTablesQuery listTablesQuery) {
        if (listTablesQuery.optionalOrderId() == null)
            return this.tableRepository.findAll();
        else
            return this.tableRepository.findTablesByOrder(listTablesQuery.optionalOrderId());
    }

    public List<Table> handle(ListRestaurantTablesQuery listRestaurantTablesQuery){
        List<Table> tables = this.tableRepository.findByRestaurantId(listRestaurantTablesQuery.restaurantId());

        if (tables.isEmpty()) {
            return null;
        } else {
            return tables;
        }
    }
}
