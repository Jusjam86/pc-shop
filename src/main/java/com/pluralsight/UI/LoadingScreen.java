package com.pluralsight.UI;

/**
 * [CLASS] — com.pcshop.ui
 * Displays an animated ASCII loading screen with a progress bar
 * when the application first starts. Uses ANSI escape codes to
 * rewrite the bar in place on a single line instead of scrolling.
 * Called once from Main before HomeScreen is shown.
 */
public class LoadingScreen {

    // Width of the progress bar in characters
    private static final int BAR_WIDTH = 40;

    // ANSI escape codes for colors and cursor control
    private static final String RESET   = "\u001B[0m";
    private static final String BOLD    = "\u001B[1m";
    private static final String CYAN    = "\u001B[36m";
    private static final String GREEN   = "\u001B[32m";
    private static final String YELLOW  = "\u001B[33m";
    private static final String WHITE   = "\u001B[97m";
    private static final String DIM     = "\u001B[2m";

    // Cursor: move to start of current line and clear it
    private static final String LINE_START = "\r";

    /** Steps shown while the bar fills up, paired with how far along the bar is (0–100). */
    private static final Object[][] STEPS = {
            { 10, "Initializing system...          " },
            { 25, "Loading product catalog...      " },
            { 40, "Configuring component pricing..." },
            { 55, "Setting up order service...     " },
            { 70, "Preparing UI screens...         " },
            { 85, "Loading signature builds...     " },
            { 95, "Finalizing setup...             " },
            {100, "Ready!                          " },
    };

    /**
     * Runs the full loading sequence.
     * Prints the banner, animates the progress bar through each step,
     * then clears the loading area and hands off to HomeScreen.
     */
    public static void show() {
        clearScreen();
        printBanner();
        runProgressBar();
        finalize_screen();
    }

    // ──────────────────────────────────────────────────────── Banner ────────────────────────────────────────────────

    private static void printBanner() {
        System.out.println();
        System.out.println(CYAN + BOLD +
                "  ███╗   ██╗███████╗██╗  ██╗██╗   ██╗███████╗" + RESET);
        System.out.println(CYAN + BOLD +
                "  ████╗  ██║██╔════╝╚██╗██╔╝██║   ██║██╔════╝" + RESET);
        System.out.println(CYAN + BOLD +
                "  ██╔██╗ ██║█████╗   ╚███╔╝ ██║   ██║███████╗" + RESET);
        System.out.println(CYAN + BOLD +
                "  ██║╚██╗██║██╔══╝   ██╔██╗ ██║   ██║╚════██║" + RESET);
        System.out.println(CYAN + BOLD +
                "  ██║ ╚████║███████╗██╔╝ ██╗╚██████╔╝███████║" + RESET);
        System.out.println(CYAN + BOLD +
                "  ╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝" + RESET);
        System.out.println();
        System.out.println(WHITE + BOLD +
                "              PC  SHOP  APPLICATION" + RESET);
        System.out.println(DIM +
                "          Build Your Dream PC, Your Way." + RESET);
        System.out.println();
        System.out.println("  " + "─".repeat(BAR_WIDTH + 10));
        System.out.println();
    }

    // ────────────────────────────────────────────────── Progress bar ────────────────────────────────────────────────

    private static void runProgressBar() {
        int currentPercent = 0;

        for (Object[] step : STEPS) {
            int targetPercent = (int) step[0];
            String label      = (String) step[1];

            // Animate smoothly from currentPercent to targetPercent
            while (currentPercent < targetPercent) {
                currentPercent++;
                printBar(currentPercent, label);
                sleep(18); // ms per tick — controls overall speed
            }

            // Pause briefly at each named step so the label is readable
            sleep(120);
        }

        System.out.println(); // newline after the bar finishes
    }

    /**
     * Prints (or rewrites) the progress bar on a single line.
     * Uses \r to return to the start of the line without scrolling.
     *
     * Example output:
     *   Loading product catalog...   [████████████░░░░░░░░░░░░░░░░░░░░░░░░░░░░]  30%
     */
    private static void printBar(int percent, String label) {
        int filled  = (int) Math.round((percent / 100.0) * BAR_WIDTH);
        int empty   = BAR_WIDTH - filled;

        String filledBar = GREEN  + "█".repeat(filled)  + RESET;
        String emptyBar  = DIM    + "░".repeat(empty)   + RESET;
        String barColor  = percent == 100 ? GREEN : YELLOW;

        System.out.printf("%s  %s%s%s  [%s%s]  %s%3d%%%s",
                LINE_START,
                DIM, label, RESET,
                filledBar, emptyBar,
                barColor + BOLD, percent, RESET);

        // Flush immediately so the rewrite appears without buffering
        System.out.flush();
    }

    // ────────────────────────────────────────────────── Finish ──────────────────────────────────────────────────────

    private static void finalize_screen() {
        sleep(300);
        System.out.println();
        System.out.println("  " + "─".repeat(BAR_WIDTH + 10));
        System.out.println();
        sleep(200);
    }

    // ────────────────────────────────────────────────── Helpers ─────────────────────────────────────────────────────

    private static void clearScreen() {
        // ANSI: clear screen and move cursor to top-left
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
