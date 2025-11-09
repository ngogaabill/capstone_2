package com.pluralsight.models;

public class ChipsAndSalsa implements OrderedItem {
    private String salsaSauce;

    public ChipsAndSalsa(int sauceOption) {
        if (sauceOption == 1) {
            this.salsaSauce = "Birriera";
        } else if (sauceOption == 2) {
            this.salsaSauce = "Taco Nazo";
        } else this.salsaSauce = "Los Taco";
    }

    public String getSalsaSauce() {
        return this.salsaSauce;
    }

    @Override
    public String description() {
        return String.format("Chip&Salsa - %10s", getSalsaSauce());
    }

    @Override
    public double getPrice() {
        return 1.50;
    }

    @Override
    public String toString() {
        return String.format("%-20s %8s\n", description(), getPrice());
    }
}
