package com.kaartik.tradingenginejava.service.orders;

public class ModifyOrder implements IOrderCore {

    private final long price;
    private final int quantity;
    private final boolean isBuySide;
    private final IOrderCore orderCore;

    public ModifyOrder(IOrderCore orderCore, long modifyPrice, int modifyQuantity, boolean isBuySide) {
        this.price = modifyPrice;
        this.quantity = modifyQuantity;
        this.isBuySide = isBuySide;
        this.orderCore = orderCore;
    }

    // Getters for the properties
    public long getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isBuySide() {
        return isBuySide;
    }

    // Implementing methods from the IOrderCore interface
    @Override
    public long getOrderId() {
        return orderCore.getOrderId();
    }

    @Override
    public String getUsername() {
        return orderCore.getUsername();
    }

    @Override
    public int getSecurityId() {
        return orderCore.getSecurityId();
    }

    // Methods to create CancelOrder and Order instances based on this ModifyOrder
    public CancelOrder toCancelOrder() {
        return new CancelOrder(this);
    }

    public Order toNewOrder() {
        return new Order(this, this.price, this.quantity, this.isBuySide);
    }
}
