package com.pluralsight.models;

import com.pluralsight.models.components.Component;
import com.pluralsight.models.enums.FormFactor;
import com.pluralsight.models.enums.PCType;
import com.pluralsight.util.Orderable;
import java.util.ArrayList;
import java.util.List;

public class PCBuild implements Orderable, Customizable {
    private final PCType size;
    private final FormFactor formFactor;
    private final List<Component> components;
    private boolean hasRGBLighting;

    public PCBuild(PCType size, FormFactor formFactor) {
        this.size = size; this.formFactor = formFactor;
        this.components = new ArrayList<>(); this.hasRGBLighting = false;
    }

    // --- Customizable ---
    @Override public void addComponent(Component c) { components.add(c); }
    @Override public List<Component> getComponents() { return new ArrayList<>(components); }

    // --- Orderable ---
    @Override
    public String getName() { return size.getDisplayName() + " " + formFactor.getDisplayName() + " PC Build"; }

    /**
     * Polymorphism — price computed dynamically.
     * Streams — mapToDouble + sum instead of a manual loop.
     */
    @Override
    public double getPrice() {
        double total = size.getBasePrice();
        total += components.stream().mapToDouble(Component::getPrice).sum();
        if (hasRGBLighting) total += getRGBCost();
        return total;
    }

    @Override
    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("  PC Build: ").append(getName()).append("\n");
        sb.append("    Form Factor : ").append(formFactor.getDisplayName()).append("\n");
        sb.append("    Base Price  : $").append(String.format("%.2f", size.getBasePrice())).append("\n");
        if (!components.isEmpty()) {
            sb.append("    Components:\n");
            components.stream()
                    .map(c -> "    " + c.getOrderSummary().trim())
                    .forEach(line -> sb.append(line).append("\n"));
        }
        if (hasRGBLighting) {
            sb.append("    + RGB Lighting Package - $").append(String.format("%.2f", getRGBCost())).append("\n");
        }
        sb.append("    Build Total : $").append(String.format("%.2f", getPrice()));
        return sb.toString();
    }

    private double getRGBCost() {
        return switch (size) {
            case STARTER    -> 29.99;
            case REGULAR    -> 39.99;
            case ENTHUSIAST -> 49.99;
            case ULTRA      -> 79.99;
        };
    }

    public PCType getSize()         { return size; }
    public FormFactor getFormFactor(){ return formFactor; }
    public boolean hasRGBLighting() { return hasRGBLighting; }
    public void setRGBLighting(boolean rgb) { this.hasRGBLighting = rgb; }

    // streams only premium components (CPU, GPU, PSU)
    public List<Component> getPremiumComponents() {
        return components.stream()
                .filter(Component::isPremium)
                .toList();
    }
    // streams only standard components (RAM, Storage, Cooling)
    public List<Component> getStandardComponents() {
        return components.stream()
                .filter(c -> !c
                        .isPremium())
                .toList();
    }
}