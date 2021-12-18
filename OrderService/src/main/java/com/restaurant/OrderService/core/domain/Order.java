package com.restaurant.OrderService.core.domain;

import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
public class Order {
    @Id
    String id;
    String restaurantId;
    String customerId;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;

    public Order(String customerId, String restaurantId, Date orderDate, String status, String deliverAddress, float totalPrice){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.status = status;
        this.deliverAddress = deliverAddress;
        this.totalPrice = totalPrice;
    }

    public Order changeOrder(ChangeOrderCommand orderCommand){
        this.customerId = orderCommand.customerId();
        this.orderDate = orderCommand.orderdate();
        this.status = orderCommand.status();
        this.deliverAddress = orderCommand.deliverAddress();
        this.totalPrice = orderCommand.totalPrice();
        return this;
    }
}
