package com.pluralsight.ui;

import com.pluralsight.Main;
import com.pluralsight.models.ChipsAndSalsa;
import com.pluralsight.models.Drink;
import com.pluralsight.models.Order;
import com.pluralsight.util.Receipt;

import java.util.Scanner;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    static Receipt receipt = new Receipt();

    public void menu() {

        boolean exit = false;
        while (!exit) {
            System.out.print("""
                    1) New Order
                    0) Exit (closes the application) 
                    Choice: """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.err.println("Wrong Choice, Try Again\n");
            }
        }
    }

    public void addOrder() {
        Order newOrder = new Order();
        boolean exit = false;

        while (!exit) {
            System.out.print("""
                    -----|Order Screen|-----
                    Option 1) Add Taco
                    Option 2) Add Drink
                    Option 3) Add Chip&Salsa
                    Option 4) Checkout
                    Option 0) Cancel Order (deletes order, returns to Home)
                    Choice: """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addTaco();
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
                    System.err.println("Wrong Choice, Try Again\n");

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
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1 || choice == 2 || choice == 3) {
            ChipsAndSalsa cp = new ChipsAndSalsa(choice);
            order.addItem(cp);
            System.out.println("Added: " + cp.description() + " - $" +
                    String.format("%.2f", cp.getPrice()));
        } else System.err.println("Wrong choice");
    }

    private void addTaco() {
    }

    private void addDrink(Order order) {

        System.out.print("""
                ----Choose Your Size----
                    S - Small  ($2.00)
                    M - Medium ($2.50)
                    L - Large  ($3.00)
                 Choice (S/M/L): """);

        String choice = scanner.nextLine().trim().toUpperCase();
        System.out.println("""
                Choose Letter Matching Flavor
                Strawberry
                Chocolate
                Vanilla""");
        String flavor = scanner.nextLine().trim().toUpperCase();
        if (choice.matches("[SML]") && flavor.matches("[SCV]")) {
            Drink drink = new Drink(choice, flavor);
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
        } else {
            System.out.println("Order not confirmed.");
        }
    }

}
