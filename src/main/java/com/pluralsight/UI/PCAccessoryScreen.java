package com.pluralsight.UI;

import com.pluralsight.models.PCAccessory;
import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;
import java.util.List;

// lets the customer add a PC maintenance or setup supply to their order.
// customer selects from the available accessoryType options.
public class PCAccessoryScreen implements Screen {
    private final OrderHelper order;

    public PCAccessoryScreen(OrderHelper order) { this.order = order; }

    @Override
    public void show() {
        boolean active = true;

        while (active) {
            InputHelper.printHeader("ADD PC ACCESSORY");

            // Build a numbered list from the enum then add a 0) Back option
            PCAccessory.AccessoryType[] types = PCAccessory.AccessoryType.values();
            System.out.println("  Select a PC accessory:\n");
            for (int i = 0; i < types.length; i++) {
                System.out.printf("    %d) %s%n", i + 1, types[i]);
            }
            System.out.println("    0) Back to Order Screen");
            InputHelper.printDivider();

            int choice = InputHelper.readInt(
                    "  Your choice (0-" + types.length + "): ", 0, types.length);

            // 0 means the customer wants to go back — exit the loop
            if (choice == 0) {
                active = false;
                continue;
            }

            // Show the selected accessory and confirm before adding
            PCAccessory accessory = new PCAccessory(types[choice - 1]);
            System.out.println("\n" + accessory.getOrderSummary());

            if (InputHelper.readYesOrNo("  Add to order?")) {
                order.addPCAccessory(accessory);
                System.out.println("  " + accessory.getName() + " added!");
            }

            // After adding (or declining), ask if they want another
            active = InputHelper.readYesOrNo("  Add another accessory?");
        }
    }
}
