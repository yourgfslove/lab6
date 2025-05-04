package com.first.restorders.controller;

import com.first.restorders.dto.OrderDTO;
import com.first.restorders.dto.OrderLineItemDTO;
import com.first.restorders.model.Order;
import com.first.restorders.model.OrderLineItems;
import com.first.restorders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Создание нового заказа
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(convertToDTO(createdOrder), HttpStatus.CREATED);
    }

    // Получение всех заказов
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Получение заказа по ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> ResponseEntity.ok(convertToDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление заказа по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // Метод для преобразования сущности в DTO
    private OrderDTO convertToDTO(Order order) {
        List<OrderLineItemDTO> orderLineItemDTOList = new ArrayList<>();
        for (OrderLineItems lineItem : order.getOrderLineItemsList()) {
            OrderLineItemDTO lineItemDTO = new OrderLineItemDTO(
                    lineItem.getId(),
                    lineItem.getPrice(),
                    lineItem.getProduceName(),
                    lineItem.getQuantity()
            );
            orderLineItemDTOList.add(lineItemDTO);
        }
        return new OrderDTO(order.getId(), order.getOrderNumber(), orderLineItemDTOList);
    }
}