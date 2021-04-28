package com.amazon.ata.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ShoppingCartUnitTest {
    private ShoppingCart emptyCart;
    private ShoppingCart cartWithItems;
    private String item1 = "Cuisinart 7 Quart Oval Casserole";
    private int item1Quantity = 1;
    private String item2 = "BIC Round Stic Xtra Life Ballpoint Pen";
    private int item2Quantity = 10;
    private String itemNotInCart = "Mickey Mantle Autographed Baseball Cards";

    @BeforeEach
    private void createCart() {
        emptyCart = new ShoppingCart();

        cartWithItems = new ShoppingCart();
        cartWithItems.addItem(item1, item1Quantity);
        cartWithItems.addItem(item2, item2Quantity);
    }

    @Test
    void addItem_emptyCartValidNameAndQuantity_isAccepted() {
        // GIVEN - empty cart
        ShoppingCart cart = emptyCart;

        // WHEN
        boolean result = cart.addItem(itemNotInCart, 1);

        // THEN
        assertTrue(result, "Expected to be able to add new item to cart with valid quantity");
        assertFoundItemWithQuantity(cart, itemNotInCart, item1Quantity);
    }

    @Test
    void addItem_cartHasItemsNewItemWithValidNameAndQuantity_isAccepted() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.addItem(itemNotInCart, 1);

        // THEN
        assertTrue(result, "Expected to be able to add new item to cart with valid quantity");
        assertFoundItemWithQuantity(cart, itemNotInCart, item1Quantity);
    }

    @Test
    void addItem_existingItem_quantityIsSummed() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;
        int additionalQuantity = 3;

        // WHEN
        boolean result = cart.addItem(item1, additionalQuantity);

        // THEN
        assertTrue(result, "Expected to be able to add same item to cart");
        assertFoundItemWithQuantity(cart, item1, item1Quantity + additionalQuantity);
    }

    @Test
    public void addItem_nullItemName_isRejected() {
        // GIVEN - empty cart
        ShoppingCart cart = emptyCart;

        // WHEN
        boolean result = cart.addItem(null, 1);

        // THEN
        assertFalse(result, "Expected not to be able to add null item to cart");
        assertNotFoundItem(cart, null);
    }

    @Test
    public void addItem_emptyStringItemName_isRejected() {
        // GIVEN - empty cart
        ShoppingCart cart = emptyCart;

        // WHEN
        boolean result = cart.addItem("", 1);

        // THEN
        assertFalse(result, "Expected not to be able to add empty string item to cart");
        assertNotFoundItem(cart, "");
    }

    @Test
    void addItem_zeroQuantity_isRejected() {
        // GIVEN - empty cart
        ShoppingCart cart = emptyCart;

        // WHEN
        boolean result = cart.addItem(itemNotInCart, 0);

        // THEN
        assertFalse(result, "Expected not to be able to add zero quantity item to cart");
        assertNotFoundItem(cart, itemNotInCart);
    }

    @Test
    void addItem_negativeQuantity_isRejected() {
        // GIVEN - empty cart
        ShoppingCart cart = emptyCart;
        int negativeQuantity = -1;

        // WHEN
        boolean result = cart.addItem(itemNotInCart, negativeQuantity);

        // THEN
        assertFalse(result, "Expected not to be able to add negative quantity item to cart");
        assertNotFoundItem(cart, itemNotInCart);
    }

    @Test
    void addItem_addExistingItemWithZeroQuantity_isRejected() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.addItem(item1, 0);

        // THEN
        assertFalse(result, "Expected not to be able to add item with zero quantity");
        assertFoundItemWithQuantity(cart, item1, item1Quantity);
    }

    @Test
    void addItem_addExistingItemWithNegativeQuantity_isRejected() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.addItem(item1, -2);

        // THEN
        assertFalse(result, "Expected not to be able to add item with negative quantity");
        assertFoundItemWithQuantity(cart, item1, item1Quantity);
    }

    @Test
    void updateQuantity_existingItemPositiveQuantity_quantityIsReplaced() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;
        int newQuantity = 13;

        // WHEN
        boolean result = cart.updateQuantity(item1, newQuantity);

        // THEN
        assertTrue(result, "Expected to be able to update item quantity");
        assertFoundItemWithQuantity(cart, item1, newQuantity);
    }

    @Test
    void updateQuantity_existingItemZeroQuantity_itemIsRemovedFromCart() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.updateQuantity(item2, 0);

        // THEN
        assertTrue(result, "Expected to be able to set item quantity to zero");
        assertNotFoundItem(cart, item2);
    }

    @Test
    void updateQuantity_itemNameNotInCart_isRejected() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.updateQuantity(itemNotInCart, 1);

        // THEN
        assertFalse(result, "Expected not to be able to update quantity on item not already in cart");
        assertNotFoundItem(cart, itemNotInCart);
    }

    @Test
    void updateQuantity_nullItemName_isRejected() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.updateQuantity(null, 1);

        // THEN
        assertFalse(result, "Expected not to be able to update quantity on null item");
        assertNotFoundItem(cart, null);
    }

    @Test
    void updateQuantity_emptyStringItemName_isRejected() {
        // GIVEN - cart with items
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.updateQuantity("", 1);

        // THEN
        assertFalse(result, "Expected not to be able to update quantity on empty string item");
        assertNotFoundItem(cart, "");
    }

    @Test
    void updateQuantity_itemNameInCartNegativeQuantity_isRejected() {
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.updateQuantity(item1, -1);

        // THEN
        assertFalse(result, "Expected not to be able to update quantity to negative");
        assertFoundItemWithQuantity(cart, item1, item1Quantity);
    }

    @Test
    void updateQuantity_itemNameNotInCartNegativeQuantity_isRejected() {
        ShoppingCart cart = cartWithItems;

        // WHEN
        boolean result = cart.updateQuantity(itemNotInCart, -1);

        // THEN
        assertFalse(result, "Expected not to be able to update quantity to negative on item not in cart");
        assertNotFoundItem(cart, itemNotInCart);
    }

    private void assertFoundItemWithQuantity(ShoppingCart cart, String itemName, int quantity) {
        boolean foundItemWithQuantity = false;
        for (ShoppingCartItem item : cart.getItems()) {
            if (item.getItemName().equals(itemName) && item.getQuantity() == quantity) {
                foundItemWithQuantity = true;
            }
        }
        assertTrue(
            foundItemWithQuantity,
            String.format(
                "Expected to find item \"%s\" with quantity %d, but getItems() returns: %s",
                itemName,
                quantity,
                Arrays.toString(cart.getItems())
            )
        );
    }

    private void assertNotFoundItem(ShoppingCart cart, String itemName) {
        boolean foundItem = false;
        int quantityFound = 0;
        for (ShoppingCartItem item : cart.getItems()) {
            if (item.getItemName().equals(itemName)) {
                foundItem = true;
                quantityFound = item.getQuantity();
            }
        }
        assertFalse(
            foundItem,
            String.format(
                "Expected not to find item \"%s\" in cart, but it was found with quantity %d",
                itemName,
                quantityFound
            )
        );
    }
}
