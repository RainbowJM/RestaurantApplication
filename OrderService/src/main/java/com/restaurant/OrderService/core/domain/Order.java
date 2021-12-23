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
@Document(collection = "Order")
public class Order {
    @Id
    @Getter
    String id;

    @Getter @Setter
    String restaurantId; // can refer to a non-existing restaurant if a restaurant has been deleted since the order was made
    @Getter @Setter
    String customerId;

    @Getter
    List<OrderLine> orderLines;
    @Getter @Setter
    Date orderDate;
    @Getter @Setter
    String status;
    @Getter @Setter
    String deliverAddress;
    @Getter @Setter
    float totalPrice;

    public Order(String customerId, String restaurantId, List<OrderLine> lines, Date orderDate, String status, String deliverAddress){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLines = lines;
        this.orderDate = orderDate;
        this.status = status;
        this.deliverAddress = deliverAddress;
        if (!lines.isEmpty()) {
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
