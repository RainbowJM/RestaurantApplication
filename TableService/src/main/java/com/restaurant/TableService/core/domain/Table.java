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
    private int id;

    @Getter
    @Setter
    private int numberOfSeats;

    public Table() {
    }

    public Table(int id, int numberOfSeats) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
    }
}
