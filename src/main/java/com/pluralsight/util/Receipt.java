package com.pluralsight.util;

import com.pluralsight.models.Order;
import com.pluralsight.models.OrderedItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Receipt {

    /**
     * Write Order To Receipt File
     */
    public void printReceipt(Order order) {
        String folderPath = "src/main/resources/receipts";
        String receiptFileName = folderPath + "/" + generateTimestamp() + ".txt";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String date = String.valueOf(LocalDate.now());
        String time = LocalTime.now().format(dateTimeFormatter);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(String.valueOf(receiptFileName)))) {
            bf.write("----------TACO GALAXY -----------\n");
            bf.write("        ORDER SUMMARY       \n");
            bf.write("Date: " + date + " Time: " + time);
            bf.write("\n---------------------------------\n");
            bf.write(String.format("%-20s %10s\n", "Description", "Price"));
            bf.write("---------------------------------\n");

            for (OrderedItem c : order.getAllItems()) {
                bf.write(String.valueOf(c));
            }
            bf.write("---------------------------------\n");
            bf.write(String.format("Total Cost: $%20.2f", Order.getTotalPrice()));
            bf.write("\n THANKS FOR SHOPPING :)");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the D&T stamp for receipt filename creation
     *
     * @return Date n Time String
     */
    public static String generateTimestamp() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        return currentTime.format(dateTimeFormatter);

    }
}
