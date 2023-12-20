package com.kaartik.tradingenginejava.service.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class OrderCore implements IOrderCore {

    private final long orderId;
    private final int securityId;
    private final String username;

}
