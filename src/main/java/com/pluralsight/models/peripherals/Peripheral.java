package com.pluralsight.models.peripherals;

import com.pluralsight.util.Orderable;

public abstract class Peripheral implements Orderable {

    // variables
    private final String name;
    private final double price;
    private final String brand;

    // constructor
    protected Peripheral(String name, double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    // getter
    public String getBrand() {return brand;}

    // methods
    public abstract String getPeripheralType();

    @Override
    public String getName() {return name;}

    @Override
    public double getPrice() {return price;}

    @Override
    public String getOrderSummary() {return "  [" + getPeripheralType() + "] " + brand + " " + name + " - $" + String.format("%.2f", price);}

    @Override
    public String toString() {return brand + " " + name + " - $" + String.format("%.2f", price);}
}
