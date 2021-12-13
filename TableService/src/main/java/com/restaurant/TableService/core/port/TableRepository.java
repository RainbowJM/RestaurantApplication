package com.restaurant.TableService.core.port;

import com.restaurant.TableService.core.domain.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends MongoRepository<Table, Long> {
    List<Table> findAll();
    Optional<Table> findById(Long id);
    boolean existsById(Long id);

    Optional<Table> deleteTableById(Long id);
}
