// File: Main.java
import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize products
        Product gadgetLaptop = new Product("ITM001", "GalaxyBook Pro", 1249.99);
        Product gadgetPhone = new Product("ITM002", "Pixel Ultra 6", 899.99);
        Product gadgetHeadphones = new Product("ITM003", "SoundMax Wireless", 129.50);

        // Create customer profile
        Customer shopperProfile = new Customer("USR1001", "Tatenda Katema");

        // Display product catalog
        System.out.println("Welcome to DigiZone Online Store!");
        System.out.println("Available Products:");
        System.out.println(gadgetLaptop);
        System.out.println(gadgetPhone);
        System.out.println(gadgetHeadphones);
        System.out.println();

        // Simulate cart actions
        shopperProfile.addItemToBasket(gadgetLaptop);
        shopperProfile.addItemToBasket(gadgetHeadphones);

        // Optional: Remove an item if needed
        // shopperProfile.removeItemFromBasket(gadgetPhone);

        // Display cart total
        System.out.printf("Total in Cart for %s: $%.2f\n", shopperProfile.getFullName(), shopperProfile.calculateBasketTotal());
        System.out.println();

        // Place an order
        Order completedOrder = new Order(shopperProfile, shopperProfile.getShoppingBasket());
        shopperProfile.clearBasket();

        // Output the order summary
        System.out.println("=== Order Confirmation ===");
        System.out.println(completedOrder.generateOrderSummary());
    }
}

