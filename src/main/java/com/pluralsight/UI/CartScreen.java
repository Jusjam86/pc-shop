package com.pluralsight.UI;

import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;

// read-only view of everything currently in the customer's order
public class CartScreen implements Screen {
    private final OrderHelper order;

    public CartScreen(OrderHelper order) { this.order = order; }

    @Override
    public void show() {
        InputHelper.printHeader("YOUR CART");

        if (order.isEmpty()) {
            System.out.println("  Your cart is empty. Add items from the order screen first.");
            InputHelper.printDivider();
            System.out.println("  Press ENTER to return.");
            InputHelper.readLine("");
            return;
        }

        printCart();
        InputHelper.printDivider();
        System.out.println("  Press ENTER to return to the order screen.");
        InputHelper.readLine("");
    }

    private void printCart() {
        String thin = "-".repeat(58) + "\n";

        // ── PC Builds ──────────────────────────────────────────────
        if (!order.getBuilds().isEmpty()) {
            System.out.println("  PC BUILDS");
            System.out.print(thin);
            order.getBuilds().forEach(b -> {
                System.out.println(b.getOrderSummary());
                System.out.print(thin);
            });
        }

        // ── Peripherals ────────────────────────────────────────────
        if (!order.getPeripherals().isEmpty()) {
            System.out.println("  PERIPHERALS");
            order.getPeripherals().forEach(p -> System.out.println(p.getOrderSummary()));
            System.out.print(thin);
        }

        // ── PC Accessories ─────────────────────────────────────────
        if (!order.getAccessories().isEmpty()) {
            System.out.println("  PC ACCESSORIES");
            order.getAccessories().forEach(a -> System.out.println(a.getOrderSummary()));
            System.out.print(thin);
        }

        // ── Totals ─────────────────────────────────────────────────
        System.out.printf("  CART TOTAL:  $%.2f%n", order.getTotal());
        System.out.printf("  Builds: %d  |  Peripherals: %d  |  Accessories: %d%n",
                order.getBuilds().size(),
                order.getPeripherals().size(),
                order.getAccessories().size());
    }
}
