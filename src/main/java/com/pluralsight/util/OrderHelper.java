package com.pluralsight.util;

import com.pluralsight.models.PCAccessory;
import com.pluralsight.models.PCBuild;
import com.pluralsight.models.peripherals.Peripheral;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// order — aggregates everything a customer has added during their session
public class OrderHelper {

    private static final DateTimeFormatter RECEIPT_FMT =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final DateTimeFormatter DISPLAY_FMT =
            DateTimeFormatter.ofPattern("MMMM d, yyyy  hh:mm:ss a");

    private final LocalDateTime orderTime;
    private final List<PCBuild> builds;
    private final List<Peripheral> peripherals;
    private final List<PCAccessory> accessories;

    public OrderHelper() {
        this.orderTime   = LocalDateTime.now();
        this.builds      = new ArrayList<>();
        this.peripherals = new ArrayList<>();
        this.accessories = new ArrayList<>();
    }

    // --- Mutators (newest-first insertion) ---
    public void addBuild(PCBuild b)           { builds.add(0, b); }
    public void addPeripheral(Peripheral p)   { peripherals.add(0, p); }
    public void addPCAccessory(PCAccessory a) { accessories.add(0, a); }

    // --- Accessors (defensive copies) ---
    public List<PCBuild>     getBuilds()      { return new ArrayList<>(builds); }
    public List<Peripheral>  getPeripherals() { return new ArrayList<>(peripherals); }
    public List<PCAccessory> getAccessories() { return new ArrayList<>(accessories); }
    public LocalDateTime     getOrderTime()   { return orderTime; }

    // --- Removal by index (used by CartScreen) ---
    public void removeBuild(int index)      { builds.remove(index); }
    public void removePeripheral(int index) { peripherals.remove(index); }
    public void removeAccessory(int index)  { accessories.remove(index); }

    public boolean isEmpty() {
        return builds.isEmpty() && peripherals.isEmpty() && accessories.isEmpty();
    }

    // valid if at least one PC build, or at least one peripheral or accessory
    public boolean isValid() {
        if (!builds.isEmpty()) return true;
        return !peripherals.isEmpty() || !accessories.isEmpty();
    }

    // each item's own getPrice() is called without type-checking
    public double getTotal() {
        return builds.stream().mapToDouble(PCBuild::getPrice).sum()
                + peripherals.stream().mapToDouble(Peripheral::getPrice).sum()
                + accessories.stream().mapToDouble(PCAccessory::getPrice).sum();
    }

    public String getReceiptFileName() { return orderTime.format(RECEIPT_FMT) + ".txt"; }
    public String getDisplayTime()     { return orderTime.format(DISPLAY_FMT); }

    // builds the full formatted receipt string
    public String buildReceiptText() {
        String div  = "=".repeat(58) + "\n";
        String thin = "-".repeat(58) + "\n";
        StringBuilder sb = new StringBuilder();

        sb.append(div);
        sb.append("           NEXUS PC SHOP -- ORDER RECEIPT\n");
        sb.append(div);
        sb.append("Date/Time : ").append(getDisplayTime()).append("\n");
        sb.append(thin);

        if (!builds.isEmpty()) {
            sb.append("  PC BUILDS\n").append(thin);
            builds.forEach(b -> sb.append(b.getOrderSummary()).append("\n").append(thin));
        }
        if (!peripherals.isEmpty()) {
            sb.append("  PERIPHERALS\n");
            peripherals.forEach(p -> sb.append(p.getOrderSummary()).append("\n"));
            sb.append(thin);
        }
        if (!accessories.isEmpty()) {
            sb.append("  PC ACCESSORIES\n");
            accessories.forEach(a -> sb.append(a.getOrderSummary()).append("\n"));
            sb.append(thin);
        }

        sb.append(String.format("  ORDER TOTAL:  $%.2f%n", getTotal()));
        sb.append(div);
        sb.append("  Thank you for building with NEXUS PC SHOP!\n");
        sb.append(div);
        return sb.toString();
    }
}
