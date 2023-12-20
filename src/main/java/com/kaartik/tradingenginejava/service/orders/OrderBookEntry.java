package com.kaartik.tradingenginejava.service.orders;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class OrderBookEntry {

    @Getter
    private final LocalDateTime creationTime;
    @Getter
    private final Limit parentLimit;
    @Getter
    private final Order currentOrder;

    @Getter @Setter
    private OrderBookEntry next;
    @Getter @Setter
    private OrderBookEntry previous;

    public OrderBookEntry(Order currentOrder, Limit parentLimit) {
        this.creationTime = LocalDateTime.now();
        this.parentLimit = parentLimit;
        this.currentOrder = currentOrder;
    }


}
