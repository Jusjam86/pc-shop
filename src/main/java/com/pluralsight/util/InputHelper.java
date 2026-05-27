package com.pluralsight.util;

import java.util.List;
import java.util.Scanner;

// this class holds the scanner instance as a private static final so that
// no screen class ever creates its own scanner
public class InputHelper {
    private static final Scanner userInput = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return userInput.nextLine().trim();
    }

    public static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = userInput.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.println("  Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static boolean readYesOrNo(String prompt) {
        while (true) {
            String input = readLine(prompt + " (y/n): ").toLowerCase();
            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;
            System.out.println("  Please enter y or n.");
        }
    }


    // IntStream prints numbered menu
    public static <T> T pickFromList(String label, List<T> items) {
        System.out.println("\n  " + label);
        java.util.stream.IntStream.range(0, items.size())
                .forEach(i -> System.out.printf("    %d) %s%n", i + 1, items.get(i)));
        int choice = readInt("  Your choice (1-" + items.size() + "): ", 1, items.size());
        return items.get(choice - 1);
    }

    public static void printDivider() {
        System.out.println("-".repeat(58));
    }

    public static void printHeader(String text) {
        System.out.println("\n" + "=".repeat(58));
        System.out.println("  " + text);
        System.out.println("=".repeat(58));
    }
}