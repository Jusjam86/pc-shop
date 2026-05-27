package com.pluralsight.models.components;

public class PSU extends Component {

    // variables
    private final String efficiencyRating; // 80+ Bronze & 80+ Silver aren't frequent anymore, 80+ Gold, 80+ Platinum
    private final int wattage;

    // constructor
    public PSU(String name, double basePrice, String efficiencyRating, int wattage) {
        super(name, basePrice, true, 0.0);
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
    }
    // getters
    public String getEfficiencyRating() {return efficiencyRating;}
    public int getWattage() {return wattage;}

    // methods
    @Override
    public String getCategory() {return "PSU";}

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " +  efficiencyRating + " | " + wattage;
    }
}
