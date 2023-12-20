package com.kaartik.tradingenginejava.service.orders;

public class CancelOrder implements IOrderCore {

    private final IOrderCore orderCore;

    public CancelOrder(IOrderCore orderCore){
        this.orderCore = orderCore;
    }

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
}
