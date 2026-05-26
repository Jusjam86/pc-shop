package com.pluralsight.models.peripherals;

public class Monitor extends Peripheral {

    // variables
    private final double sizeInches;
    private final int refreshRateHz;
    private final String resolution;
    private final String panelType;

    // constructor
    public Monitor(String name, double price, String brand, double sizeInches,
                   int refreshRateHz, String resolution, String panelType) {
        super(name, price, brand);
        this.sizeInches = sizeInches;
        this.refreshRateHz = refreshRateHz;
        this.resolution = resolution;
        this.panelType = panelType;
    }

    // getters
    public double getSizeInches() {return sizeInches;}
    public int getRefreshRateHz() {return refreshRateHz;}
    public String getResolution() {return resolution;}
    public String getPanelType() {return panelType;}

    // methods
    @Override
    public String getPeripheralType() { return "Monitor"; }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " + sizeInches + "\", " + resolution + " @ " + refreshRateHz + "Hz, " + panelType;
    }
}
