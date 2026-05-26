package com.pluralsight.models.enums;

// build tier determines base price and component capacity
// learned this from Claude
// enums are a special data type that defines a fixed set of named constants
// they're used when a variable should only ever hold one of a predefined set of values

public enum PCType {

    STARTER("Starter Build", 399.99),
    REGULAR("Regular Build", 699.99),
    ENTHUSIAST("Enthusiast Build", 799.99),
    ULTRA("Ultra Build", 1299.99);

    // variables
    private final String displayName;
    private final double basePrice;

    // constructor
    PCType(String displayName, double basePrice) {
        this.displayName = displayName;
        this.basePrice = basePrice;
    }

    // getters
    public String getDisplayName() { return displayName; }
    public double getBasePrice()   { return basePrice; }

    // methods
    @Override
    public String toString() { return displayName + " ($" + String.format("%.2f", basePrice) + ")"; }
}
