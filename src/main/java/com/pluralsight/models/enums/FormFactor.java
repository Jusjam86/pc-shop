package com.pluralsight.models.enums;

// pc chassis/ form factor determines the physical size and expandability
// learned this from Claude
// enums are a special data type that defines a fixed set of named constants
// they're used when a variable should only ever hold one of a predefined set of values

public enum FormFactor {
    MINI_ITX("Mini-ITX", "Ultra-compact, great for tight spaces"),
    MICRO_ATX("Micro-ATX", "Balanced size and expandability"),
    MID_TOWER("Mid-Tower ATX", "Most popular all-around build"),
    FULL_TOWER("Full-Tower ATX", "Maximum airflow and expansion slots");

    // variables
    private final String displayName;
    private final String description;

    // constructor
    FormFactor(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    // getters
    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }

    // methods
    @Override
    public String toString() { return displayName; }
}