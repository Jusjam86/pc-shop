package com.pluralsight;

import com.pluralsight.UI.HomeScreen;
import com.pluralsight.UI.LoadingScreen;
import com.pluralsight.util.OrderHelper;

public class Main {
    static void main() {
        LoadingScreen.show();
        new HomeScreen(new OrderHelper()).show();
    }

}
