package com.pluralsight.ui;

import com.pluralsight.Main;
import com.pluralsight.models.*;
import com.pluralsight.util.Receipt;

import java.util.ArrayList;
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
                taco.gettacoSizePrice(3.50);
            }
            case 2 -> {
                taco.setTacoSize("3-Taco");
                taco.gettacoSizePrice(9.00);
            }
            case 3 -> {
                taco.setTacoSize("Burrito");
                taco.gettacoSizePrice(8.50);
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
        System.out.println("""
                Toppings Options
                   1) Meat
                   2) Cheese
                   3) Other toppings
                   4) Select sauces:
                Choice: """);
        int toppingsOption = getInput();
        switch (toppingsOption) {
            case 1:
                addMeat(taco);
                break;
            case 2:
                addCheese();
                break;
            case 3:
                regularToppings(taco);
                break;
            case 4:
                selectSauces(taco);
                break;
        }
//        System.out.println("Do you want your Taco Deep Fried?(Y/N)");
//        String deepFried = scanner.nextLine().trim().toUpperCase();
//        if (deepFried.equals("Y")) {
//            taco.isDeepFried(true);
//        }
        order.addItem(taco);
        System.out.println("Added: " + taco.description() + "- $" + String.format("%.2f", taco.getPrice()));

    }

    private void addCheese() {

    }

    private void addMeat(Taco taco) {
        String[] meats = {"carne asada", "al pastor", "carnitas", "pollo", "chorizo", "pescado"};
        System.out.println("""
                 Meat Options:
                      1) carne asada
                      2) al pastor
                      3) carnitas
                      4) pollo
                      5) chorizo
                      6) pescado
                Choice: """);
        int meatChoice = getInput();
        if (meatChoice >= 1 && meatChoice <= 6) {
            String meatName = meats[meatChoice - 1];
            //do you want extra
            System.out.println("Do you want extra meat?(Y/N)");
            String extraMeat = scanner.nextLine().trim().toUpperCase();
            boolean isExtra = extraMeat.equals("Y");//If the answer is y then true
            double meatPrice = 0.0;
            double extraPrice = 0.0;
            String tacoSize = taco.getTacoSize();
            if (tacoSize.equals("Single")) {
                meatPrice = 1.0;
                extraPrice = 0.50;

            } else if (tacoSize.equals("3-Taco")) {
                meatPrice = 2.0;
                extraPrice = 1.00;
            } else if (tacoSize.equals("Burrito")) {
                meatPrice = 3.0;
                extraPrice = 1.50;
            }
            //add meat topping
            PremiumToppings meat = new PremiumToppings(meatName, Toppings.MEAT, isExtra, meatPrice, extraPrice);
            taco.addTopping(meat);
            double totalMeatPrice = 0.0;
            if (isExtra) {
                totalMeatPrice = meatPrice + extraPrice;
            } else {
                totalMeatPrice = meatPrice;
            }
            String msg = "";
            if (isExtra) {
                msg = " Extra";
            }
            System.out.printf("Added: %s%s - $%.2f%n", meatName, msg, totalMeatPrice);
            taco.addToppingPrice(totalMeatPrice);//send the total meatPrice

        } else {
            System.err.println("Wrong choice");
        }

    }

    private void selectSauces(Taco taco) {
        String[] sauces = {"Sauces", "salsa verde", "salsa roja", "chipotle", "habanero", "mild", "extra hot"};
        System.out.println("""
                Sauce Toppings Menu
                        1- salsa verde
                        2- salsa roja
                        3- chipotle
                        4- habanero
                        5- mild
                        6- extra hot
                
                 Choice(1/2/3): """);
        String sauceChoices = scanner.nextLine().trim();
        String[] selectedSauce = sauceChoices.split("\\/");

        for (String s : selectedSauce) {
            try {
                int index = Integer.parseInt(s.trim());
                if (index >= 1 && index <= 6) {
                    Toppings sauce = new Toppings(sauces[index - 1], Toppings.SAUCE, false);
                    taco.addTopping(sauce);

                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong entry" + selectedSauce);
            }
        }
    }

    private void regularToppings(Taco taco) {
        String[] regualarTopps = {"lettuce", "cilantro", "onions ", "tomatoes", "jalapeños", "radishes", "pico de", "guacamole", "corn"};
        System.out.println("""
                Regular Toppings menu:
                      1- lettuce 
                      2- cilantro 
                      3- onions
                      4- tomatoes 
                      5- jalapeños 
                      6- radishes
                      7- pico de
                      8- guacamole
                      9- corn
                Choice(i.e 1/2/3)""");
        String choices = scanner.nextLine().trim();
        String[] toppingChoices = choices.split("\\/"); //["2","4"] -> [2,4]

        //convert the string input into a array of numbers
        for (String c : toppingChoices) {
            try {
                int index = Integer.parseInt(c.trim());
                if (index >= 1 && index <= 9) {
                    Toppings regular = new Toppings(regualarTopps[index - 1], Toppings.REGULAR, false);
                    System.out.println("Added Topping: " + regular.toString());
                    taco.addTopping(regular);

                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong entry" + toppingChoices);
            }
        }
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

        List<OrderedItem> items = order.getAllItems();
        displayOrderSummary(items);
        System.out.print("Confirm order? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            receipt.printReceipt(order);//error it's not creating the file right away its waiting untill the program is closed
            System.out.println("Order confirmed!");
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

    public void addOtherItem() {

    }
}