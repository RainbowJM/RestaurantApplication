package com.restaurant.TableService.core.application;

import com.restaurant.TableService.core.domain.Table;
import com.restaurant.TableService.core.port.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TableQueryService {
    private final TableRepository tableRepository;

    public TableQueryService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> GetAllTables(){
       return this.tableRepository.findAll();
    }
}
