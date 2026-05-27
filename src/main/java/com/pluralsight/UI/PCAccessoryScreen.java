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
        InputHelper.printHeader("ADD PC ACCESSORY");

        PCAccessory.AccessoryType type = InputHelper.pickFromList(
                "Select a PC accessory:", List.of(PCAccessory.AccessoryType.values()));

        PCAccessory accessory = new PCAccessory(type);
        System.out.println("\n" + accessory.getOrderSummary());

        if (InputHelper.readYesOrNo("  Add to order?")) {
            order.addPCAccessory(accessory);
            System.out.println("  PC Accessory added!");
        }
    }
}
