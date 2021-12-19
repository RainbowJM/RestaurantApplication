package com.restaurant.StorageService.core.application;

import com.restaurant.StorageService.core.port.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StorageQueryService {
    private final StorageRepository storageRepository;

    public StorageQueryService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }
}
