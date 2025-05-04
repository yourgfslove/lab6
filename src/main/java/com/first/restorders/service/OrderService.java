package com.first.restorders.service;

import com.first.restorders.model.Order;
import com.first.restorders.model.OrderLineItems;
import com.first.restorders.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        for (OrderLineItems item : order.getOrderLineItemsList()) {
            item.setOrder(order);  // Устанавливаем связь с заказом
        }

        // Сохраняем заказ вместе с его строками
        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder).getBody();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
