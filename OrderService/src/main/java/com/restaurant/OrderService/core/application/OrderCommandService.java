package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.adapters.incoming.message.RestaurantEventListener;
import com.restaurant.OrderService.adapters.incoming.message.UserEventListener;
import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.application.command.UpdateOrderStatus;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import com.restaurant.OrderService.core.application.command.CreateOrderCommand;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.domain.*;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownRestaurantName;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownUsername;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderCommandService {
    private final OrderRepository repository;

    public OrderCommandService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order handle(CreateOrderCommand orderCommand) {
        // todo: is online- or tableOrder?
        Order order;
        if (!UserEventListener.userExists(orderCommand.customerId())) throw new OrderWithUnknownUsername();
        if (!RestaurantEventListener.restaurantExists(orderCommand.restaurantId())) throw new OrderWithUnknownRestaurantName();

        List<OrderLine> orderLines = new ArrayList<>();
        for(CreateOrderLineRequest lines :  orderCommand.lines()){
            orderLines.add(new OrderLine(lines.getProductId(), lines.getAmount(), lines.getPrice()));
        }

        if(orderCommand.orderType() == OrderType.ONLINE) order = new OnlineOrder(orderCommand.customerId(), orderCommand.restaurantId(), orderLines, orderCommand.orderdate(), orderCommand.status(), orderCommand.location());
        else if(orderCommand.orderType() == OrderType.TABLE) order = new TableOrder();
        else throw new OrderNotFound("none");
        return this.repository.save(order);
    }

    public Order handleCreateOnlineOrder(CreateOrderCommand orderCommand){
        List<OrderLine> orderLines = new ArrayList<>();
        for(CreateOrderLineRequest lines :  orderCommand.lines()){
            orderLines.add(new OrderLine(lines.getProductId(), lines.getAmount(), lines.getPrice()));
        }
        Order order = new OnlineOrder(orderCommand.customerId(), orderCommand.restaurantId(), orderLines, orderCommand.orderdate(), orderCommand.status(), orderCommand.location());
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
}