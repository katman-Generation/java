// File: com/ecommerce/Product.java
package com.ecommerce;

public class Product {
    private String itemCode;
    private String itemName;
    private double itemPrice;

    public Product(String itemCode, String itemName, double itemPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return itemCode + " | " + itemName + " | $" + itemPrice;
    }
}

