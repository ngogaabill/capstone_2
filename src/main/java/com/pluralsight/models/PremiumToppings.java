package com.pluralsight.models;

public class PremiumToppings extends Toppings {
    private double price;
    private double extraPrice;

    public PremiumToppings(String name, String type, boolean isExtra, double price, double extraPrice) {
        super(name, type, isExtra);
        this.price = price;
        this.extraPrice = price;
    }

    @Override
    public double getPrice() {
        if (isExtra()) {
            return price + extraPrice;
        }
        return price;
    }

    @Override
    public String toString() {
        if (isExtra()) {
            return getName() + "Extra " + String.format("%.2f", getPrice());
        }
        return getName() + String.format("%.2f", getPrice());
    }
}
