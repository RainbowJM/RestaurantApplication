package com.restaurant.OrderService.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class OrderLine {
    @Id
    String orderLineId;
    String orderId;
    String productId;
    int amount;
    float price;
}
