package com.restaurant.UserService.core.domain.external;

import java.util.Date;

public record Order(String id, String customerId, Date orderDate, String status, String deliverAddress, float totalPrice) {
}
