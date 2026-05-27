package com.pluralsight.UI;

import com.pluralsight.catalog.PartsCatalog;
import com.pluralsight.models.peripherals.Peripheral;
import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;
import java.util.ArrayList;

// peripheralScreen — add mice, mouse pads, keyboards, monitors, or headsets.
// all types handled through the Peripheral abstract class.
public class PeripheralScreen implements Screen {
    private final OrderHelper order;
    public PeripheralScreen(OrderHelper order) { this.order = order; }

    @Override
    public void show() {
        InputHelper.printHeader("ADD PERIPHERAL");
        boolean adding = true;
        while (adding) {
            System.out.println("  What peripheral would you like to add?");
            System.out.println("  1) Mouse");
            System.out.println("  2) Mouse Pad");
            System.out.println("  3) Keyboard");
            System.out.println("  4) Monitor");
            System.out.println("  5) Headset");
            System.out.println("  0) Done");
            InputHelper.printDivider();
            int choice = InputHelper.readInt("  Select: ", 0, 5);
            Peripheral picked = switch (choice) {
                case 1 -> InputHelper.pickFromList("Select Mouse:",     new ArrayList<>(PartsCatalog.getMice()));
                case 2 -> InputHelper.pickFromList("Select Mouse Pad:", new ArrayList<>(PartsCatalog.getMousePads()));
                case 3 -> InputHelper.pickFromList("Select Keyboard:",  new ArrayList<>(PartsCatalog.getKeyboards()));
                case 4 -> InputHelper.pickFromList("Select Monitor:",   new ArrayList<>(PartsCatalog.getMonitors()));
                case 5 -> InputHelper.pickFromList("Select Headset:",   new ArrayList<>(PartsCatalog.getHeadsets()));
                case 0 -> { adding = false; yield null; }
                default -> null;
            };
            if (picked != null) {
                System.out.println("\n" + picked.getOrderSummary());
                if (InputHelper.readYesOrNo("  Add to order?")) {
                    order.addPeripheral(picked);
                    System.out.println("  " + picked.getPeripheralType() + " added!");
                }
                adding = InputHelper.readYesOrNo("  Add another peripheral?");
            }
        }
    }
}
