package com.first.restorders.dto;

import com.first.restorders.model.Order;
import com.first.restorders.model.OrderLineItems;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String orderNumber;
    private List<OrderLineItemDTO> orderLineItemsList;

    // Конструкторы, геттеры, сеттеры

    public OrderDTO(Long id, String orderNumber, List<OrderLineItemDTO> orderLineItemsList) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItemDTO> getOrderLineItemsList() {
        return orderLineItemsList;
    }

    public void setOrderLineItemsList(List<OrderLineItemDTO> orderLineItemsList) {
        this.orderLineItemsList = orderLineItemsList;
    }

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
