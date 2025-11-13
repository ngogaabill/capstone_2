package com.pluralsight.models;

import java.util.ArrayList;

public class Taco implements OrderedItem {

    private String tortillaType;
    private String tacoSize;
    private boolean deepFried;
    private double tacoSizePrice;
    private double premiumPrice;
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

    public boolean isDeepFried(boolean b) {
        return deepFried;
    }

    @Override
    public String description() {
        if (deepFried) {
            return "Size: " + getTacoSize() + " - Tortilla: " + getTortillaType() + "\nToppings:\n" + getToppings() + "Deep Fried";
        }
        return "Size: " + getTacoSize() + " - Tortilla: " + getTortillaType() + "\nToppings:\n" + getToppings();
    }

    public ArrayList<Toppings> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        return tacoSizePrice + premiumPrice;
    }

    public void getTacoSizePrice(double v) {
        this.tacoSizePrice = v;
    }

    public void addTopping(Toppings sauce) {
        toppings.add(sauce);
    }

    @Override
    public String toString() {
        return description() + "$" + getPrice() + "\n";
    }

    public void addToppingPrice(double total) {
        premiumPrice += total;
    }
}
