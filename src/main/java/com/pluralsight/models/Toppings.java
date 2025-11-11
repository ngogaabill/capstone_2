package com.pluralsight.models;

public class Toppings {
    private String name;
    private String type;
    private boolean isExtra;

    // Constants for topping types
    public static final String MEAT = "MEAT";
    public static final String CHEESE = "CHEESE";
    public static final String REGULAR = "REGULAR";
    public static final String SAUCE = "SAUCE";
    public static final String SIDE = "SIDE";


    public Toppings(String name, String type, boolean isExtra) {
        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    public String getName() {
        return name;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return 0;
    }
    public String getDescription(){
        return "Topping " + name + '\'' +
                ", type='" + type + '\'' ;

    }

    @Override
    public String toString() {
        return getDescription() + getPrice();
    }
}
