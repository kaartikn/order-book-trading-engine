package com.kaartik.tradingenginejava.service.orderbook;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OrderBookSpread {
    private Optional<Long> bid;
    private Optional<Long> ask;

    public OrderBookSpread(Long bid, Long ask) {
        this.bid = Optional.ofNullable(bid);
        this.ask = Optional.ofNullable(ask);
    }

    public Optional<Long> getBid() {
        return bid;
    }

    public Optional<Long> getAsk() {
        return ask;
    }

    public Optional<Long> getSpread() {
        if (bid.isPresent() && ask.isPresent()) {
            return Optional.of(ask.get() - bid.get());
        } else {
            return Optional.empty();
        }
    }
}
