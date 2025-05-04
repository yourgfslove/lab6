package com.first.restorders.dto;

import com.first.restorders.model.OrderLineItems;

public class OrderLineItemDTO {
    private Long id;
    private double price;
    private String produceName;
    private int quantity;

    // Конструкторы, геттеры, сеттеры

    public OrderLineItemDTO(Long id, double price, String produceName, int quantity) {
        this.id = id;
        this.price = price;
        this.produceName = produceName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
