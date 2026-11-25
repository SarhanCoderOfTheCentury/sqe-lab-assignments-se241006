package com.sqelab.lab05;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {

    // One line in the cart: the unit price and how many pieces we have
    private static class Item {
        private final double price;
        private int quantity;

        Item(double price, int quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }

    private final Map<String, Item> items = new LinkedHashMap<>();
    private double discountPercent = 0;

    // REQ-1 and REQ-2
    public void addItem(String name, double price, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be more than 0");
        }

        Item existing = items.get(name);
        if (existing != null) {
            existing.quantity = existing.quantity + quantity;
        } else {
            items.put(name, new Item(price, quantity));
        }
    }

    // REQ-5
    public boolean removeItem(String name) {
        return items.remove(name) != null;
    }

    // REQ-4
    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 50) {
            throw new IllegalArgumentException("Discount must be between 0 and 50");
        }
        discountPercent = percent;
    }

    // REQ-3
    public double getTotal() {
        double sum = 0;
        for (Item item : items.values()) {
            sum = sum + item.price * item.quantity;
        }
        return sum - (sum * discountPercent / 100);
    }

    // REQ-6
    public int getItemCount() {
        int count = 0;
        for (Item item : items.values()) {
            count = count + item.quantity;
        }
        return count;
    }
}
