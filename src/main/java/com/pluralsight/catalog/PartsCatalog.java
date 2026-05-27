package com.pluralsight.catalog;

import com.pluralsight.models.components.*;
import com.pluralsight.models.enums.PCType;
import java.util.List;

// this is the shop's full catalog of all it's products
// all methods return unmodifiable lists by List.of()
// pricing for ram, storage, & cooling are $0 because they're included into the build base price

public class PartsCatalog {

    // CPU list + tier list
    public static List<CPU> getCPUs(PCType type) {
        // extra is overclocked related to PCType selected
        double extra = switch (type){
            case STARTER -> 50.00; case REGULAR -> 75.00; case ENTHUSIAST -> 100.00; case ULTRA -> 150.00;
        };
        return List.of(
                new CPU("Intel Core i3-14100",  0, extra, "LGA1700",  4),
                new CPU("Intel Core i5-14600K", 0, extra, "LGA1700", 14),
                new CPU("Intel Core i7-14700K", 0, extra, "LGA1700", 20),
                new CPU("Intel Core i9-13900KS", 0, extra, "LGA1700", 24),
                new CPU("Intel Core i9-14900KS", 0, extra, "LGA1700", 24),
                new CPU("AMD Ryzen 5 7600X",    0, extra, "AM5",      6),
                new CPU("AMD Ryzen 7 7800X3D",    0, extra, "AM5",      8),
                new CPU("AMD Ryzen 9 7900X",    0, extra, "AM5",      12),
                new CPU("AMD Ryzen 7 9800X3D",    0, extra, "AM5",      8),
                new CPU("AMD Ryzen 9 9950X3D2",    0, extra, "AM5",     16)
        );
    }
    // GPU list + tier list
    public static List<GPU> getGPUs(PCType type) {
        // some GPU's start with the 2 fan versions that can upgrade to 3 fans when selecting enthusiast or ultra
      double base = switch (type) {
            case STARTER -> 50.00; case REGULAR -> 75.00; case ENTHUSIAST -> 100.00; case ULTRA -> 150.00;
      };
      // this is for overclocking for better performance
      double extra = switch (type)  {
            case STARTER -> 30.00; case REGULAR -> 45.00; case ENTHUSIAST -> 85.00; case ULTRA -> 100.00;
      };
      return List.of(
              new GPU("NVIDIA RTX 4060 Ti",     0, extra,  8),
              new GPU("NVIDIA RTX 4070 Super",      0, extra, 12),
              new GPU("NVIDIA RTX 4070 Ti Super",       0, extra,  16),
              new GPU("NVIDIA RTX 4080 Super",      0, extra, 16),
              new GPU("NVIDIA RTX 4090",        0, extra,  24),
              new GPU("NVIDIA RTX 5060 Ti",     0, extra,  16),
              new GPU("NVIDIA RTX 5070 Ti",     0, extra, 16),
              new GPU("NVIDIA RTX 5080 Super",      0, extra, 16),
              new GPU("NVIDIA RTX 5090",        0, extra,  32),
              new GPU("AMD RX 7600",        0, extra,  8),
              new GPU("AMD RX 7700 XT",     0, extra,  12),
              new GPU("AMD RX 7800 XT",     0, extra, 16),
              new GPU("AMD RX 7900 XT/ GRE",        0, extra,  20),
              new GPU("AMD RX 7900 XTX",        0, extra, 24),
              new GPU("AMD RX 9060 XT",     0, extra, 16),
              new GPU("AMD RX 9070 XT",     0, extra, 16)
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
                new PSU("Seasonic Focus GX850W ", 0, "80+ Gold", 850),
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
                new RAM("x2 8GB DDR5-4800", 0, 8, 4800),
                new RAM("x2 16GB DDR5-6000", 0, 16, 6000),
                new RAM("x2 32GB DDR5-6000", 0, 32, 6000),
                new RAM("x2 64GB DDR5-5600", 0, 64, 5600),
                new RAM("x2 8GB DDR4-3200", 0, 8, 3200),
                new RAM("x2 16GB DDR4-3600", 0, 16, 3600),
                new RAM("x2 32GB DDR4-3600", 0, 32, 3600)
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
}
