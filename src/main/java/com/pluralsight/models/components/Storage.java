package com.pluralsight.models.components;

public class Storage extends Component {

    // variables
    private final String storageType;
    private final int capacityGB;

    // constructor
    public Storage(String name, double basePrice, String storageType, int capacityGB) {
        super(name, basePrice, false, 0.0);
        this.storageType = storageType; this.capacityGB = capacityGB;
    }

    // getters
    public String getStorageType() { return storageType; }
    public int getCapacityGB()     { return capacityGB; }

    // methods
    @Override public String getCategory() { return "Storage"; }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " + storageType + " " + capacityGB + "GB";
    }
}