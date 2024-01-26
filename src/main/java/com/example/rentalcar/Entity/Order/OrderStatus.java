package com.example.rentalcar.Entity.Order;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED("Created"),
    CANCELED("Canceled"),
    PAID("Paid"),
    STARTED("Started"),
    EXPIRED("Expired"),
    ENDED("Ended");

    private final String status;
}
