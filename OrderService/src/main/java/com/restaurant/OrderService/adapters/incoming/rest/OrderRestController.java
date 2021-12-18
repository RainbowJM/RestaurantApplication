package com.restaurant.OrderService.adapters.incoming.rest;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.*;
import com.restaurant.OrderService.core.application.*;
import com.restaurant.OrderService.core.application.command.*;
import com.restaurant.OrderService.core.application.query.*;
import com.restaurant.OrderService.core.domain.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    public final OrderQueryService queryService;
    public final OrderCommandService commandService;

    public OrderRestController(OrderQueryService queryService, OrderCommandService commandService){
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrder() {
        return this.queryService.handle(new ListAllOrdersQuery());
    }

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrdersFromRestaurant(@PathVariable String restaurantId ) {
        return this.queryService.handle(new ListRestaurantOrdersQuery(restaurantId));
    }

    @GetMapping("/one/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOneOrder(@PathVariable String orderId ) {
        return this.queryService.handle(new OrderQuery(orderId));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) throws ParseException {
        return this.commandService.handle(new CreateOrderCommand(createOrderRequest.getCustomerId(), createOrderRequest.getRestaurantId(), createOrderRequest.getOrderDate(), createOrderRequest.getStatus(), createOrderRequest.getDeliverAddress(), createOrderRequest.getTotalPrice()));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order changeOrder(@Valid @RequestBody ChangeOrderRequest changeOrderRequest){
        return this.commandService.handle( new ChangeOrderCommand( changeOrderRequest.getOrderId(), changeOrderRequest.getRestaurantId(), changeOrderRequest.getCustomerId(), changeOrderRequest.getOrderDate(), changeOrderRequest.getStatus(), changeOrderRequest.getDeliverAddress(), changeOrderRequest.getTotalPrice()));
    }

    @PutMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public Order cancelOrder(@PathVariable String id) {
        return this.commandService.handle(new CancelOrderCommand(id));
    }

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        this.commandService.handle(new DeleteOrderCommand(id));
    }
}