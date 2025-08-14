// File: com/ecommerce/Customer.java
package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String userId;
    private String fullName;
    private List<Product> shoppingBasket;

    public Customer(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
        this.shoppingBasket = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void addItemToBasket(Product selectedItem) {
        shoppingBasket.add(selectedItem);
        System.out.println(selectedItem.getItemName() + " has been added to your basket.");
    }

    public void removeItemFromBasket(Product selectedItem) {
        if (shoppingBasket.remove(selectedItem)) {
            System.out.println(selectedItem.getItemName() + " has been removed from your basket.");
        } else {
            System.out.println("Item not found in your basket.");
        }
    }

    public double calculateBasketTotal() {
        double totalAmount = 0;
        for (Product item : shoppingBasket) {
            totalAmount += item.getItemPrice();
        }
        return totalAmount;
    }

    public List<Product> getShoppingBasket() {
        return shoppingBasket;
    }

    public void clearBasket() {
        shoppingBasket.clear();
    }
}

