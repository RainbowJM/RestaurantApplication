package com.restaurant.UserService.adapter.outgoing.rest;

import java.util.Date;

public record OrderResult(String id, String customerId, Date orderDate, String status, String deliverAddress, float totalPrice) {
}
