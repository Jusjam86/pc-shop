package com.pluralsight;

import com.pluralsight.UI.HomeScreen;
import com.pluralsight.util.OrderHelper;

public class Main {
    static void main() {
        new HomeScreen(new OrderHelper()).show();
    }

}
