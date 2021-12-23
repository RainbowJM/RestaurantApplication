package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.OrderLine;
import com.restaurant.OrderService.core.domain.OrderStatus;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
public class CreateOrderRequest {
    @NotBlank(message = "customerId can't be empty")
    String customerId;
    @NotBlank(message = "restaurantId can't be empty")
    String restaurantId;
    @NotBlank(message = "OrderLines can't be empty")
    List<CreateOrderLineRequest> orderLines;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    Date orderDate;
    OrderStatus status;
    @NotBlank(message = "Delivery address can't be empty.")
    String deliverAddress;

    public CreateOrderRequest(String customerId, String restaurantId, List<CreateOrderLineRequest> lines, String orderDate, String status, String deliverAddress){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLines = lines;
        this.deliverAddress = deliverAddress;
        if(status == null) this.status = OrderStatus.CREATED;
        else this.status = OrderStatus.valueOf(status);

        try {
            this.orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
