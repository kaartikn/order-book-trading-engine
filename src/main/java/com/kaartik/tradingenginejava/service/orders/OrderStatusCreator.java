package com.kaartik.tradingenginejava.service.orders;

public final class OrderStatusCreator {

    public static CancelOrderStatus generateCancelOrderStatus(CancelOrder co) {
        return new CancelOrderStatus();
    }

    public static NewOrderStatus generateNewOrderStatus(Order co) {
        return new NewOrderStatus();
    }

    public static ModifyOrderStatus generateModifyOrderStatus(ModifyOrder co) {
        return new ModifyOrderStatus();
    }
}
