package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.adapters.incoming.message.UserEventListener;
import com.restaurant.OrderService.adapters.incoming.message.event.UserEvent;
import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.application.command.CancelOrderCommand;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import com.restaurant.OrderService.core.application.command.CreateOrderCommand;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.OrderLine;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownUsername;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
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
        if (!UserEventListener.userExists(orderCommand.customerId())) {
            throw new OrderWithUnknownUsername();
        }

        List<OrderLine> orderLines = new ArrayList<>();
        for(CreateOrderLineRequest lines :  orderCommand.lines()){
            orderLines.add(new OrderLine(lines.getProductId(), lines.getAmount(), lines.getPrice()));
        }
        Order order = new Order(orderCommand.customerId(), orderCommand.restaurantId(), orderLines, orderCommand.orderdate(), orderCommand.status(), orderCommand.deliverAddress());
        return this.repository.save(order);
    }

    public Order handle(ChangeOrderCommand orderCommand){
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        Order order = optOrder.get();
        order.changeOrder(orderCommand);
        this.repository.save(order);
        System.out.println(order.getOrderLines());
        return order;
    }

    public Order handle(CancelOrderCommand orderCommand){
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        Order order = optOrder.get();
        order.setStatus("Cancelled");
        return this.repository.save(order);
    }

    public void handle(DeleteOrderCommand orderCommand) {
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        this.repository.deleteOrderById(orderCommand.orderId());
    }
}