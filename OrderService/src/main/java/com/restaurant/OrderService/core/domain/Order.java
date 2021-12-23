package com.restaurant.OrderService.core.domain;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import com.restaurant.OrderService.core.domain.external.Restaurant;
import com.restaurant.OrderService.core.domain.external.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public abstract class Order {
    @Id
    @Getter
    String id;

    @Getter @Setter
    String restaurantId; // can refer to a non-existing restaurant if a restaurant has been deleted since the order was made
    @Getter @Setter
    String customerId;
    @Getter
    OrderType orderType;

    @Getter
    List<OrderLine> orderLines;
    @Getter @Setter
    Date orderDate;
    @Getter @Setter
    OrderStatus status;
    @Getter @Setter
    String location;
    @Getter
    float totalPrice;

    public Order(String customerId, String restaurantId, OrderType orderType, List<OrderLine> lines,  Date orderDate, OrderStatus status, String location){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLines = lines;
        this.orderType = orderType;
        this.orderDate = orderDate;
        this.status = status;
        this.location = location;
        if (!lines.isEmpty()) {
            for(OrderLine line : lines){
                this.totalPrice += line.getPrice();
            }
        }
    }

    public void setStatus(String strStatus){
        this.status = OrderStatus.valueOf(strStatus);
    }

    public void changeOrderLines(List<CreateOrderLineRequest> lines){
        System.out.println("Setting orderlines...");
        this.orderLines = new ArrayList<>();
        for(CreateOrderLineRequest lineRequest: lines){
            System.out.println(lineRequest);
            this.orderLines.add(new OrderLine(lineRequest.getProductId(), lineRequest.getAmount(), lineRequest.getPrice()));
        }
        if (!orderLines.isEmpty()) {
            for(OrderLine line : orderLines){
                this.totalPrice += line.getPrice();
            }
        }
    }
}