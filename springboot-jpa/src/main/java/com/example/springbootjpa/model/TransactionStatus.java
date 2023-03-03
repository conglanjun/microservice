package com.example.springbootjpa.model;

public enum TransactionStatus {
    SUCCESS,
    FAILURE,
    PENDING,
    EXPIRED,
    CANCELLED,
    MANUAL_APPROVAL_REQUIRED,
    REJECTED_BY_STEP_UP,
}
