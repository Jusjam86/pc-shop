package com.pluralsight.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class ReceiptWriter {
    private static final String RECEIPTS = "receipts";

    // saves a receipt to receipts/yyyyMMdd-HHmmss.txt
    public static Path saveReceipt(OrderHelper order) throws IOException {
        Path dir = Paths.get(RECEIPTS);
        if (!Files.exists(dir)) Files.createDirectories(dir);
        Path file = dir.resolve(order.getReceiptFileName());
        Files.writeString(file, order.buildReceiptText());
        return file;
    }

    // returns all saved receipt files, from the newest first (list + sort)
    public static List<Path> listReceipts() throws IOException {
        Path dir = Paths.get(RECEIPTS);
        if (!Files.exists(dir)) return List.of();
        try (var stream = Files.list(dir)) {
            return stream.filter(p -> p.toString().endsWith(".txt"))
                    .sorted(Comparator.reverseOrder())
                    .toList();
        }
    }

    // reads and returns the full text of a saved receipt
    public static String readReceipt(Path receiptPath) throws IOException {
        return Files.readString(receiptPath);
    }
}
