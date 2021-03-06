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

    // region Get
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public List<Order> getOrder(@RequestParam(required = false) String userId, @RequestParam(required = false) String restaurantId) {
        // fixme: check if owner stuff
        return this.queryService.handle(new ListOrdersQuery(userId, restaurantId));
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order getOrdersFromRestaurant(@PathVariable String orderId) {
        return this.queryService.handle(new OrderQuery(orderId));
    }
    // endregion

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return this.commandService.handle(new CreateOrderCommand(createOrderRequest.getCustomerId(), createOrderRequest.getRestaurantId(), createOrderRequest.getOrderType(), createOrderRequest.getOrderLines(), createOrderRequest.getOrderDate(), createOrderRequest.getStatus(), createOrderRequest.getLocation()));
    }

    // region Put
    @PutMapping("/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order changeOrder(@Valid @RequestBody ChangeOrderRequest changeOrderRequest){
        return this.commandService.handle( new ChangeOrderCommand( changeOrderRequest.getOrderId(), changeOrderRequest.getRestaurantId(), changeOrderRequest.getCustomerId(), changeOrderRequest.getLines(), changeOrderRequest.getStatus(), changeOrderRequest.getDeliverAddress()));
    }

    @PutMapping("/{orderId}/status/{newStatus}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public Order changeStatus(Authentication authentication, @PathVariable String orderId, @PathVariable String newStatus){
        if (authentication.getAuthorities().stream().findFirst().get().getAuthority().equals("User")) {
            // fixme: check if it's the same user who owns the order, otherwise throw an exception
        }
        return this.commandService.handle(new UpdateOrderStatus(orderId, newStatus));
    }
    // endregion

    @DeleteMapping("/{orderId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"Management", "OtherService"})
    public void deleteOrder(@PathVariable String orderId) {
        this.commandService.handle(new DeleteOrderCommand(orderId));
    }

    // region Others
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Map<String, String>> handleOldTokenException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint or your login token has expired."));
    }

    @ExceptionHandler({OrderWithUnknownRestaurantName.class, OrderWithUnknownRestaurantName.class})
    public ResponseEntity<Map<String, String>> handleBadParametersException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", exception.getMessage()));
    }
    // endregion
}