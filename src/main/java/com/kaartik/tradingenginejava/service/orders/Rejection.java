package com.kaartik.tradingenginejava.service.orders;

public record Rejection(IOrderCore rejectedOrder,
                        RejectionReason rejectionReason) implements IOrderCore {

    public RejectionReason getRejectedReason() {
        return rejectionReason;
    }

    @Override
    public long getOrderId() {
        return rejectedOrder.getOrderId();
    }

    @Override
    public String getUsername() {
        return rejectedOrder.getUsername();
    }

    @Override
    public int getSecurityId() {
        return rejectedOrder.getSecurityId();
    }
}
