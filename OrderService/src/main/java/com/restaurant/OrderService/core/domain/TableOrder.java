package com.restaurant.OrderService.core.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Document(collection = "TableOrder")
public class TableOrder extends Order{

    public TableOrder(String customerId, String restaurantId, OrderType orderType, List<OrderLine> lines, Date orderDate, OrderStatus status, String tableId){
        super(customerId, restaurantId, orderType, lines, orderDate, status, tableId);
    }
}


