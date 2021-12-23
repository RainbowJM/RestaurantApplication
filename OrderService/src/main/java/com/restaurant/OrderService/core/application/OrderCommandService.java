package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.adapters.incoming.message.RestaurantEventListener;
import com.restaurant.OrderService.adapters.incoming.message.UserEventListener;
import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.application.command.UpdateOrderStatus;
import com.restaurant.OrderService.adapters.outgoing.message.EventPublisher;
import com.restaurant.OrderService.core.application.command.CancelOrderCommand;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import com.restaurant.OrderService.core.application.command.CreateOrderCommand;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.domain.OnlineOrder;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.OrderLine;
import com.restaurant.OrderService.core.domain.OrderStatus;
import com.restaurant.OrderService.core.domain.event.OrderReadyEvent;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownRestaurantName;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownUsername;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderCommandService {
    private final OrderRepository repository;
    private final EventPublisher eventPublisher;

    public OrderCommandService(OrderRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Order handle(CreateOrderCommand orderCommand) {
        if (!UserEventListener.userExists(orderCommand.customerId())) {
            throw new OrderWithUnknownUsername();
        }

        if (!RestaurantEventListener.restaurantExists(orderCommand.restaurantId())) {
            throw new OrderWithUnknownRestaurantName();
        }

        List<OrderLine> orderLines = new ArrayList<>();
        for(CreateOrderLineRequest lines :  orderCommand.lines()){
            orderLines.add(new OrderLine(lines.getProductId(), lines.getAmount(), lines.getPrice()));
        }
        Order order = new OnlineOrder(orderCommand.customerId(), orderCommand.restaurantId(), orderLines, orderCommand.orderdate(), orderCommand.status(), orderCommand.deliverAddress());
        return this.repository.save(order);
    }

    public Order handle(ChangeOrderCommand orderCommand){
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());
        Order order = optOrder.get();
        System.out.println("changing order...");
        System.out.println(orderCommand.lines());

        if(orderCommand.customerId() != null) order.setCustomerId(orderCommand.customerId());
        if(orderCommand.restaurantId() != null) order.setRestaurantId(orderCommand.restaurantId());
        if(orderCommand.deliverAddress() != null) order.setDeliverAddress(orderCommand.deliverAddress());
        if(orderCommand.lines() != null && !orderCommand.lines().isEmpty()) order.changeOrderLines(orderCommand.lines());
        for(OrderLine orderLine: order.getOrderLines())
            System.out.println(orderLine);
        this.repository.save(order);
        return order;
    }

    public Order handle(UpdateOrderStatus orderCommand){
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        Order order = optOrder.get();
        order.setStatus(orderCommand.orderStatus());
        return this.repository.save(order);
    }

    public void handle(DeleteOrderCommand orderCommand) {
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        this.repository.deleteOrderById(orderCommand.orderId());
    }

    @EventListener
    public void sendOrderReadyEvent(ApplicationReadyEvent event) {
        eventPublisher.publish(new OrderReadyEvent());
    }
}