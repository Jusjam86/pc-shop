package com.pluralsight.models.peripherals;

public class Mouse extends Peripheral {

    // variables
    private int dpi;
    private boolean isWireless;

    // constructor
    public Mouse(String name, double price, String brand, int dpi, boolean isWireless) {
        super(name, price, brand);
        this.dpi = dpi;
        this.isWireless = isWireless;
    }

    // getter & boolean
    public int getDpi() { return dpi; }
    public boolean isWireless() { return isWireless; }

    // methods
    @Override public String getPeripheralType() { return "Mouse"; }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " + dpi + " DPI, " + (isWireless ? "Wireless" : "Wired");
    }
}
