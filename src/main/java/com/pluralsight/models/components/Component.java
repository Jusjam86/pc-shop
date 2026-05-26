package com.pluralsight.models.components;
import com.pluralsight.util.Orderable;

 // base for all PC hardware components: CPU, GPU, PSU, RAM, Storage, Cooling

public abstract class Component implements Orderable {

    // variables
    private final String name;
    private final double basePrice;
    private final boolean isPremium;
    private boolean isExtra;
    private final double extraCost;

    // constructor
    protected Component(String name, double basePrice, boolean isPremium, double extraCost) {
        this.name = name; this.basePrice = basePrice;
        this.isPremium = isPremium; this.extraCost = extraCost; this.isExtra = false;
    }

    // methods
    public abstract String getCategory();

    @Override
    public String getName()  { return name; }

    @Override
    public double getPrice() { return basePrice + (isExtra ? extraCost : 0.0); }

    @Override
    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("  + [").append(getCategory()).append("] ").append(name);
        if (isExtra) sb.append(" [Upgraded]");
        sb.append(" - $").append(String.format("%.2f", getPrice()));
        return sb.toString();
    }

    // other variables
    public boolean isPremium()    { return isPremium; }
    public boolean isExtra()      { return isExtra; }
    public double getBasePrice()  { return basePrice; }
    public double getExtraCost()  { return extraCost; }
    public void setExtra(boolean extra) { this.isExtra = extra; }

    // other methods
    @Override
    public String toString() {
        return "[" + getCategory() + "] " + name + (isPremium ? " *" : "") + " - $" + String.format("%.2f", getPrice());
    }
}
