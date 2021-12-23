package com.restaurant.OrderService.core.domain;

import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Document(collection = "OnlineOrder")
public class OnlineOrder extends Order {

    public OnlineOrder(String customerId, String restaurantId, List<OrderLine> lines, Date orderDate, OrderStatus status, String deliverAddress){
        super(customerId, restaurantId, lines, orderDate, status, deliverAddress);
    }
}
