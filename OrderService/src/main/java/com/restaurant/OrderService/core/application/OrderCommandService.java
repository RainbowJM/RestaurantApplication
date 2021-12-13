package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.core.application.command.CancelOrderCommand;
import com.restaurant.OrderService.core.application.command.ChangeOrderCommand;
import com.restaurant.OrderService.core.application.command.CreateOrderCommand;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderCommandService {
    private final OrderRepository repository;

    public OrderCommandService(OrderRepository repository) { this.repository = repository; }

    public Order handle(CreateOrderCommand orderCommand) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(orderCommand.orderdate());
        Order order = new Order(orderCommand.customerId(), date, orderCommand.status(), orderCommand.deliverAddress(), orderCommand.totalPrice());
        return this.repository.save(order);
    }

    public Order handle(ChangeOrderCommand orderCommand){
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        Order order = optOrder.get();
        order.changeOrder(orderCommand);
        return order;
    }

    public void handle(CancelOrderCommand orderCommand){
        Optional<Order> optOrder = this.repository.findById(orderCommand.orderId());
        if (optOrder.isEmpty())
            throw new OrderNotFound(orderCommand.orderId());

        Order order = optOrder.get();
        order.setStatus("Cancelled");
        this.repository.save(order);
    }
}