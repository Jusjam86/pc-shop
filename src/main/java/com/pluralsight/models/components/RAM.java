package com.pluralsight.models.components;

public class RAM extends Component {

    // variables
    private final int capacityGB;
    private final int speedMHz;

    // constructor
    public RAM(String name, double basePrice, int capacityGB, int speedMHz) {
        super(name, basePrice, false, 0.0);
        this.capacityGB = capacityGB; this.speedMHz = speedMHz;
    }

    // getters
    public int getCapacityGB() { return capacityGB; }
    public int getSpeedMHz()   { return speedMHz; }

    // methods
    @Override public String getCategory() { return "RAM"; }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " + capacityGB + "GB @ " + speedMHz + "MHz";
    }
}
