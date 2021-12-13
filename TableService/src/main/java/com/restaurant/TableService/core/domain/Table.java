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
    private String id;

    @Id
    @Getter
    private String restaurantId;

    @Getter
    @Setter
    private Long numberOfSeats;

    public Table() {
    }

    public Table(String id, String restaurantId, Long numberOfSeats) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.numberOfSeats = numberOfSeats;
    }
}
