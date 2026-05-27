package com.pluralsight.catalog;

import com.pluralsight.models.SignatureBuild;
import com.pluralsight.models.components.*;
import com.pluralsight.models.enums.FormFactor;
import com.pluralsight.models.enums.PCType;
import com.pluralsight.models.peripherals.*;
import java.util.List;

// could've added the motherboards but there was too many compared to other components,
// it'll be connected to the formfactor class

// this is the shop's full catalog of all it's products
// all methods return unmodifiable lists by List.of()
// pricing for ram, storage, & cooling are $0 because they're included into the build base price
public class PartsCatalog {

    // CPU list + tier list
    public static List<CPU> getCPUs(PCType type) {
        // extra is overclocked related to PCType selected
        double extra = switch (type) {
            case STARTER -> 50.00;
            case REGULAR -> 75.00;
            case ENTHUSIAST -> 100.00;
            case ULTRA -> 150.00;
        };
        return List.of(
                new CPU("Intel Core i3-14100", 0, extra, "LGA1700", 4),
                new CPU("Intel Core i5-14600K", 0, extra, "LGA1700", 14),
                new CPU("Intel Core i7-14700K", 0, extra, "LGA1700", 20),
                new CPU("Intel Core i9-13900KS", 0, extra, "LGA1700", 24),
                new CPU("Intel Core i9-14900KS", 0, extra, "LGA1700", 24),
                new CPU("AMD Ryzen 5 7600X", 0, extra, "AM5", 8),
                new CPU("AMD Ryzen 7 7800X3D", 0, extra, "AM5", 16),
                new CPU("AMD Ryzen 9 7900X", 0, extra, "AM5", 12),
                new CPU("AMD Ryzen 7 9800X3D", 0, extra, "AM5", 16),
                new CPU("AMD Ryzen 9 9950X3D2", 0, extra, "AM5", 16)
        );
    }

    // GPU list + tier list
    public static List<GPU> getGPUs(PCType type) {
        // some GPU's start with the 2 fan versions that can upgrade to 3 fans when selecting enthusiast or ultra
        double base = switch (type) {
            case STARTER -> 50.00;
            case REGULAR -> 75.00;
            case ENTHUSIAST -> 100.00;
            case ULTRA -> 150.00;
        };
        // this is for overclocking for better performance
        double extra = switch (type) {
            case STARTER -> 30.00;
            case REGULAR -> 45.00;
            case ENTHUSIAST -> 85.00;
            case ULTRA -> 100.00;
        };
        return List.of(
                new GPU("NVIDIA RTX 4060 Ti", 0, extra, 8),
                new GPU("NVIDIA RTX 4070 Super", 0, extra, 12),
                new GPU("NVIDIA RTX 4070 Ti Super", 0, extra, 16),
                new GPU("NVIDIA RTX 4080 Super", 0, extra, 16),
                new GPU("NVIDIA RTX 4090", 0, extra, 24),
                new GPU("NVIDIA RTX 5060 Ti", 0, extra, 16),
                new GPU("NVIDIA RTX 5070 Ti", 0, extra, 16),
                new GPU("NVIDIA RTX 5080 Super", 0, extra, 16),
                new GPU("NVIDIA RTX 5090", 0, extra, 32),
                new GPU("AMD RX 7600", 0, extra, 8),
                new GPU("AMD RX 7700 XT", 0, extra, 12),
                new GPU("AMD RX 7800 XT", 0, extra, 16),
                new GPU("AMD RX 7900 XT/ GRE", 0, extra, 20),
                new GPU("AMD RX 7900 XTX", 0, extra, 24),
                new GPU("AMD RX 9060 XT", 0, extra, 16),
                new GPU("AMD RX 9070 XT", 0, extra, 16)
        );
    }

    // PSU list + tier list
    public static List<PSU> getPSUs(PCType type) {
        double base = switch (type) {
            case STARTER -> 65.00;
            case REGULAR -> 85.00;
            case ENTHUSIAST -> 120.00;
            case ULTRA -> 160.00;
        };
        return List.of(
                new PSU("MSI MAG A850GL", 0, "80+ Gold", 850),
                new PSU("MSI MPG 1000W", 0, "80+ Gold", 1000),
                new PSU("MSI MEG Ai1300P", 0, "80+ Platinum", 1300),
                new PSU("MSI MEG Ai1600T", 0, "80+ Titanium", 1600),
                new PSU("Seasonic Focus GX850W", 0, "80+ Gold", 850),
                new PSU("Seasonic Vertex GX 1000W", 0, "80+ Gold", 1000),
                new PSU("Seasonic Vertex PX 1200W", 0, "80+ Platinum", 1200),
                new PSU("Corsair RM850e", 0, "80+ Gold", 850),
                new PSU("Corsair RM1000x", 0, "80+ Gold", 1000),
                new PSU("Corsair HX1500i", 0, "80+ Platinum", 1500),
                new PSU("Lian Li EDGE850W", 0, "80+ Gold", 1000),
                new PSU("Lian Li RS1000W", 0, "80+ Gold", 1000),
                new PSU("Lian Li SX1200W", 0, "80+ Platinum", 1200)
        );
    }

    // RAM list
    public static List<RAM> getRAMOptions() {
        return List.of(
                new RAM("(2x8GB) DDR5-4800", 0, 8, 4800),
                new RAM("(2x16GB) DDR5-6000", 0, 16, 6000),
                new RAM("(2x32GB) DDR5-6000", 0, 32, 6000),
                new RAM("(2x64GB) DDR5-5600", 0, 64, 5600),
                new RAM("(2x8GB) DDR4-3200", 0, 8, 3200),
                new RAM("(2x16GB) DDR4-3600", 0, 16, 3600),
                new RAM("(2x32GB) DDR4-3600", 0, 32, 3600)
        );
    }

    // Storage list
    public static List<Storage> getStorageOptions() {
        return List.of(
                new Storage("500GB NVMe SSD", 0, "NVMe", 500),
                new Storage("1TB NVMe SSD", 0, "NVMe", 1000),
                new Storage("2TB NVMe SSD", 0, "NVMe", 2000),
                new Storage("4TB NVMe SSD", 0, "NVMe", 4000),
                new Storage("1TB SATA SSD", 0, "SSD", 1000),
                new Storage("2TB SATA SSD", 0, "SSD", 2000),
                new Storage("1TB HDD", 0, "HDD", 1000),
                new Storage("2TB HDD", 0, "HDD", 2000),
                new Storage("4TB HDD", 0, "HDD", 4000)
        );
    }
    // Cooling list
    public static List<Cooling> getCoolingOptions() {
        return List.of(
                new Cooling("Peerless Assassin 120 V3",           0, "Air",   75),
                new Cooling("Cooler Master Hyper 212",    0, "Air",   150),
                new Cooling("Noctua NH-D15",              0, "Air",   250),
                new Cooling("NZXT Kraken 120 AIO",        0, "AIO 120mm", 150),
                new Cooling("Thermalright Trofeo Vision 360mm AIO",    0, "AIO 360mm", 280),
                new Cooling("TRYX Stage 360mm AIO",  0, "AIO 360mm", 280),
                new Cooling("Lian Li Galahad 240mm AIO",  0, "AI0 240mm", 250)
        );
    }
    // Mouse list
    public static List<Mouse> getMice() {
        return List.of(
                new Mouse("DeathAdder V3",    59.99, "Razer",    30000, false),
                new Mouse("G502 X Plus",      89.99, "Logitech", 25600, true),
                new Mouse("Model O Minus",    39.99, "Glorious", 19000, false),
                new Mouse("Basilisk V3 Pro", 129.99, "Razer",    30000, true)
        );
    }
    // Mouse Pad
    public static List<MousePad> getMousePads() {
        return List.of(
                new MousePad("Strider Medium", 19.99, "SteelSeries", "Medium (320x270mm)"),
                new MousePad("Strider XL",     39.99, "SteelSeries", "XL (900x300mm)"),
                new MousePad("Desk Mat Pro",   49.99, "Corsair",     "Desk Mat (900x400mm)"),
                new MousePad("Fury S Speed",   14.99, "HyperX",      "Small (290x240mm)")
        );
    }
    // Monitors list
    public static List<Monitor> getMonitors() {
        return List.of(
                new Monitor("Odyssey G5",        329.99, "Samsung",   27.0, 165, "1440p",     "VA"),
                new Monitor("IPS 4K Pro",        499.99, "LG",        27.0, 144, "4K",        "IPS"),
                new Monitor("ROG Swift PG279QM", 599.99, "ASUS",      27.0, 240, "1440p",     "IPS"),
                new Monitor("Curved FHD 24\"",   199.99, "AOC",       24.0, 165, "1080p",     "VA"),
                new Monitor("AW3423DW",          999.99, "Alienware", 34.0, 165, "3440x1440", "QD-OLED")
        );
    }
    // Headset list
    public static List<Headset> getHeadsets() {
        return List.of(
                new Headset("Cloud II Wireless", 149.99, "HyperX",      true,  true),
                new Headset("Arctis Nova Pro",   249.99, "SteelSeries", true,  true),
                new Headset("BlackShark V2",      79.99, "Razer",       true,  false),
                new Headset("G435",               79.99, "Logitech",    false, true)
        );
    }
    // Signature/ Pre-Builds
    // it'll be here because its part of the catalog, and it's easier here since I can just list the pre-builds
    public static List<SignatureBuild> getSignatureBuilds() {
        return List.of(
                new SignatureBuild("The Budget Build", "Great everyday performance on a budget that doesn't break the bank",
                        PCType.REGULAR, FormFactor.MID_TOWER,
                        List.of(new CPU("AMD Ryzen 5 7600X", 0, 50, "AM5", 8),
                                new GPU("AMD RX 7600", 75, 30, 8),
                                new PSU("Corsair RM850", 85, "80+ Gold", 850),
                                new RAM("(2x16GB) DDR5-6000", 0, 16, 6000),
                                new Storage("500GB NVMe SSD", 0, "NVMe", 500),
                                new Cooling("Cooler Master Hyper 212", 0, "Air", 150)), false),

                new SignatureBuild("AMD Starter Build", "This build sets the standard for performance in gaming and ok in workloads, it isn't high specs but it gets the job done better than a budget build",
                        PCType.STARTER, FormFactor.MICRO_ATX,
                        List.of(new CPU("AMD Ryzen 9 7900X", 50, 45, "AM5", 12),
                                new GPU("AMD RX 7700X", 75, 45, 12),
                                new PSU("MSI MAG A850GL", 50, "80+ Gold", 850),
                                new RAM("(2x32GB) DDR5-6000",0, 32, 6000),
                                new Storage("2TB NVMe SSD", 0, "NVMe", 2000),
                                new Cooling("Lian Li Galahad 240mm AIO",  0, "AIO 240mm", 250)), false),

                new SignatureBuild("Intel Starter Build", "This build sets the standard for performance in workloads and ok in gaming, it isn't high specs but it gets the job done better than a budget build",
                        PCType.STARTER, FormFactor.MICRO_ATX,
                        List.of(new CPU("Intel Core i7-14700K", 50, 45, "LGA1700", 20),
                                new GPU("NVIDIA RTX 4070 Ti Super", 75, 45, 16),
                                new PSU("MSI MAG A850GL", 50, "80+ Gold", 850),
                                new RAM("(2x32GB) DDR5-6000",0, 32, 6000),
                                new Storage("2TB NVMe SSD", 0, "NVMe", 2000),
                                new Cooling("Lian Li Galahad 240mm AIO",  0, "AIO 240mm", 250)), false),

                new SignatureBuild("The Gamer", "Optimized for 1440p gaming — fast CPU, powerful GPU, 64GB RAM",
                        PCType.ENTHUSIAST, FormFactor.MID_TOWER,
                        List.of(new CPU("AMD Ryzen 7 9800X3D", 100, 100, "AM5", 16),
                                new GPU("AMD RX 7900 XTX", 100, 85, 24),
                                new PSU("Lian Li RS1000W", 0, "80+ Gold", 1000),
                                new RAM("(2x32GB) DDR5-6000", 0, 32, 6000),
                                new Storage("2TB NVMe SSD", 0, "NVMe", 2000),
                                new Cooling("TRYX Stage 360mm AIO",  0, "AIO 360mm", 280)), true),

                new SignatureBuild("The Creator", "Built for video editing & 3D rendering — max RAM, pro GPU",
                        PCType.ENTHUSIAST, FormFactor.MID_TOWER,
                        List.of(new CPU("Intel Core i9-13900KS", 100, 100, "LGA1700", 24),
                                new GPU("NVIDIA RTX 4090", 100, 85, 24),
                                new PSU("Seasonic Vertex GX 1000W", 0, "80+ Gold", 1000),
                                new RAM("(2x64GB) DDR5-5600", 0, 64, 5600),
                                new Storage("4TB NVMe SSD", 0, "NVMe", 4000),
                                new Cooling("Thermalright Trofeo Vision 360mm AIO",    0, "AIO 360mm", 280)), true),

                new SignatureBuild("Shattered Reality","The Ultimate gaming PC, the best components for gaming",
                        PCType.ULTRA, FormFactor.FULL_TOWER,
                        List.of(new CPU("AMD Ryzen 9 9950X3D2", 150, 150, "AM5", 16),
                                new GPU("AMD RX 9070 XT", 150, 100, 16),
                                new PSU("Corsair HX1500i", 0, "80+ Platinum", 1500),
                                new RAM("(2x64GB) DDR5-5600", 0, 64, 5600),
                                new Storage("4TB NVMe SSD", 0, "NVMe", 4000),
                                new Cooling("TRYX Stage 360mm AIO",  0, "AIO 360mm", 280)), true),

                new SignatureBuild("God's Creation", "The Ulitmate workstation PC, the best components for editing & rendering",
                        PCType.ULTRA, FormFactor.FULL_TOWER,
                        List.of(new CPU("Intel Core i9-14900KS", 150, 150, "LGA1700", 24),
                                new GPU("NVIDIA RTX 5090", 150, 100, 32),
                                new PSU("MSI MEG Ai1600T", 0, "80+ Titanium", 1600),
                                new RAM("(2x64GB) DDR5-5600", 0, 64, 5600),
                                new Storage("4TB NVMe SSD", 0, "NVMe", 4000),
                                new Cooling("Thermalright Trofeo Vision 360mm AIO",    0, "AIO 360mm", 280)), true)
        );
    }
}
