package com.pluralsight.util;

import com.pluralsight.models.PCAccessory;
import com.pluralsight.models.PCBuild;
import com.pluralsight.models.components.GPU;
import com.pluralsight.models.enums.FormFactor;
import com.pluralsight.models.enums.PCType;
import com.pluralsight.models.peripherals.Mouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderHelperTest {

    private OrderHelper order;
    private PCBuild build;
    private Mouse mouse;
    private PCAccessory accessory;

    @BeforeEach
    void setUp() {
        order = new OrderHelper();

        build = new PCBuild(PCType.STARTER, FormFactor.MID_TOWER);
        build.addComponent(new GPU("RTX 4060", 50.0, 30.0, 8)); // adds $50

        mouse      = new Mouse("DeathAdder V3", 69.99, "Razer", 30000, false);
        accessory  = new PCAccessory(PCAccessory.AccessoryType.USB_HUB); // $14.99
    }

    @Test
    void order_isEmpty_whenNothingAdded() {
        assertTrue(order.isEmpty());
    }

    @Test
    void order_isValid_withAtLeastOneBuild() {
        order.addBuild(build);
        assertTrue(order.isValid());
    }

    @Test
    void total_includesBuildPrice() {
        order.addBuild(build);
        // STARTER 379.99 + GPU base 50.00 = 429.99
        assertEquals(429.99, order.getTotal(), 0.001);
    }
}