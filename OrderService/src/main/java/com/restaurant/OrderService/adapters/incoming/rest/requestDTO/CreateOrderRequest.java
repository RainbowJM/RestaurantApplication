package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.OrderLine;
import com.restaurant.OrderService.core.domain.OrderStatus;
import com.restaurant.OrderService.core.domain.OrderType;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import lombok.Getter;
import org.springframework.http.converter.HttpMessageNotReadableException;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.SchemaOutputResolver;
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
//    @NotBlank(message = "OrderLines can't be empty")
    OrderType orderType;
    List<CreateOrderLineRequest> orderLines;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    Date orderDate;
    OrderStatus status;
    @NotBlank(message = "Location can't be empty.")
    String location;

    public CreateOrderRequest(String customerId, String restaurantId, String orderType, List<CreateOrderLineRequest> lines, String orderDate, String location){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.location = location;
        this.status = OrderStatus.CREATED;
        System.out.println(lines);
        this.orderLines = lines;
        System.out.println(orderType);

        try {
            this.orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
            this.orderType = OrderType.valueOf(orderType.toUpperCase());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            throw new OrderNotFound("Cannot find orderType");
        }
    }
}
