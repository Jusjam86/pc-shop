package com.pluralsight.util;

// contract for anything that can be placed on an order

public interface Orderable {
    String getName();
    double getPrice();
    String getOrderSummary();
}