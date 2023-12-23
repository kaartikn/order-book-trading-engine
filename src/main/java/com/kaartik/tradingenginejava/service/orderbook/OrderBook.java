package com.kaartik.tradingenginejava.service.orderbook;

import com.kaartik.tradingenginejava.service.instrument.Security;
import com.kaartik.tradingenginejava.service.orders.*;

import java.util.*;

//Cannot match orders in the OrderBook thus far
public class OrderBook implements IRetrievalOrderbook {

    private final Security instrument;
    private final HashMap<Long, OrderBookEntry> orders = new HashMap<>();
    private final TreeSet<Limit> askLimits = new TreeSet<>(LimitComparer.AskLimitComparer.getComparer());
    private final TreeSet<Limit> bidLimits = new TreeSet<>(LimitComparer.BidLimitComparer.getComparer());

    public OrderBook(Security instrument) {
        this.instrument = instrument;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public void addOrder(Order order) {
        Limit baseLimit = new Limit(order.getPrice());
        addOrder(order, baseLimit, order.isBuySide() ? bidLimits : askLimits, orders);
    }

    private static void addOrder(Order order, Limit baseLimit, SortedSet<Limit> limitLevels, HashMap<Long, OrderBookEntry> internalBook){
        OrderBookEntry orderBookEntry = new OrderBookEntry(order, baseLimit);

        // Use the contains method to check if the TreeSet already has a limit with the same price.
        if (limitLevels.contains(baseLimit)) {
            // Retrieve the existing limit from the TreeSet
            Limit limit = limitLevels.tailSet(baseLimit).first();
            if (limit.isEmpty()) {
                limit.setHead(orderBookEntry);
                limit.setTail(orderBookEntry);
            } else {
                OrderBookEntry tailPointer = limit.getTail();
                tailPointer.setNext(orderBookEntry);
                orderBookEntry.setPrevious(tailPointer);
                limit.setTail(orderBookEntry);
            }
        } else {
            limitLevels.add(baseLimit);
            baseLimit.setHead(orderBookEntry);
            baseLimit.setTail(orderBookEntry);
        }
        internalBook.put(order.getOrderId(), orderBookEntry);
    }

    @Override
    public void changeOrder(ModifyOrder modifyOrder) {
        OrderBookEntry obe = orders.get(modifyOrder.getOrderId());
        if (obe != null){
            removeOrder(modifyOrder.toCancelOrder());
            addOrder(modifyOrder.toNewOrder(), obe.getParentLimit(), modifyOrder.isBuySide() ? bidLimits : askLimits, orders);
        }
    }

    @Override
    public boolean containsOrder(long orderId) {
        return orders.containsKey(orderId);
    }

    @Override
    public List<OrderBookEntry> getAskOrders() {
        List<OrderBookEntry> orderBookEntries = new ArrayList<>();
        for (Limit ask : askLimits) {
            if (!ask.isEmpty()) {
                OrderBookEntry askPointer = ask.getHead();
                while (askPointer != null){
                    orderBookEntries.add(askPointer);
                    askPointer = askPointer.getNext();
                }
            }
        }
        return orderBookEntries;
    }

    @Override
    public List<OrderBookEntry> getBidOrders() {
        List<OrderBookEntry> orderBookEntries = new ArrayList<>();
        for (Limit bid : bidLimits) {
            if (!bid.isEmpty()) {
                OrderBookEntry bidPointer = bid.getHead();
                while (bidPointer != null) {
                    orderBookEntries.add(bidPointer);
                    bidPointer = bidPointer.getNext();
                }
            }
        }
        return orderBookEntries;
    }

    @Override
    public OrderBookSpread getSpread() {
        Long bestAsk = (askLimits.isEmpty() || askLimits.first().isEmpty()) ? null : askLimits.first().getPrice();
        Long bestBid = (bidLimits.isEmpty() || bidLimits.last().isEmpty()) ? null : bidLimits.last().getPrice();
        return new OrderBookSpread(bestBid, bestAsk);
    }

    @Override
    public void removeOrder(CancelOrder cancelOrder) {
        OrderBookEntry obe = orders.get(cancelOrder.getOrderId());
        if (obe != null) {
            removeOrder(cancelOrder.getOrderId(), obe, orders);
        }
    }

    private static void removeOrder(long orderId, OrderBookEntry obe, HashMap<Long, OrderBookEntry> internalBook){
        // LinkedList fix
        if (obe.getPrevious() != null) {
            obe.getPrevious().setNext(obe.getNext());
        }
        if (obe.getNext() != null) {
            obe.getNext().setPrevious(obe.getPrevious());
        }

        // OrderBookEntry on Limit Level
        if (obe.getParentLimit().getHead() == obe) {
            obe.getParentLimit().setHead(obe.getNext());
        }
        if (obe.getParentLimit().getTail() == obe) {
            obe.getParentLimit().setTail(obe.getPrevious());
        }

        internalBook.remove(orderId);
    }

}
