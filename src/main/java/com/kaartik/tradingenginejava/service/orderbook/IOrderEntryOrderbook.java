package com.kaartik.tradingenginejava.service.orderbook;

import com.kaartik.tradingenginejava.service.orders.CancelOrder;
import com.kaartik.tradingenginejava.service.orders.ModifyOrder;
import com.kaartik.tradingenginejava.service.orders.Order;

public interface IOrderEntryOrderbook extends IReadOnlyOrderbook {
    void addOrder(Order order);
    void changeOrder(ModifyOrder modifyOrder);
    void removeOrder(CancelOrder cancelOrder);
}
