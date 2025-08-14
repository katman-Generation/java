// File: com/ecommerce/orders/Order.java
package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.List;
import java.util.UUID;

public class Order {
    private String transactionId;
    private Customer purchaser;
    private List<Product> purchasedItems;
    private double purchaseTotal;
    private String currentStatus;

    public Order(Customer purchaser, List<Product> purchasedItems) {
        this.transactionId = UUID.randomUUID().toString();
        this.purchaser = purchaser;
        this.purchasedItems = purchasedItems;
        this.purchaseTotal = calculatePurchaseTotal();
        this.currentStatus = "Processing";
    }

    private double calculatePurchaseTotal() {
        double totalValue = 0;
        for (Product item : purchasedItems) {
            totalValue += item.getItemPrice();
        }
        return totalValue;
    }

    public void updateOrderStatus(String newStatus) {
        this.currentStatus = newStatus;
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Transaction ID: ").append(transactionId).append("\n");
        summary.append("Customer Name: ").append(purchaser.getFullName()).append("\n");
        summary.append("Ordered Items:\n");
        for (Product item : purchasedItems) {
            summary.append("- ").append(item.toString()).append("\n");
        }
        summary.append("Total Amount: $").append(purchaseTotal).append("\n");
        summary.append("Order Status: ").append(currentStatus).append("\n");
        return summary.toString();
    }
}

