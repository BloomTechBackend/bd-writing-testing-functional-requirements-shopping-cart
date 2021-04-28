package com.amazon.ata.shoppingcart;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * SAMPLE SOLUTION (with way more test methods than you're expected to have created)
 * <p>
 * Test class that runs through tests for the ShoppingCart class. Prints out results of tests to
 * the console.
 * <p>
 * Run the tests by running {@code main()} method directly in IntelliJ.
 * Run the tests by running {@code main()} method directly in IntelliJ.
 * <p>
 * This is a possible set of methods that you could have implemented as part of this activity.
 * <p>
 * You were only expected to create two of these (or others we didn't include here!). There are more test cases you
 * could have come up with (e.g. varying numbers of items in the cart).
 * <p>
 * There are many ways to test post-conditions/invariants, as well as different messages to print out in the case of
 * failures/successes, as well as ways to construct those messages. You may even see some variation in the
 * test methods below, but these are good representations of what you may have come up with.
 * <p>
 * Note that in the Unit Testing lesson, we'll find an even better way of doing this, but the structure of
 * the test methods will largely remain the same, so this is great practice!
 */
public class ShoppingCartSolutionTest {
    /**
     * Calls the test methods, keeping track of whether each ShoppingCart method passes all of its tests or not.
     * Prints a summary of results. Note that some tests may not run if earlier tests fail.
     * <p>
     * The {@code @Test} annotation here marks this as a unit test, so that JUnit runs the tests when the Brazil package
     * is built. We'll cover this in the Unit Testing lesson.
     */
    @Test
    void runAllTests() {
        ShoppingCartSolutionTest tester = new ShoppingCartSolutionTest();
        boolean pass = true;

        // addItem() tests
        System.out.println("\nTesting addItem()...");

        pass = tester.addItem_withEmptyItemName_isRejected();
        pass = tester.addItem_withValidNameAndQuantity_addsItemToCart() && pass;
        pass = addItem_withExistingItem_sumsItemQuantity() && pass;
        pass = addItem_withNullItemName_isRejected() && pass;
        pass = addItem_withNegativeQuantity_isRejected() && pass;
        pass = addItem_withZeroQuantity_isRejected() && pass;

        if (!pass) {
            String errorMessage = "\n/!\\ /!\\ /!\\ The addItem() method tests failed. Test aborted. /!\\ /!\\ /!\\";
            System.out.println(errorMessage);
            fail(errorMessage);
        } else {
            System.out.println("The addItem() method tests passed!");
        }


        // updateQuantity() tests

        System.out.println("\nTesting updateQuantity()...");
        pass = tester.updateQuantity_withZeroQuantity_removesItemFromCart() && pass;
        updateQuantity_withPositiveQuantity_replacesQuantity();
        updateQuantity_withNullItemName_isRejected();
        updateQuantity_withEmptyItemName_isRejected();
        updateQuantity_onItemNotInCart_isRejected();
        updateQuantity_withNegativeQuantity_isRejected();

        if (!pass) {
            String errorMessage = "\n/!\\ /!\\ /!\\ The updateQuantity() method tests failed. Test aborted. "
                                  + "/!\\ /!\\ /!\\";
            System.out.println(errorMessage);
            fail(errorMessage);
        } else {
            System.out.println("The updateQuantity() method tests passed!");
        }


        System.out.println("\nAll tests passed!");
    }


    // addItem() test cases:  -------------------------------

    private boolean addItem_withValidNameAndQuantity_addsItemToCart() {
        // GIVEN
        // Empty ShoppingCart
        ShoppingCart cart = new ShoppingCart();
        // a valid item name
        String newItemName = "Froggie pencil";
        // a positive quantity
        int quantity = 6;

        // WHEN - Add a new item by calling `addItem()` with non-empty itemName and positive quantity
        boolean result = cart.addItem(newItemName, quantity);

        // THEN
        // `addItem()` returns true
        if (!result) {
            System.out.println("  FAIL: Adding item " + newItemName + " should have succeeded");
            return false;
        }

        // ShoppingCart contains the itemName and quantity specified
        if (!cartContainsItemAndQuantity(cart, newItemName, quantity)) {
            System.out.println("  FAIL: Could not find new item with name " + newItemName + " and quantity " +
                               quantity);
            return false;
        }

        System.out.println("  PASS: Adding item " + newItemName + " to ShoppingCart was accepted, as was"
                           + " expected.");
        return true;
    }

    private boolean addItem_withExistingItem_sumsItemQuantity() {
        // GIVEN
        // ShoppingCart with one item in it
        ShoppingCart cart = new ShoppingCart();
        String itemName = "Cheerios";
        int originalQuantity = 2;
        cart.addItem(itemName, originalQuantity);
        // positive quantity
        int additionalQuantity = 3;


        // WHEN - Call `addItem()` same itemName and new positive quantity
        boolean result = cart.addItem(itemName, additionalQuantity);

        // THEN
        // `addItem()` returns true
        if (!result) {
            System.out.println("  FAIL: Adding item already in cart " + itemName + " should have succeeded");
            return false;
        }

        // ShoppingCart contains the item with quantity sum of original quantity new positive quantity
        if (!cartContainsItemAndQuantity(cart, itemName, originalQuantity + additionalQuantity)) {
            System.out.println("  FAIL: Adding item already in cart " + itemName +
                               " did not result in summed quantity " +
                               originalQuantity + additionalQuantity);
            return false;
        }

        System.out.println("  PASS: Adding item already in cart " + itemName + " resulted in summed "
                           + "quantity, as was expected.");
        return true;
    }

    private boolean addItem_withNullItemName_isRejected() {
        // GIVEN
        // Empty ShoppingCart
        ShoppingCart cart = new ShoppingCart();
        // positive quantity
        int quantity = 100;

        // WHEN - Add a new item by calling `addItem()` with null itemName, positive quantity
        boolean result = cart.addItem(null, quantity);

        // THEN
        // `addItem()` returns false
        if (result) {
            System.out.println("  FAIL: Adding item with null name succeeded but should not have");
            return false;
        }

        // the ShoppingCart remains empty
        if (cart.getItems().length > 0) {
            System.out.println("  FAIL: Adding item with null name to empty cart should result in empty cart");
            return false;
        }

        System.out.println("  PASS: Adding item with null name was rejected, as was expected.");
        return true;
    }

    private boolean addItem_withEmptyItemName_isRejected() {
        // GIVEN - an empty ShoppingCart
        ShoppingCart cart = new ShoppingCart();

        // WHEN - add an item with empty string for name
        boolean result = cart.addItem("", 1);

        // THEN
        // add to cart should have failed
        if (result) {
            System.out.println("  FAIL: Adding item '' should not have succeeded");
            return false;
        }

        // ShoppingCart should be empty
        if (cart.getItems().length > 0) {
            System.out.println(
                String.format("  FAIL: Adding item '' should keep ShoppingCart empty, but contains %s",
                              Arrays.toString(cart.getItems())
                )
            );
            return false;
        }

        System.out.println("  PASS: Adding item '' was rejected, as was expected.");
        return true;
    }


    private boolean addItem_withNegativeQuantity_isRejected() {
        // GIVEN - Empty ShoppingCart
        ShoppingCart cart = new ShoppingCart();

        // WHEN - Add a new item by calling `addItem()` with non-empty itemName, negative quantity
        boolean result = cart.addItem("Desk chair", -2);

        // THEN
        // `addItem()` returns false
        if (result) {
            System.out.println("  FAIL: Adding item with negative quantity succeeded but should not have");
            return false;
        }

        // the ShoppingCart remains empty
        if (cart.getItems().length > 0) {
            System.out.println("  FAIL: Adding item with negative quantity to empty cart should result in empty cart");
            return false;
        }

        System.out.println("  PASS: Adding item with negative quantity was rejected, as was expected.");
        return true;
    }

    private boolean addItem_withZeroQuantity_isRejected() {
        // GIVEN - Empty ShoppingCart
        ShoppingCart cart = new ShoppingCart();

        // WHEN - Add a new item by calling `addItem()` with non-empty itemName, zero quantity
        boolean result = cart.addItem("Head First Java", 0);

        // THEN
        // `addItem()` returns false
        if (result) {
            System.out.println("  FAIL: Adding item with zero quantity succeeded but should not have");
            return false;
        }

        // the ShoppingCart remains empty
        if (cart.getItems().length > 0) {
            System.out.println("  FAIL: Adding item with zero quantity to empty cart should result in empty cart");
            return false;
        }

        System.out.println("  PASS: Adding item with zero quantity was rejected, as was expected.");
        return true;
    }

    // updateQuantity() test cases:  ------------------------

    private boolean updateQuantity_withPositiveQuantity_replacesQuantity() {
        // GIVEN - ShoppingCart with one item
        String itemName = "Granola bars";
        int originalQuantity = 1;
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(itemName, originalQuantity);
        // positive replacement quantity
        int replacementQuantity = 4;


        // WHEN - Call `updateQuantity()` with same itemName and a new positive quantity
        boolean result = cart.updateQuantity(itemName, replacementQuantity);

        // THEN
        // `updateQuantity()` returns true
        if (!result) {
            System.out.println(String.format("  FAIL: Updating quantity on item \"%s\"", itemName));
            return false;
        }

        // item still exists in ShoppingCart and its quantity is the new positive quantity
        if (!cartContainsItemAndQuantity(cart, itemName, replacementQuantity)) {
            System.out.println(
                String.format("  FAIL: Updating quantity on item \"%s\" to %d, did not result in that quantity",
                              itemName,
                              replacementQuantity)
            );
            return false;
        }

        System.out.println(
            String.format("  PASS: Updating quantity on item \"%s\" resulted in updated quantity",
                          itemName
            )
        );
        return true;
    }

    private boolean updateQuantity_withZeroQuantity_removesItemFromCart() {
        // GIVEN -  ShoppingCart with one item
        String itemName = "Instant Pot";
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(itemName, 1);

        // WHEN - Call `updateQuantity()` with same itemName and a zero quantity
        boolean result = cart.updateQuantity(itemName, 0);

        // THEN
        // `updateQuantity()` returns true
        if (!result) {
            System.out.println(String.format("  FAIL: Updating quantity on item \"%s\" failed", itemName));
            return false;
        }

        // ShoppingCart no longer contains the item
        if (cartContainsItem(cart, itemName)) {
            System.out.println(
                String.format("  FAIL: Updating quantity on item \"%s\" to 0 did not remove item", itemName)
            );
            return false;
        }

        System.out.println(
            String.format("  PASS: Updating quantity on item \"%s\" to 0 removed item from cart", itemName)
        );
        return true;
    }

    private boolean updateQuantity_withNullItemName_isRejected() {
        // GIVEN - ShoppingCart with one item
        String existingItem = "Binoculars";
        int originalQuantity = 1;
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(existingItem, originalQuantity);
        // positive replacement quantity
        int replacementQuantity = 4;

        // WHEN - Call `updateQuantity()` with null itemName and positive quantity
        boolean result = cart.updateQuantity(null, replacementQuantity);

        // THEN
        // `updateQuantity()` returns false
        if (result) {
            System.out.println("  FAIL: Updating quantity with a null item name succeeded, but expected not to");
        }

        // ShoppingCart does not contain an item with null itemName
        if (cartContainsItem(cart, null)) {
            System.out.println("  FAIL: Updating quantity with null item should not result in null item in cart");
        }

        // ShoppingCart still contains original item with original quantity
        if (!cartContainsItemAndQuantity(cart, existingItem, originalQuantity)) {
            System.out.println("  FAIL: Updating quantity with null item changed existing item in some way");
        }

        System.out.println("  PASS: Updating quantity with null item was rejected, as expected");
        return true;
    }

    private boolean updateQuantity_withEmptyItemName_isRejected() {
        // GIVEN - ShoppingCart with one item
        String existingItem = "HDMI Cables (set of 6)";
        int originalQuantity = 4;
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(existingItem, originalQuantity);
        // positive replacement quantity
        int replacementQuantity = 3;

        // WHEN - Call `updateQuantity()` with itemName "" and positive quantity
        boolean result = cart.updateQuantity("", replacementQuantity);

        // THEN
        // `updateQuantity()` returns false
        if (result) {
            System.out.println("  FAIL: Updating quantity with item name \"\" succeeded, but expected not to");
        }

        // ShoppingCart does not contain an item with empty string itemName
        if (cartContainsItem(cart, "")) {
            System.out.println("  FAIL: Updating quantity with item name \"\" should not result in item added to cart");
        }

        // ShoppingCart still contains original item with original quantity
        if (!cartContainsItemAndQuantity(cart, existingItem, originalQuantity)) {
            System.out.println("  FAIL: Updating quantity with item name \"\" changed existing item in some way");
        }

        System.out.println("  PASS: Updating quantity with item name \"\" was rejected as expected");
        return true;
    }

    private boolean updateQuantity_onItemNotInCart_isRejected() {
        // GIVEN - ShoppingCart with one item
        String existingItem = "HDMI Cables (set of 6)";
        int originalQuantity = 4;
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(existingItem, originalQuantity);
        // item not in cart
        String itemNotInCart = "Power Cords (set of 4)";
        // positive replacement quantity
        int replacementQuantity = 3;

        // WHEN - Call `updateQuantity()` with a different item name and positive quantity
        boolean result = cart.updateQuantity(itemNotInCart, replacementQuantity);

        // THEN
        // `updateQuantity()` returns false
        if (result) {
            System.out.println("  FAIL: Updating quantity with item not in cart succeeded, but expected not to");
        }

        // ShoppingCart does not contain an item with the new itemName
        if (cartContainsItem(cart, itemNotInCart)) {
            System.out.println("  FAIL: Updating quantity with item not in cart should not result in item being found"
                               + " in cart");
        }

        // ShoppingCart still contains original item with original quantity
        if (!cartContainsItemAndQuantity(cart, existingItem, originalQuantity)) {
            System.out.println("  FAIL: Updating quantity with item not in cart changed existing item in some way");
        }

        System.out.println("  PASS: Updating quantity with item not in cart was rejected as expected");
        return true;
    }

    private boolean updateQuantity_withNegativeQuantity_isRejected() {
        // GIVEN - ShoppingCart with one item
        String itemName = "Telescope";
        int originalQuantity = 2;
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(itemName, originalQuantity);

        // WHEN - Call `updateQuantity()` with a the same itemName but negative quantity
        boolean result = cart.updateQuantity(itemName, -1);

        // THEN
        // `updateQuantity()` returns false
        if (result) {
            System.out.println("  FAIL: Updating quantity with negative quantity succeeded, but expected not to");
        }

        // ShoppingCart still contains original item with original quantity
        if (!cartContainsItemAndQuantity(cart, itemName, originalQuantity)) {
            System.out.println("  FAIL: Updating quantity with negative quantity changed existing item in some way");
        }

        System.out.println("  PASS: Updating quantity with negative quantity was rejected as expected");
        return true;
    }

    /**
     * The entry point, which results in calls to all test methods. We'll talk about this soon, but if you want to
     * understand a little more about the significance of the main() method, Google "public static void main".
     *
     * @param args Command line arguments (ignored).
     */
    public static void main(String[] args) {
        ShoppingCartSolutionTest tester = new ShoppingCartSolutionTest();
        tester.runAllTests();
    }

    /**
     * Helper method for checking that an expected item/quantity was found in the ShoppingCart. Feel free to use this
     * in your tests as well!
     *
     * @param cart     the {@code ShoppingCart} to test
     * @param itemName the name of the item expected to be in {@code cart}
     * @param quantity the expected quantity for the item to be in {@code cart}
     * @return {@code true} if the item is found with the specified quantity, {@code false} otherwise.
     */
    private boolean cartContainsItemAndQuantity(ShoppingCart cart, String itemName, int quantity) {
        boolean foundItemWithQuantity = false;
        for (ShoppingCartItem item : cart.getItems()) {
            if (item.getItemName().equals(itemName) && item.getQuantity() == quantity) {
                foundItemWithQuantity = true;
            }
        }

        return foundItemWithQuantity;
    }

    /**
     * Helper method for checking that a particular item exists in the ShoppingCart (regardless of quantity).
     * <p>
     * You can create methods like this that several of your tests call to help simplify the test code.
     *
     * @param cart     the {@code ShoppingCart} to test
     * @param itemName the name of the item to look for in the {@code cart}
     * @return {@code true} if the item is found in {@code cart}, {@code false} otherwise.
     */
    private boolean cartContainsItem(ShoppingCart cart, String itemName) {
        boolean foundItem = false;
        for (ShoppingCartItem item : cart.getItems()) {
            if (item.getItemName().equals(itemName)) {
                foundItem = true;
            }
        }

        return foundItem;
    }
}
