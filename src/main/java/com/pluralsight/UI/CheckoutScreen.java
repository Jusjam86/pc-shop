package com.pluralsight.UI;

import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;
import com.pluralsight.util.ReceiptWriter;
import java.io.IOException;
import java.nio.file.Path;

// shows the full order summary, then either confirms (saves receipt) or cancels
// after purchase, offers to view the receipt
public class CheckoutScreen implements Screen {
    private final OrderHelper order;
    private final OrderHelper orderService;
    public CheckoutScreen(OrderHelper order, OrderHelper orderService) {
        this.order = order; this.orderService = orderService;
    }

    @Override
    public void show() {
        InputHelper.printHeader("CHECKOUT");
        if (!order.isValid()) {
            System.out.println("  Your order is empty. Please add at least one item.");
            InputHelper.printDivider();
            return;
        }
        System.out.println(order.buildReceiptText());
        System.out.println("  1) Confirm Order  (save receipt)");
        System.out.println("  0) Cancel Order   (discard and return to home)");
        InputHelper.printDivider();
        if (InputHelper.readInt("  Select: ", 0, 1) == 1) {
            confirmOrder();
        } else {
            System.out.println("\n  Order cancelled. See you next time!\n");
        }
    }

    private void confirmOrder() {
        try {
            Path saved = ReceiptWriter.saveReceipt(order);
            System.out.println("\n  Order confirmed!");
            System.out.println("  Receipt saved: " + saved.getFileName());
            System.out.println("  Thank you for building with NEXUS PC SHOP!\n");
            if (InputHelper.readYesOrNo("  View your receipt now?")) {
                new ReceiptScreen(orderService, saved).show();
            }
        } catch (IOException e) {
            System.out.println("  Warning: Could not save receipt -- " + e.getMessage());
        }
    }
}
