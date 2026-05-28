package com.pluralsight.UI;

import com.pluralsight.catalog.PartsCatalog;
import com.pluralsight.models.PCBuild;
import com.pluralsight.models.SignatureBuild;
import com.pluralsight.models.components.*;
import com.pluralsight.models.enums.FormFactor;
import com.pluralsight.models.enums.PCType;
import com.pluralsight.util.InputHelper;
import com.pluralsight.util.OrderHelper;
import java.util.List;

 // buildScreen walks the customer through configuring a PC build step by step:
 // 1. Build tier    2. Form factor    3. CPU    4. GPU     5. PSU
 // 6. RAM           7. Storage        8. Cooling    9. RGB Lighting

public class BuildScreen implements Screen {
    private final OrderHelper order;
    public BuildScreen(OrderHelper order) { this.order = order; }

    @Override
    public void show() {
        InputHelper.printHeader("ADD PC BUILD");
        if (InputHelper.readYesOrNo("  Start from a Signature Build template?")) {
            buildFromSignature(); return;
        }

        // Step 1 — Build tier
        PCType size = InputHelper.pickFromList("Select build tier:", List.of(PCType.values()));

        // Step 2 — Form factor
        FormFactor form = InputHelper.pickFromList("Select form factor (chassis):", List.of(FormFactor.values()));
        PCBuild build = new PCBuild(size, form);

        // Step 3 — CPU (premium)
        System.out.println("\n  * Premium component — CPU (Processor)");
        CPU cpu = InputHelper.pickFromList("Select CPU:", PartsCatalog.getCPUs(size));
        if (InputHelper.readYesOrNo("  Upgrade to a higher-tier CPU? (+" + fmt(cpu) + ")")) cpu.setExtra(true);
        build.addComponent(cpu);

        // Step 4 — GPU (premium)
        System.out.println("\n  * Premium component — GPU (Graphics Card)");
        GPU gpu = InputHelper.pickFromList("Select GPU:", PartsCatalog.getGPUs(size));
        if (InputHelper.readYesOrNo("  Upgrade GPU memory? (+" + fmt(gpu) + ")")) gpu.setExtra(true);
        build.addComponent(gpu);

        // Step 5 - PSU
        System.out.println("\n PSU (Power Supply Unit");
        build.addComponent(InputHelper.pickFromList("Select PSU:", PartsCatalog.getPSUs(size)));

        // Step 6 — RAM
        System.out.println("\n  RAM (Memory)");
        build.addComponent(InputHelper.pickFromList("Select RAM:", PartsCatalog.getRAMOptions()));

        // Step 7 — Storage
        System.out.println("\n  Storage");
        build.addComponent(InputHelper.pickFromList("Select Storage:", PartsCatalog.getStorageOptions()));

        // Step 8 — Cooling
        System.out.println("\n  Cooling Solution");
        build.addComponent(InputHelper.pickFromList("Select Cooling:", PartsCatalog.getCoolingOptions()));

        // Step 9 — RGB Lighting (special option)
        System.out.println("\n  Special Option -- RGB Lighting Package");
        System.out.printf("  Full-system RGB lighting (+$%.2f for %s)%n", rgbCost(size), size.getDisplayName());
        if (InputHelper.readYesOrNo("  Add RGB lighting?")) build.setRGBLighting(true);

        System.out.println("\n" + build.getOrderSummary());
        if (InputHelper.readYesOrNo("\n  Add this build to your order?")) {
            order.addBuild(build);
            System.out.println("  Build added");
        } else {
            System.out.println("  Build discarded");
        }
    }

    private void buildFromSignature() {
        List<SignatureBuild> sigs = PartsCatalog.getSignatureBuilds();
        System.out.println();
        for (int i = 0; i < sigs.size(); i++) {
            SignatureBuild s = sigs.get(i);
            System.out.printf("  %d) %s -- %s  ($%.2f)%n", i + 1, s.getSignatureName(), s.getDescription(), s.getPrice());
        }
        SignatureBuild chosen = sigs.get(InputHelper.readInt("  Your choice (1-" + sigs.size() + "): ", 1, sigs.size()) - 1);
        System.out.println("\n" + chosen.getOrderSummary());
        if (!chosen.hasRGBLighting() && InputHelper.readYesOrNo("  Add RGB lighting? (+" + String.format("$%.2f", rgbCost(chosen.getSize())) + ")"))
            chosen.setRGBLighting(true);
        if (InputHelper.readYesOrNo("\n  Add this Signature Build to your order?")) {
            order.addBuild(chosen);
            System.out.println("  Signature Build added");
        }
    }

    private String fmt(Component c) { return String.format("$%.2f", c.getExtraCost()); }
    private double rgbCost(PCType size) {
        return switch (size) { case STARTER -> 29.99; case REGULAR -> 39.99; case ENTHUSIAST -> 49.99; case ULTRA -> 79.99; };
    }
}