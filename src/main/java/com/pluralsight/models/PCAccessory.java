package com.pluralsight.models;
import com.pluralsight.util.Orderable;

// do not make this a record class because it doesn't fit in this context for the project
// even though it fits the criteria
public class PCAccessory implements Orderable {

    // lists all available PC accessories
    public enum AccessoryType {
        USB_HUB              ("USB 3.0 Hub (4-port)",    12.99),
        USB_HUB2             ("USB 3.0 Hub (8-port)",    24.99),
        CABLE_MANAGEMENT_KIT ("Cable Management Kit",     7.99),
        THERMAL_PASTE        ("Premium Thermal Paste",    6.99),
        DUST_FILTER_SET      ("Magnetic Dust Filter Set", 10.99),
        SCREEN_CLEANING_KIT  ("Screen Cleaning Kit",      6.99);

        // needs to be in here
        private final String displayName;
        private final double price;

        // constructor
        AccessoryType(String displayName, double price) {
            this.displayName = displayName;
            this.price = price;
        }

        // enums getter
        public String getDisplayName() { return displayName; }
        public double getPrice()       { return price; }

         // enums method
        @Override
        public String toString() { return displayName + " ($" + String.format("%.2f", price) + ")"; }
    }

    // getter & variable
    private final AccessoryType type;
    public PCAccessory(AccessoryType type) { this.type = type; }
    public AccessoryType getType() { return type; }

    // methods
    @Override public String getName()  { return type.getDisplayName(); }
    @Override public double getPrice() { return type.getPrice(); }

    @Override
    public String getOrderSummary() {
        return "  [PC Accessory] " + getName() + " - $" + String.format("%.2f", getPrice());
    }

    @Override
    public String toString() { return getName() + " - $" + String.format("%.2f", getPrice()); }
}