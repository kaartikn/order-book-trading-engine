package com.kaartik.tradingenginejava.service.orderbook;

import com.kaartik.tradingenginejava.service.orders.OrderBookEntry;

import java.util.List;

public interface IRetrievalOrderbook extends IOrderEntryOrderbook {
    List<OrderBookEntry> getAskOrders();
    List<OrderBookEntry> getBidOrders();
}
