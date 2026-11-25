package com.sqelab.lab05;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ShoppingCartTest {

    private static final double DELTA = 0.001;

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart(); // new empty cart for every test
    }

    // ---------- REQ-1: adding items ----------

    @Test
    @DisplayName("REQ-1: adding one item puts it in the cart")
    void addItemStoresTheItem() {
        cart.addItem("Pen", 10, 2);

        assertAll("cart after adding one item",
                () -> assertEquals(2, cart.getItemCount()),
                () -> assertEquals(20.0, cart.getTotal(), DELTA));
    }

    @Test
    @DisplayName("REQ-1: adding the same item again increases its quantity")
    void addingSameItemIncreasesQuantity() {
        cart.addItem("Pen", 10, 2);
        cart.addItem("Pen", 10, 3);

        assertAll("cart after adding Pen twice",
                () -> assertEquals(5, cart.getItemCount()),
                () -> assertEquals(50.0, cart.getTotal(), DELTA));
    }

    // ---------- REQ-2: invalid input ----------

    @ParameterizedTest(name = "invalid item: name=''{0}'', price={1}, quantity={2}")
    @CsvSource({
            "Pen, 0, 1",      // price is zero
            "Pen, -5, 1",     // price is negative
            "Pen, 10, 0",     // quantity is zero
            "Pen, 10, -1",    // quantity is negative
            "'', 10, 1"       // empty name
    })
    void invalidItemIsRejected(String name, double price, int quantity) {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem(name, price, quantity));
    }

    @Test
    @DisplayName("REQ-2: a rejected item is not added to the cart")
    void rejectedItemDoesNotChangeTheCart() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Pen", 0, 1));

        assertEquals(0, cart.getItemCount());
    }

    // ---------- REQ-3: total ----------

    @Test
    void emptyCartHasZeroTotal() {
        assertEquals(0.0, cart.getTotal(), DELTA);
    }

    @Test
    @DisplayName("REQ-3: total is the sum of price x quantity of all items")
    void totalIsSumOfPriceTimesQuantity() {
        cart.addItem("Pen", 10, 2);      // 20
        cart.addItem("Notebook", 50, 3); // 150
        cart.addItem("Bag", 500.5, 1);   // 500.5

        assertEquals(670.5, cart.getTotal(), DELTA);
    }

    // ---------- REQ-4: discount ----------

    @ParameterizedTest(name = "{0}% discount on Rs. 200 gives Rs. {1}")
    @CsvSource({
            "0, 200.0",    // lower boundary
            "1, 198.0",
            "25, 150.0",
            "49, 102.0",
            "50, 100.0"    // upper boundary
    })
    void validDiscountReducesTheTotal(double percent, double expectedTotal) {
        cart.addItem("Shoes", 100, 2); // total is 200

        cart.applyDiscount(percent);

        assertEquals(expectedTotal, cart.getTotal(), DELTA);
    }

    @ParameterizedTest(name = "{0}% discount is rejected")
    @ValueSource(doubles = {-1, 51, 100, -50})
    void invalidDiscountIsRejected(double percent) {
        assertThrows(IllegalArgumentException.class,
                () -> cart.applyDiscount(percent));
    }

    @Test
    @DisplayName("REQ-4: a rejected discount does not change the total")
    void rejectedDiscountLeavesTotalUnchanged() {
        cart.addItem("Shoes", 100, 2);

        assertThrows(IllegalArgumentException.class,
                () -> cart.applyDiscount(51));

        assertEquals(200.0, cart.getTotal(), DELTA);
    }

    // ---------- REQ-5: removing items ----------

    @Test
    @DisplayName("REQ-5: removing an item returns true and takes it out")
    void removeExistingItemReturnsTrue() {
        cart.addItem("Pen", 10, 2);
        cart.addItem("Bag", 500, 1);

        boolean removed = cart.removeItem("Pen");

        assertAll("cart after removing Pen",
                () -> assertTrue(removed),
                () -> assertEquals(1, cart.getItemCount()),
                () -> assertEquals(500.0, cart.getTotal(), DELTA));
    }

    @Test
    @DisplayName("REQ-5: removing an item that is not in the cart returns false")
    void removeMissingItemReturnsFalse() {
        cart.addItem("Pen", 10, 2);

        assertFalse(cart.removeItem("Laptop"));
        assertEquals(2, cart.getItemCount()); // cart is not changed
    }

    @Test
    void removingTheSameItemTwiceReturnsFalseTheSecondTime() {
        cart.addItem("Pen", 10, 2);

        assertTrue(cart.removeItem("Pen"));
        assertFalse(cart.removeItem("Pen"));
    }

    // ---------- REQ-6: item count ----------

    @Test
    void emptyCartHasZeroItems() {
        assertEquals(0, cart.getItemCount());
    }

    @Test
    @DisplayName("REQ-6: item count is the total quantity of all items")
    void itemCountIsTotalQuantity() {
        cart.addItem("Pen", 10, 2);
        cart.addItem("Notebook", 50, 3);
        cart.addItem("Pen", 10, 1); // same item, quantity becomes 3

        assertEquals(6, cart.getItemCount());
    }
}
