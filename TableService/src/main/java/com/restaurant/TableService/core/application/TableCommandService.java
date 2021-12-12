package com.restaurant.TableService.core.application;

import com.restaurant.TableService.core.port.TableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TableCommandService {
    private final TableRepository tableRepository;

    public TableCommandService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }
}
