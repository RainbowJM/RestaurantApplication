package com.restaurant.TableService.core.port;

import com.restaurant.TableService.core.domain.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends MongoRepository<Table, String> {
    List<Table> findAll();
    Optional<Table> findById(String id);
    boolean existsById(String id);

    Optional<Table> deleteTableById(String id);
}
