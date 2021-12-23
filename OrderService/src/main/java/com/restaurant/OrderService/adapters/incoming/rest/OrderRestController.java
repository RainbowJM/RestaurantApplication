package com.restaurant.OrderService.adapters.incoming.rest;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.*;
import com.restaurant.OrderService.core.application.*;
import com.restaurant.OrderService.core.application.command.*;
import com.restaurant.OrderService.core.application.query.*;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownRestaurantName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        // fixme: check if owner stuff
        return this.queryService.handle(new OrderQuery(orderId));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) throws ParseException {
        return this.commandService.handle(new CreateOrderCommand(createOrderRequest.getCustomerId(), createOrderRequest.getRestaurantId(), createOrderRequest.getOrderLines(), createOrderRequest.getOrderDate(), createOrderRequest.getStatus(), createOrderRequest.getDeliverAddress(), createOrderRequest.getTotalPrice()));
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order changeOrder(@Valid @RequestBody ChangeOrderRequest changeOrderRequest){
        return this.commandService.handle( new ChangeOrderCommand( changeOrderRequest.getOrderId(), changeOrderRequest.getRestaurantId(), changeOrderRequest.getCustomerId(), changeOrderRequest.getLines(), changeOrderRequest.getOrderDate(), changeOrderRequest.getStatus(), changeOrderRequest.getDeliverAddress(), changeOrderRequest.getTotalPrice()));
    }

    @PutMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order cancelOrder(Authentication auth, @PathVariable String id) {
        if (auth.getAuthorities().stream().findFirst().get().getAuthority().equals("User")) {
            // fixme: check if it's the same user who owns the order, otherwise throw an exception
        }
        return this.commandService.handle(new CancelOrderCommand(id));
    }

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"Management", "OtherService"})
    public void deleteOrder(@PathVariable String id) {
        this.commandService.handle(new DeleteOrderCommand(id));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Map<String, String>> handleOldTokenException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint or your login token has expired."));
    }

    @ExceptionHandler({OrderWithUnknownRestaurantName.class, OrderWithUnknownRestaurantName.class})
    public ResponseEntity<Map<String, String>> handleBadParametersException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", exception.getMessage()));
    }
}