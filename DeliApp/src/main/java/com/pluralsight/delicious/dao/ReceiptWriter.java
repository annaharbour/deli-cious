package com.pluralsight.delicious.dao;

import com.pluralsight.delicious.models.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmmss");
    private static final String RECEIPTS_FOLDER = "receipts";

    private final Order currentOrder;

    public ReceiptWriter(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void writeLines() {
        try {
            Path folderPath = Paths.get(RECEIPTS_FOLDER);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            String fileName = currentOrder.getTimeStamp().format(DATE_FORMAT) + "-" +
                    currentOrder.getTimeStamp().format(TIME_FORMAT) + ".txt";
            Path filePath = folderPath.resolve(fileName);

            try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(filePath))) {
                pw.printf("Order Timestamp: %s\n",
                        currentOrder.getTimeStamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                pw.println("----------------------------------------------------");
                pw.printf("Customer Name: %s\n", currentOrder.getCustomer().getCustomerName());
                pw.println("----------------------------------------------------");
                pw.println("ORDER SUMMARY");
                currentOrder.getOrderItems().forEach(item -> {
                    pw.println(item.getReceiptLine());
                });
                pw.println("----------------------------------------------------");
                String total = String.format("$%.2f", currentOrder.getPrice());
                pw.printf("%-30s%10s", "ORDER TOTAL:", total);
            }
            System.out.println("Receipt written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}
