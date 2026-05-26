package com.pluralsight.models.components;

public class CPU extends Component {

    // variables
    private final String socketType;
    private final int cores;

    // constructor
    public CPU(String name, double basePrice, double extraCost, String socketType, int cores) {
        super(name, basePrice, true, extraCost);
        this.socketType = socketType;
        this.cores = cores;
    }

    // getters
    public String getSocketType() { return socketType; }
    public int getCores() { return cores; }

    // methods
    @Override
    public String getCategory() { return "CPU"; }

    @Override
    public String getOrderSummary(){
        return super.getOrderSummary() + " | " + cores + "-core, Socket " + socketType;
    }
}
