package com.pluralsight.models.peripherals;

public class MousePad extends Peripheral {

    // variables
    private final String size;

    // constructor
    public MousePad(String name, double price, String brand, String size) {
        super(name, price, brand);
        this.size = size;
    }

    // getter
    public String getSize() {return size;}

    // methods
    @Override public String getPeripheralType() { return "Mouse Pad"; }

    @Override
    public String getOrderSummary() { return super.getOrderSummary() + " | " + size; }

}
