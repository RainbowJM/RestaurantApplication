package com.restaurant.StorageService.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="Storage")
public class Storage {
    @Id
    @Getter @Setter
    private String name; // always the same as the restaurantName

    @Getter @Setter
    private List<Item> items;
}
