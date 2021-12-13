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
    Long orderId;
    Long customerId;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;

    public Order(Long customerId, Date orderDate, String status, String deliverAddress, float totalPrice){
        this.customerId = customerId;
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
