package com.example.model;

public enum OrderStatus {

    BEING_CREATED, PAID_FOR, FULFILLED, CANCELLED;

    /**
     * Verify the transition between {@link OrderStatus} is valid.
     * <p>
     * NOTE: This is where any/all rules for state transitions should be kept and enforced.
     */
    public static boolean valid(OrderStatus currentStatus, OrderStatus newStatus) {

        if (currentStatus == BEING_CREATED) {
            return newStatus == PAID_FOR || newStatus == CANCELLED;
        } else if (currentStatus == PAID_FOR) {
            return newStatus == FULFILLED;
        } else if (currentStatus == FULFILLED) {
            return false;
        } else if (currentStatus == CANCELLED) {
            return false;
        } else {
            throw new RuntimeException("Unrecognized situation.");
        }
    }
}