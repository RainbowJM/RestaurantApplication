package com.restaurant.OrderService.adapters;

import com.restaurant.OrderService.data.OrderDto;
import com.restaurant.OrderService.application.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/")
public class OrderRestService {
    public final OrderService service;

    public OrderRestService(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public String placeholder() {
        // Everything should be done in the application domain. Adapters shouldn't have any dependencies on the domain package.
        return service.placeholder();
    }

    @GetMapping("{restaurantId}/")
    public List<OrderDto> getAllOrders(@PathVariable Long restaurantId){
        return service.getAllReservationsByRestaurant(restaurantId);
    }

    @GetMapping("order/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId){
        return service.getOneOrder(orderId);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        return service.createOrder(orderDto);
    }
}
