package com.pluralsight.UI;

import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;

// entry point fo user
public class HomeScreen implements Screen{
    private final OrderHelper orderService;
    public  HomeScreen(OrderHelper orderService) {this.orderService = orderService;}

    @Override
    public void show() {

        boolean running = true;

        while (running) {

            InputHelper.printHeader("NEXUS PC SHOP -- Welcome!");
            System.out.println("  1) New Order");
            System.out.println("  2) View Past Receipts");
            System.out.println("  0) Exit");
            InputHelper.printDivider();
            int choice = InputHelper.readInt("  Select an option: ", 0, 2);

            switch (choice)
            {
                case 1 -> new OrderScreen(orderService).show();
                case 2 -> new ReceiptScreen(orderService).show();
                case 0 -> {
                    System.out.println("\n  Thanks for visiting NEXUS PC SHOP. Goodbye!\n");
                    running = false;
                }
            }
        }
    }
}
