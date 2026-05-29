package com.pluralsight.UI;

import com.pluralsight.models.PCAccessory;
import com.pluralsight.models.PCBuild;
import com.pluralsight.models.peripherals.Peripheral;
import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;

import java.util.List;

// read-only view of everything currently in the customer's order
public class CartScreen implements Screen {
    private final OrderHelper order;

    public CartScreen(OrderHelper order) { this.order = order; }

    @Override
    public void show() {
        // Keep showing the cart until the customer chooses to leave
        while (true) {
            InputHelper.printHeader("YOUR CART");

            if (order.isEmpty()) {
                System.out.println("  Your cart is empty.");
                InputHelper.printDivider();
                System.out.println("  Press ENTER to return to the order screen.");
                InputHelper.readLine("");
                return;
            }

            // Print every item with a numbered label for removal
            printNumberedCart();

            InputHelper.printDivider();
            int totalItems = order.getBuilds().size()
                    + order.getPeripherals().size()
                    + order.getAccessories().size();

            System.out.println("  Enter the item number to remove it, or 0 to go back.");
            int choice = InputHelper.readInt(
                    "  Your choice (0-" + totalItems + "): ", 0, totalItems);

            if (choice == 0) return;

            removeItem(choice);
        }
    }
    private void printNumberedCart() {
        String thin = "-".repeat(58);
        int counter = 1;

        // ── PC Builds ──────────────────────────────────────────────
        List<PCBuild> builds = order.getBuilds();
        if (!builds.isEmpty()) {
            System.out.println("  PC BUILDS");
            System.out.println("  " + thin);
            for (PCBuild b : builds) {
                System.out.printf("  %d) %s%n", counter++, b.getName());
                // Print component details indented under the build name
                b.getComponents().forEach(c ->
                        System.out.println("       " + c.getOrderSummary().trim()));
                if (b.hasRGBLighting())
                    System.out.println("       + RGB Lighting Package");
                System.out.printf("       Build Total: $%.2f%n", b.getPrice());
                System.out.println("  " + thin);
            }
        }

        // ── Peripherals ────────────────────────────────────────────
        List<Peripheral> peripherals = order.getPeripherals();
        if (!peripherals.isEmpty()) {
            System.out.println("  PERIPHERALS");
            for (Peripheral p : peripherals) {
                System.out.printf("  %d) %s%n", counter++, p.getOrderSummary().trim());
            }
            System.out.println("  " + thin);
        }

        // ── PC Accessories ─────────────────────────────────────────
        List<PCAccessory> accessories = order.getAccessories();
        if (!accessories.isEmpty()) {
            System.out.println("  PC ACCESSORIES");
            for (PCAccessory a : accessories) {
                System.out.printf("  %d) %s%n", counter++, a.getOrderSummary().trim());
            }
            System.out.println("  " + thin);
        }

        // ── Totals ─────────────────────────────────────────────────
        System.out.printf("  CART TOTAL:  $%.2f%n", order.getTotal());
        System.out.printf("  Builds: %d  |  Peripherals: %d  |  Accessories: %d%n",
                builds.size(), peripherals.size(), accessories.size());
        System.out.println();
    }
    // allow user to remove any item chosen by its number
    private void removeItem(int choice) {
        List<PCBuild> builds = order.getBuilds();
        List<Peripheral> peripherals = order.getPeripherals();
        List<PCAccessory> accessories = order.getAccessories();

        int buildCount = builds.size();
        int peripheralCount = peripherals.size();

        String itemName;
        Runnable removeAction;

        if (choice <= buildCount) {
            // Falls in the builds range
            int idx = choice - 1;
            itemName = builds.get(idx).getName();
            removeAction = () -> order.removeBuild(idx);

        } else if (choice <= buildCount + peripheralCount) {
            // Falls in the peripherals range
            int idx = choice - buildCount - 1;
            itemName = peripherals.get(idx).getName();
            removeAction = () -> order.removePeripheral(idx);

        } else {
            // Falls in the accessories range
            int idx = choice - buildCount - peripheralCount - 1;
            itemName = accessories.get(idx).getName();
            removeAction = () -> order.removeAccessory(idx);
        }

        // Confirm before removing
        System.out.printf("%n  Remove \"%s\" from your cart?%n", itemName);
        if (InputHelper.readYesOrNo("  Confirm")) {
            removeAction.run();
            System.out.println("  \"" + itemName + "\" removed from cart.");
        } else {
            System.out.println("  No changes made.");
        }
    }
}
