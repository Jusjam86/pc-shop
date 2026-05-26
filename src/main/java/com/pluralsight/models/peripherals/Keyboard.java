package com.pluralsight.models.peripherals;

import javax.lang.model.element.NestingKind;

public class Keyboard extends Peripheral {

    // variables
    private final String switchType;
    private final boolean isMechanical;
    private final boolean isWireless;

    // constructor
    public Keyboard(String name, double price, String brand, String switchType, boolean isMechanical, boolean isWireless) {
        super(name, price, brand);
        this.switchType = switchType;
        this.isMechanical = isMechanical;
        this.isWireless = isWireless;
    }

    // getter and boolean
    public boolean isMechanical() {return isMechanical;}
    public boolean isWireless() {return isWireless;}
    public String getSwitchType() {return switchType;}

    // methods
    @Override
    public String getPeripheralType() {return "Keyboard";}

    @Override
    public String getOrderSummary(){
        return super.getOrderSummary() + " | " + (isMechanical ? "Mechanical" : "Membrane") + " | "
                + (isWireless ? "Wireless " : "Wired");
    }
}
