package com.pluralsight.models;

import java.util.ArrayList;

public class Taco implements OrderedItem {

    private String tortillaType;
    private String tacoSize;
    private boolean deepFried;
    private ArrayList<Toppings> toppings = new ArrayList<>();

    public Taco() {
    }

    public void setTortillaType(String tortillaType) {
        this.tortillaType = tortillaType;
    }

    public void setTacoSize(String tacoSize) {
        this.tacoSize = tacoSize;
    }

    public void setDeepFried(boolean deepFried) {
        this.deepFried = deepFried;
    }

    public Taco(String tortillaType, String tacoSize, boolean deepFried) {
        this.tortillaType = tortillaType;
        this.tacoSize = tacoSize;
        this.deepFried = deepFried;
    }

    public String getTortillaType() {
        return tortillaType;
    }

    public String getTacoSize() {
        return tacoSize;
    }

    public boolean isDeepFried() {
        return deepFried;
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public void tacoSizePrice(double v) {

    }
}
