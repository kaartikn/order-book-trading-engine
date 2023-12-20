package com.kaartik.tradingenginejava.service.orders;

public final class RejectCreator {

    public static Rejection generateOrderCoreRejection(IOrderCore rejectedOrder, RejectionReason rejectionReason){
        return new Rejection(rejectedOrder, rejectionReason);
    }

}
