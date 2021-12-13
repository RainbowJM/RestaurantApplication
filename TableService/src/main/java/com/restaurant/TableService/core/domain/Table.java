package com.restaurant.TableService.core.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Table")
@ToString
@EqualsAndHashCode
public class Table {
    @Id
    @Getter
    private Long id;

    @Getter
    private Long restaurantId;

    @Getter
    @Setter
    private Long numberOfSeats;

    public Table() {
    }

    public Table(Long id, Long restaurantId, Long numberOfSeats) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.numberOfSeats = numberOfSeats;
    }
}
