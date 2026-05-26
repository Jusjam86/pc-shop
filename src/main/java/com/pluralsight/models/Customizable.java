package com.pluralsight.models;

import com.pluralsight.models.components.Component;
import java.util.List;

// this is a contract for items that accept hardware upgrades like starter, regular, enthusiast, or ultra

public interface Customizable {
    void addComponent(Component component);
    List<Component> getComponents();
}
