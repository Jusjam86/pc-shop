package com.pluralsight.models.peripherals;

public class Headset extends Peripheral {

    // variables
    private final boolean isSurround;
    private final boolean isWireless;

    // constructor
    public Headset(String name, double price, String brand, boolean isSurround, boolean isWireless) {
        super(name, price, brand);
        this.isSurround = isSurround;
        this.isWireless = isWireless;
    }

    // boolean
    public boolean isSurround() {return isSurround;}
    public boolean isWireless() {return isWireless;}

    // methods
    @Override
    public String getPeripheralType() {return "Headset";}

    @Override
    public String getOrderSummary(){
        return super.getOrderSummary() + " |  " + (isSurround ? "7.1 Surround" : "Stereo") + ", "
                + (isWireless ? "Wireless" : "Wired");
    }
}
