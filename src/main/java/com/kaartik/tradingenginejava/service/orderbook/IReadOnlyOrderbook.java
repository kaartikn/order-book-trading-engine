package com.kaartik.tradingenginejava.service.orderbook;

public interface IReadOnlyOrderbook {
    boolean containsOrder(long orderId);
    OrderBookSpread getSpread();
    int getCount();
}
