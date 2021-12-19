package com.restaurant.OrderService.adapters.rest;

import com.restaurant.OrderService.adapters.rest.requestDTO.ChangeOrderRequest;
import com.restaurant.OrderService.adapters.rest.requestDTO.CreateOrderRequest;
import com.restaurant.OrderService.core.application.OrderCommandService;
import com.restaurant.OrderService.core.application.OrderQueryService;
import com.restaurant.OrderService.core.application.command.CancelOrderCommand;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import com.restaurant.OrderService.core.application.command.CreateOrderCommand;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.application.query.ListAllOrdersQuery;
import com.restaurant.OrderService.core.domain.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest){
        return this.commandService.handle(new CreateOrderCommand(createOrderRequest.getCustomerId(), createOrderRequest.getOrderDate(), createOrderRequest.getStatus(), createOrderRequest.getDeliverAddress(), createOrderRequest.getTotalPrice()));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order changeOrder(@Valid @RequestBody ChangeOrderRequest changeOrderRequest){
        return this.commandService.handle( new ChangeOrderCommand( changeOrderRequest.getOrderId(), changeOrderRequest.getCustomerId(), changeOrderRequest.getOrderDate(), changeOrderRequest.getStatus(), changeOrderRequest.getDeliverAddress(), changeOrderRequest.getTotalPrice()));
    }

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        this.commandService.handle(new DeleteOrderCommand(id));
    }

    @PostMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public Order cancelOrder(@PathVariable String id) {
        return this.commandService.handle(new CancelOrderCommand(id));
    }

}
