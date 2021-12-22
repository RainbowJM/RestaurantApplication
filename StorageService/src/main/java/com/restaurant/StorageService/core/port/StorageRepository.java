package com.restaurant.StorageService.core.port;

import com.restaurant.StorageService.core.domain.Storage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StorageRepository extends MongoRepository<Storage, String> {
}
