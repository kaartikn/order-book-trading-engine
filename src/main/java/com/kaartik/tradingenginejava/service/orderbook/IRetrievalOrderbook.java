package com.kaartik.tradingenginejava.service.orderbook;

import com.kaartik.tradingenginejava.service.orders.OrderBookEntry;

import java.util.List;

// Theoretically this Interface is to allow us to retrieve order book inputs from outside the orderbook.
public interface IRetrievalOrderbook extends IOrderEntryOrderbook {
    List<OrderBookEntry> getAskOrders();
    List<OrderBookEntry> getBidOrders();
}
