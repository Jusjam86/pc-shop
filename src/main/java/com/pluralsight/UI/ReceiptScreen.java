package com.pluralsight.UI;

import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;
import com.pluralsight.util.ReceiptWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

//  receiptViewerScreen — browse and view saved receipts from the receipts/ folder
//  accessible from HomeScreen (browse all) or CheckoutScreen (view just-saved receipt)
public class ReceiptScreen implements Screen {
    private final OrderHelper orderService;
    private final Path preselected;

    // used from HomeScreen, full browser
    public ReceiptScreen(OrderHelper orderService) {
        this.orderService = orderService; this.preselected = null;
    }
    // used from CheckoutScreen, jumps straight to the just-saved receipt
    public ReceiptScreen(OrderHelper orderService, Path receipt) {
        this.orderService = orderService; this.preselected = receipt;
    }

    @Override
    public void show() {
        if (preselected != null) { displayReceipt(preselected); return; }
        browseReceipts();
    }

    private void browseReceipts() {
        InputHelper.printHeader("VIEW PAST RECEIPTS");
        List<Path> receipts;
        try { receipts = ReceiptWriter.listReceipts(); }
        catch (IOException e) { System.out.println("  Could not read receipts folder: " + e.getMessage()); return; }

        if (receipts.isEmpty()) {
            System.out.println("  No receipts saved yet.");
            InputHelper.printDivider();
            System.out.println("  Press ENTER to return.");
            InputHelper.readLine("");
            return;
        }
        System.out.println("  Saved receipts (newest first):\n");
        for (int i = 0; i < receipts.size(); i++) {
            System.out.printf("    %d) %s%n", i + 1, receipts.get(i).getFileName());
        }
        System.out.println("    0) Back");
        InputHelper.printDivider();
        int choice = InputHelper.readInt("  Select a receipt to view (0 to go back): ", 0, receipts.size());
        if (choice == 0) return;
        displayReceipt(receipts.get(choice - 1));
        if (InputHelper.readYesOrNo("  View another receipt?")) browseReceipts();
    }

    private void displayReceipt(Path path) {
        InputHelper.printHeader("RECEIPT -- " + path.getFileName());
        try { System.out.println(ReceiptWriter.readReceipt(path)); }
        catch (IOException e) { System.out.println("  Could not read file: " + e.getMessage()); }
        System.out.println("  Press ENTER to continue.");
        InputHelper.readLine("");
    }
}