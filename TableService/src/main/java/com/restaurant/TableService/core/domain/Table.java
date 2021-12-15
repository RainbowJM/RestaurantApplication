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

    @Getter
    private String restaurantId;

    @Getter
    @Setter
    private Long numberOfSeats;

    @Getter
    @Setter
    private String location;

    @Getter
    @Setter
    private String user;

    public Table() {
    }

    public Table(String restaurantId, String location, Long numberOfSeats) {
        this.restaurantId = restaurantId;
        this.location = location;
        this.numberOfSeats = numberOfSeats;
    }
}
