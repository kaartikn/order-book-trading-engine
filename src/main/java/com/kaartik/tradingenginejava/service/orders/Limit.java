package com.kaartik.tradingenginejava.service.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Limit {
    public long price;
    private OrderBookEntry head;
    private OrderBookEntry tail;

    public Limit(long price){
        this.price = price;
    }

    public int compareTo(Limit other) {
        return Long.compare(this.price, other.price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Limit limit = (Limit) obj;
        return price == limit.price;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(price);
    }

    // The size of independent orders in the orderbook.
    public int getLevelOrderCount(){
        int orderCount = 0;
        OrderBookEntry headPointer = head;
        while (headPointer != null){
            if(headPointer.getCurrentOrder().getCurrentQuantity() != 0)
                orderCount++;
            headPointer = headPointer.getNext();
        }
        return orderCount;
    }

    // Get the cumulative total order quantity.
    public int getLevelOrderQuantity(){
        int orderQuantity = 0;
        OrderBookEntry headPointer = head;
        while (headPointer != null){
            orderQuantity += headPointer.getCurrentOrder().getCurrentQuantity();
            headPointer = headPointer.getNext();
        }
        return orderQuantity;
    }

    // Returns a list of all the OrderRecords at this price level. Doesn't expose the orders themselves but prepares a copy of the orders of the type OrderRecord.
    public List<OrderRecord> getLevelOrderRecords() {
        List<OrderRecord> orderRecords = new ArrayList<>();
        OrderBookEntry headPointer = head;
        int theoreticalQueuePosition = 0;
        while (headPointer != null){
            Order currentOrder = headPointer.getCurrentOrder();
            if (currentOrder.getCurrentQuantity() != 0){
                orderRecords.add(
                        new OrderRecord(
                                currentOrder.getOrderId(),
                                currentOrder.getCurrentQuantity(),
                                price,
                                currentOrder.isBuySide(),
                                currentOrder.getUsername(),
                                currentOrder.getSecurityId(),
                                theoreticalQueuePosition
                                )
                );
            }
            theoreticalQueuePosition++;
            headPointer = headPointer.getNext();
        }
        return orderRecords;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public Side getSide() {
        if (isEmpty()) {
            return Side.Unknown; // Assuming UNKNOWN is an enum value
        } else {
            return head.getCurrentOrder().isBuySide() ? Side.Bid : Side.Ask; // Assuming BID and ASK are enum values
        }
    }

}
