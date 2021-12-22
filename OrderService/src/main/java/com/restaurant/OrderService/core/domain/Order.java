package com.restaurant.OrderService.core.domain;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    @Id
    String id;
    String restaurantId;
    String customerId;
    List<OrderLine> orderLines;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;

    public Order(String customerId, String restaurantId, List<OrderLine> lines, Date orderDate, String status, String deliverAddress){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLines = lines;
        this.orderDate = orderDate;
        this.status = status;
        this.deliverAddress = deliverAddress;
        if(!lines.isEmpty()){
            for(OrderLine line : lines){
                this.totalPrice += line.getPrice();
            }
        }
    }

    public Order changeOrder(ChangeOrderCommand orderCommand){
        this.customerId = orderCommand.customerId();
        this.restaurantId = orderCommand.restaurantId();

        this.orderDate = orderCommand.orderDate();
        this.status = orderCommand.status();
        this.deliverAddress = orderCommand.deliverAddress();

        this.orderLines = new ArrayList<>();
        for(CreateOrderLineRequest lineRequest: orderCommand.lines()){
            this.orderLines.add(new OrderLine(lineRequest.getProductId(), lineRequest.getAmount(), lineRequest.getPrice()));
        }
        return this;
    }
}
