package com.kaartik.tradingenginejava.service.orders;

import lombok.AllArgsConstructor;

public class Order implements IOrderCore {

    private final long price;
    private final boolean isBuySide;
    private final long initialQuantity;
    private int currentQuantity;
    private final IOrderCore orderCore;

    public Order(IOrderCore orderCore, long price, int quantity, boolean isBuySide) {
        this.price = price;
        this.isBuySide = isBuySide;
        this.initialQuantity = quantity;
        this.currentQuantity = quantity;
        this.orderCore = orderCore;
    }

    public Order(ModifyOrder modifyOrder) {
        this(modifyOrder, modifyOrder.getPrice(), modifyOrder.getQuantity(), modifyOrder.isBuySide());
    }

    public long getPrice() {
        return price;
    }

    public long getInitialQuantity() {
        return initialQuantity;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public boolean isBuySide() {
        return isBuySide;
    }

    public long getOrderId() {
        return orderCore.getOrderId();
    }

    public String getUsername() {
        return orderCore.getUsername();
    }

    public int getSecurityId() {
        return orderCore.getSecurityId();
    }

    public void increaseQuantity(long quantityDelta) {
        this.currentQuantity += quantityDelta;
    }

    public void decreaseQuantity(long quantityDelta) {
        if (quantityDelta > currentQuantity)
            throw new IllegalArgumentException("Quantity Delta > CurrentQuantity");
        this.currentQuantity -= quantityDelta;
    }
}