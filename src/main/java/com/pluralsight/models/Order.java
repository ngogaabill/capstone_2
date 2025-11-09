package com.pluralsight.models;

import com.pluralsight.util.Receipt;

import java.util.ArrayList;
import java.util.List;

public class Order {
    static ArrayList<OrderedItem> cart;

    public Order() {
        this.cart = new ArrayList<>();
    }

    public void addItem(OrderedItem item) {
        cart.add(item);
    }

    public List<OrderedItem> getAllItem() {
        return cart;
    }

    public static double getTotalPrice() {
        double total = 0.0;
        for (OrderedItem c : cart) {
            total += c.getPrice();
        }
        return total;
    }

}
