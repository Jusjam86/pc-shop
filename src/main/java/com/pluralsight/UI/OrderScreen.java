package com.pluralsight.UI;


import com.pluralsight.util.OrderHelper;
import com.pluralsight.util.InputHelper;

// main hub after "New Order" is selected
// options: Add PC Build | Add Peripheral | Add PC Accessory | View Cart | Checkout | Cancel Order.
// order is created and owned here; sub-screens receive it
public class OrderScreen implements Screen {

    private final OrderHelper orderService;

    public OrderScreen(OrderHelper orderService) { this.orderService = orderService; }

    @Override
    public void show() {
        OrderHelper order = new OrderHelper();
        boolean active = true;

        while (active) {
            InputHelper.printHeader("ORDER SCREEN");
            printCartLine(order);
            System.out.println("  1) Add PC Build");
            System.out.println("  2) Add Peripheral  (mouse, keyboard, monitor, headset...)");
            System.out.println("  3) Add PC Accessory  (USB hubs, cable kit, thermal paste...)");
            System.out.println("  4) View Cart");
            System.out.println("  5) Checkout");
            System.out.println("  0) Cancel Order");
            InputHelper.printDivider();

            int choice = InputHelper.readInt("  Select an option: ", 0, 5);
            switch (choice) {
                case 1 -> new BuildScreen(order).show();
                case 2 -> new PeripheralScreen(order).show();
                case 3 -> new PCAccessoryScreen(order).show();
                case 4 -> new CartScreen(order).show();
                case 5 -> { new CheckoutScreen(order, orderService).show(); active = false; }
                case 0 -> { System.out.println("\n  Order cancelled. Returning to home screen.\n"); active = false; }
            }
        }
    }

    // one line cart summary shown at the top of each menu loop
    private void printCartLine(OrderHelper order) {
        if (order.isEmpty()) { System.out.println("  Cart: (empty)\n"); return; }
        System.out.printf("  Cart Total: $%.2f  |  Builds: %d  Peripherals: %d  Accessories: %d%n%n",
                order.getTotal(),
                order.getBuilds().size(),
                order.getPeripherals().size(),
                order.getAccessories().size());
    }
}