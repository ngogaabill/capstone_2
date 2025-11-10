package com.pluralsight.ui;

import com.pluralsight.Main;
import com.pluralsight.models.*;
import com.pluralsight.util.Receipt;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    static Receipt receipt = new Receipt();

    public void menu() {

        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1) New Order
                    0) Exit (closes the application) 
                    Choice: """);
            int choice = getInput();

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 0:
                    exit = true;
                    System.out.println("GoodBye, Thanks For Shopping with Taco Galaxy");
                    break;
                default:
                    System.err.println("Wrong Choice, Try Again");
            }
        }
    }

    public void addOrder() {
        Order newOrder = new Order();
        boolean exit = false;

        while (!exit) {
            System.out.println("""
                    -----|Order Screen|-----
                    Option 1) Add Taco
                    Option 2) Add Drink
                    Option 3) Add Chip&Salsa
                    Option 4) Checkout
                    Option 0) Cancel Order (deletes order, returns to Home)
                    Choice: """);
            int choice = getInput();

            switch (choice) {
                case 1:
                    addTaco(newOrder);
                    break;
                case 2:
                    addDrink(newOrder);
                    break;
                case 3:
                    addChipSalsa(newOrder);
                    break;
                case 4:
                    checkOut(newOrder);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.err.println("Wrong Choice, Try Again");

            }
        }
    }

    private void addChipSalsa(Order order) {
        System.out.println("""
                Chips&Salsa   ($1.50)
                salsa Sauce Option:
                                   1) Birrieraia Familia Castro
                                   2) Taco Nazo
                                   3) Los Tacos
                Choice: """);
        int salsaSauce = getInput();
        if (salsaSauce == 1 || salsaSauce == 2 || salsaSauce == 3) {
            ChipsAndSalsa cp = new ChipsAndSalsa(salsaSauce);
            order.addItem(cp);
            System.out.println("Added: " + cp.description() + " - $" +
                    String.format("%.2f", cp.getPrice()));
        } else System.err.println("Wrong choice");
    }

    private void addTaco(Order order) {
        //taco Size Menu
        System.out.println("""
                 Choose Taco Size
                   1) Single
                   2) 3-Taco
                   3) Burrito
                Choice(1/2/3):""");
        int tacoSize = getInput();
        //Create a taco
        Taco taco = new Taco();

        switch (tacoSize) {
            case 1 -> {
                taco.setTacoSize("Single");
                taco.tacoSizePrice(3.50);
            }
            case 2 -> {
                taco.setTacoSize("3-Taco");
                taco.tacoSizePrice(9.00);
            }
            case 3 -> {
                taco.setTacoSize("Burrito");
                taco.tacoSizePrice(8.50);
            }
            default -> System.err.println("Wrong Choice");
        }
        //tortilla type menu
        System.out.println("""
                Tortilla Type
                    1) Shell 
                    2) Corn 
                    3) Flour 
                    4) Hard shell
                    5) Bowl""");
        int tortillaType = getInput();
        switch (tortillaType) {
            case 1 -> taco.setTortillaType("Shell");
            case 2 -> taco.setTortillaType("Corn");
            case 3 -> taco.setTortillaType("Flour");
            case 4 -> taco.setTortillaType("Hard Shell");
            case 5 -> taco.setTortillaType("Bowl");
            default -> System.err.println("Wrong Choice");
        }
        //toppings


    }

    private void addDrink(Order order) {
        System.out.print("""
                ----Choose Your Size----
                    S - Small  ($2.00)
                    M - Medium ($2.50)
                    L - Large  ($3.00)
                 Choice (S/M/L): """);

        String drinkSize = scanner.nextLine().trim().toUpperCase();
        System.out.println("""
                Choose Letter Matching Flavor
                     (S)trawberry
                     (C)hocolate
                     (V)anilla
                Choice: """);
        String flavor = scanner.nextLine().trim().toUpperCase();
        if (drinkSize.matches("[SML]") && flavor.matches("[SCV]")) {
            Drink drink = new Drink(drinkSize, flavor);
            order.addItem(drink);
            System.out.println("Added: " + drink.description() + " - $" +
                    String.format("%.2f", drink.getPrice()));
        } else {
            System.out.println("Please choose Correct Options");
        }
    }

    public static void checkOut(Order order) {

        System.out.print("Confirm order? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            receipt.printReceipt(order);
            System.out.println("Order confirmed!");
            List<OrderedItem> items = order.getAllItems();
            displayOrderSummary(items);
        } else {
            System.out.println("Order not confirmed.");
        }
    }

    /**
     * Display Order Summary
     *
     * @param items
     */
    public static void displayOrderSummary(List<OrderedItem> items) {
        System.out.println("\n-----------ORDER SUMMARY ---------");
        System.out.printf("%-30s %5s%n", "Item", "Price");
        System.out.println("-----------------------------------");

        double total = 0.0;
        for (OrderedItem item : items) {
            System.out.printf("%-30s $%2.2f%n", item.description(), item.getPrice());
            total += item.getPrice();
        }
        System.out.println("-----------------------------------");
        System.out.printf("%-30s $%2.2f%n", "TOTAL", total);
        System.out.println("===================================");
    }

    /**
     * @return Value or -1 for type mismatch
     */
    private static int getInput() {
        if (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } else {
            scanner.nextLine();//consume Invalid input
            return -1;
        }
    }
}