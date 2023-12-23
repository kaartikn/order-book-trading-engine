package com.kaartik.tradingenginejava.service.orders;

import java.util.Comparator;

// The comparer sorts Bids in descending order and Asks in ascending order.
public class LimitComparer {
    private LimitComparer(){}

    public static class BidLimitComparer implements Comparator<Limit> {
        private static final Comparator<Limit> COMPARER = new BidLimitComparer();
        public static Comparator<Limit> getComparer() {
            return COMPARER;
        }

        @Override
        public int compare(Limit x, Limit y) {
            if (x.getPrice() == y.getPrice()) return 0;
            else if (x.getPrice() > y.getPrice()) return -1;
            else return 1;
        }
    }

    public static class AskLimitComparer implements Comparator<Limit> {
        private static final Comparator<Limit> COMPARER = new AskLimitComparer();

        public static Comparator<Limit> getComparer() {
            return COMPARER;
        }

        @Override
        public int compare(Limit x, Limit y) {
            if (x.getPrice() == y.getPrice()) return 0;
            else if (x.getPrice() > y.getPrice()) return 1;
            else return -1;
        }
    }

}

