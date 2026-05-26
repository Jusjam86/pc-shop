package com.pluralsight.models.components;

public class Cooling extends Component {

    // variables
    private final String coolingType;
    private final int tdpWatts;

    // constructor
    public Cooling(String name, double basePrice, String coolingType, int tdpWatts) {
        super(name, basePrice, false, 0.0);
        this.coolingType = coolingType;
        this.tdpWatts = tdpWatts;
    }

    // getters
    public String getCoolingType() { return coolingType; }
    public int getTdpWatts()       { return tdpWatts; }

    // methods
    @Override
    public String getCategory() { return "Cooling"; }

    @Override
    public String getOrderSummary() {
        return super.getOrderSummary() + " | " + coolingType + ", up to " + tdpWatts + "W TDP";
    }
}
