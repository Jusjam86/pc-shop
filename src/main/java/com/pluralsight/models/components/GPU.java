package com.pluralsight.models.components;

public class GPU extends Component {

    // variables
    private final int vramGB;

    // constructor
    public GPU(String name, double basePrice, double extraCost, int vramGB) {
        super(name, basePrice, true, extraCost);
        this.vramGB = vramGB;
    }

    // getters
    public int getVramGB() { return vramGB; }

    // methods
    @Override public String getCategory() { return "GPU"; }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " + vramGB + "GB VRAM";
    }
}
