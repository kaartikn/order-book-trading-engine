package com.kaartik.tradingenginejava.service.orders;

public enum RejectionReason {
    Unknown,
    OrderNotFound,
    InstrumentNotFound,
    AttemptingToModifyWrongSide
}
