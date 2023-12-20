package com.kaartik.tradingenginejava.service.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderRecord {
    private final long orderId;
    private final int quantity;
    private final long price;
    private final boolean isBuySide;
    private final String username;
    private final int securityId;
    private final int theoreticalQueuePosition;
}
