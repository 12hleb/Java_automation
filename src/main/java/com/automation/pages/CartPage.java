package com.automation.pages;

import com.automation.base.BasePage;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object class for the Sauce Demo Cart page
 * Contains all cart-related elements and methods
 */
public class CartPage extends BasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> cartItemPrices;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> cartItemDescriptions;

    // Locators for elements not using @FindBy
    private final By pageTitleLocator = By.className("title");
    private final By cartIconLocator = By.className("shopping_cart_link");
    private final By cartBadgeLocator = By.className("shopping_cart_badge");
    private final By menuButtonLocator = By.id("react-burger-menu-btn");
    private final By continueShoppingButtonLocator = By.id("continue-shopping");
    private final By checkoutButtonLocator = By.id("checkout");
    private final By cartItemsLocator = By.className("cart_item");
    private final By cartItemNamesLocator = By.className("inventory_item_name");
    private final By cartItemPricesLocator = By.className("inventory_item_price");
    private final By cartItemDescriptionsLocator = By.className("inventory_item_desc");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get page title
     * @return page title text
     */
    public String getPageTitle() {
        String title = getText(pageTitleLocator);
        logger.debug("Page title: " + title);
        return title;
    }

    /**
     * Check if page title is displayed
     * @return true if page title is displayed
     */
    public boolean isPageTitleDisplayed() {
        boolean displayed = isElementDisplayed(pageTitleLocator);
        logger.debug("Page title displayed: " + displayed);
        return displayed;
    }

    /**
     * Click cart icon
     */
    public void clickCartIcon() {
        click(cartIconLocator);
        logger.info("Clicked cart icon");
    }

    /**
     * Check if cart icon is displayed
     * @return true if cart icon is displayed
     */
    public boolean isCartIconDisplayed() {
        boolean displayed = isElementDisplayed(cartIconLocator);
        logger.debug("Cart icon displayed: " + displayed);
        return displayed;
    }

    /**
     * Get cart badge count
     * @return number of items in cart
     */
    public int getCartBadgeCount() {
        try {
            String badgeText = getText(cartBadgeLocator);
            int count = Integer.parseInt(badgeText);
            logger.debug("Cart badge count: " + count);
            return count;
        } catch (Exception e) {
            logger.debug("Cart badge not found or empty");
            return 0;
        }
    }

    /**
     * Check if cart badge is displayed
     * @return true if cart badge is displayed
     */
    public boolean isCartBadgeDisplayed() {
        boolean displayed = isElementDisplayed(cartBadgeLocator);
        logger.debug("Cart badge displayed: " + displayed);
        return displayed;
    }

    /**
     * Click menu button
     */
    public void clickMenuButton() {
        click(menuButtonLocator);
        logger.info("Clicked menu button");
    }

    /**
     * Check if menu button is displayed
     * @return true if menu button is displayed
     */
    public boolean isMenuButtonDisplayed() {
        boolean displayed = isElementDisplayed(menuButtonLocator);
        logger.debug("Menu button displayed: " + displayed);
        return displayed;
    }

    /**
     * Click continue shopping button
     */
    public void clickContinueShopping() {
        click(continueShoppingButtonLocator);
        logger.info("Clicked continue shopping button");
    }

    /**
     * Check if continue shopping button is displayed
     * @return true if continue shopping button is displayed
     */
    public boolean isContinueShoppingButtonDisplayed() {
        boolean displayed = isElementDisplayed(continueShoppingButtonLocator);
        logger.debug("Continue shopping button displayed: " + displayed);
        return displayed;
    }

    /**
     * Click checkout button
     */
    public void clickCheckout() {
        click(checkoutButtonLocator);
        logger.info("Clicked checkout button");
    }

    /**
     * Check if checkout button is displayed
     * @return true if checkout button is displayed
     */
    public boolean isCheckoutButtonDisplayed() {
        boolean displayed = isElementDisplayed(checkoutButtonLocator);
        logger.debug("Checkout button displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if checkout button is enabled
     * @return true if checkout button is enabled
     */
    public boolean isCheckoutButtonEnabled() {
        boolean enabled = isElementEnabled(checkoutButtonLocator);
        logger.debug("Checkout button enabled: " + enabled);
        return enabled;
    }

    /**
     * Get number of cart items
     * @return number of cart items
     */
    public int getCartItemCount() {
        List<WebElement> items = findElements(cartItemsLocator);
        int count = items.size();
        logger.debug("Cart item count: " + count);
        return count;
    }

    /**
     * Get all cart item names
     * @return list of cart item names
     */
    public List<String> getAllCartItemNames() {
        List<WebElement> nameElements = findElements(cartItemNamesLocator);
        List<String> names = nameElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Cart item names: " + names);
        return names;
    }

    /**
     * Get all cart item prices
     * @return list of cart item prices
     */
    public List<String> getAllCartItemPrices() {
        List<WebElement> priceElements = findElements(cartItemPricesLocator);
        List<String> prices = priceElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Cart item prices: " + prices);
        return prices;
    }

    /**
     * Get all cart item descriptions
     * @return list of cart item descriptions
     */
    public List<String> getAllCartItemDescriptions() {
        List<WebElement> descElements = findElements(cartItemDescriptionsLocator);
        List<String> descriptions = descElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Cart item descriptions: " + descriptions);
        return descriptions;
    }

    /**
     * Remove item from cart by item name
     * @param itemName name of the item to remove
     */
    public void removeItemFromCart(String itemName) {
        By removeButtonLocator = By.xpath("//div[contains(@class, 'cart_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + itemName + "']]//button[contains(@class, 'btn_secondary')]");
        click(removeButtonLocator);
        logger.info("Removed item from cart: " + itemName);
    }

    /**
     * Remove item from cart by index
     * @param index index of the item (0-based)
     */
    public void removeItemFromCartByIndex(int index) {
        By removeButtonLocator = By.xpath("(//div[contains(@class, 'cart_item')]//button[contains(@class, 'btn_secondary')])[" + (index + 1) + "]");
        click(removeButtonLocator);
        logger.info("Removed item from cart at index: " + index);
    }

    /**
     * Click on item name to view details
     * @param itemName name of the item to click
     */
    public void clickItemName(String itemName) {
        By itemNameLocator = By.xpath("//div[contains(@class, 'inventory_item_name') and text()='" + itemName + "']");
        click(itemNameLocator);
        logger.info("Clicked on item name: " + itemName);
    }

    /**
     * Click on item name by index
     * @param index index of the item (0-based)
     */
    public void clickItemNameByIndex(int index) {
        By itemNameLocator = By.xpath("(//div[contains(@class, 'inventory_item_name')])[" + (index + 1) + "]");
        click(itemNameLocator);
        logger.info("Clicked on item name at index: " + index);
    }

    /**
     * Click on item image
     * @param itemName name of the item
     */
    public void clickItemImage(String itemName) {
        By itemImageLocator = By.xpath("//div[contains(@class, 'cart_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + itemName + "']]//img[contains(@class, 'inventory_item_img')]");
        click(itemImageLocator);
        logger.info("Clicked on item image: " + itemName);
    }

    /**
     * Click on item image by index
     * @param index index of the item (0-based)
     */
    public void clickItemImageByIndex(int index) {
        By itemImageLocator = By.xpath("(//img[contains(@class, 'inventory_item_img')])[" + (index + 1) + "]");
        click(itemImageLocator);
        logger.info("Clicked on item image at index: " + index);
    }

    /**
     * Get remove button text for item
     * @param itemName name of the item
     * @return remove button text
     */
    public String getRemoveButtonText(String itemName) {
        By removeButtonLocator = By.xpath("//div[contains(@class, 'cart_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + itemName + "']]//button[contains(@class, 'btn_secondary')]");
        String buttonText = getText(removeButtonLocator);
        logger.debug("Remove button text for " + itemName + ": " + buttonText);
        return buttonText;
    }

    /**
     * Get remove button text for item by index
     * @param index index of the item (0-based)
     * @return remove button text
     */
    public String getRemoveButtonTextByIndex(int index) {
        By removeButtonLocator = By.xpath("(//div[contains(@class, 'cart_item')]//button[contains(@class, 'btn_secondary')])[" + (index + 1) + "]");
        String buttonText = getText(removeButtonLocator);
        logger.debug("Remove button text for item at index " + index + ": " + buttonText);
        return buttonText;
    }

    /**
     * Check if item is in cart
     * @param itemName name of the item
     * @return true if item is in cart
     */
    public boolean isItemInCart(String itemName) {
        List<String> cartItemNames = getAllCartItemNames();
        boolean inCart = cartItemNames.contains(itemName);
        logger.debug("Item " + itemName + " in cart: " + inCart);
        return inCart;
    }

    /**
     * Get item price by name
     * @param itemName name of the item
     * @return item price
     */
    public String getItemPrice(String itemName) {
        By itemPriceLocator = By.xpath("//div[contains(@class, 'cart_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + itemName + "']]//div[contains(@class, 'inventory_item_price')]");
        String price = getText(itemPriceLocator);
        logger.debug("Item price for " + itemName + ": " + price);
        return price;
    }

    /**
     * Get item price by index
     * @param index index of the item (0-based)
     * @return item price
     */
    public String getItemPriceByIndex(int index) {
        By itemPriceLocator = By.xpath("(//div[contains(@class, 'inventory_item_price')])[" + (index + 1) + "]");
        String price = getText(itemPriceLocator);
        logger.debug("Item price at index " + index + ": " + price);
        return price;
    }

    /**
     * Get item description by name
     * @param itemName name of the item
     * @return item description
     */
    public String getItemDescription(String itemName) {
        By itemDescLocator = By.xpath("//div[contains(@class, 'cart_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + itemName + "']]//div[contains(@class, 'inventory_item_desc')]");
        String description = getText(itemDescLocator);
        logger.debug("Item description for " + itemName + ": " + description);
        return description;
    }

    /**
     * Get item description by index
     * @param index index of the item (0-based)
     * @return item description
     */
    public String getItemDescriptionByIndex(int index) {
        By itemDescLocator = By.xpath("(//div[contains(@class, 'inventory_item_desc')])[" + (index + 1) + "]");
        String description = getText(itemDescLocator);
        logger.debug("Item description at index " + index + ": " + description);
        return description;
    }

    /**
     * Calculate total price of all items in cart
     * @return total price
     */
    public double getTotalPrice() {
        List<String> prices = getAllCartItemPrices();
        double total = 0.0;
        for (String price : prices) {
            // Remove $ sign and convert to double
            String cleanPrice = price.replace("$", "");
            total += Double.parseDouble(cleanPrice);
        }
        logger.debug("Total price: $" + total);
        return total;
    }

    /**
     * Wait for cart page to load
     */
    public void waitForCartPageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, pageTitleLocator);
        WaitUtils.waitForElementVisible(driver, cartIconLocator);
        logger.info("Cart page loaded successfully");
    }

    /**
     * Verify cart page elements are displayed
     * @return true if all cart page elements are displayed
     */
    public boolean verifyCartPageElements() {
        boolean pageTitle = isPageTitleDisplayed();
        boolean cartIcon = isCartIconDisplayed();
        boolean menuButton = isMenuButtonDisplayed();
        boolean continueShoppingButton = isContinueShoppingButtonDisplayed();
        boolean checkoutButton = isCheckoutButtonDisplayed();

        boolean allElementsPresent = pageTitle && cartIcon && menuButton && continueShoppingButton && checkoutButton;
        logger.info("Cart page elements verification: " + allElementsPresent);
        return allElementsPresent;
    }

    /**
     * Check if cart is empty
     * @return true if cart is empty
     */
    public boolean isCartEmpty() {
        int itemCount = getCartItemCount();
        boolean empty = itemCount == 0;
        logger.debug("Cart empty: " + empty);
        return empty;
    }

    /**
     * Clear all items from cart
     */
    public void clearCart() {
        int itemCount = getCartItemCount();
        for (int i = 0; i < itemCount; i++) {
            removeItemFromCartByIndex(0); // Always remove first item as list shifts
            WaitUtils.staticWait(1); // Small wait for UI update
        }
        logger.info("Cleared all items from cart");
    }

    /**
     * Check if cart page is displayed
     * @return true if cart page is displayed
     */
    public boolean isCartPageDisplayed() {
        boolean displayed = isElementDisplayed(pageTitleLocator) && 
                           isElementDisplayed(continueShoppingButtonLocator);
        logger.debug("Cart page displayed: " + displayed);
        return displayed;
    }

    /**
     * Get cart title
     * @return cart title text
     */
    public String getCartTitle() {
        String title = getText(pageTitleLocator);
        logger.debug("Cart title: " + title);
        return title;
    }

    /**
     * Get cart item name by index
     * @param index index of the item (0-based)
     * @return item name
     */
    public String getCartItemNameByIndex(int index) {
        List<WebElement> nameElements = findElements(cartItemNamesLocator);
        if (index >= 0 && index < nameElements.size()) {
            String name = nameElements.get(index).getText();
            logger.debug("Cart item name at index " + index + ": " + name);
            return name;
        }
        logger.warn("Invalid index for cart item name: " + index);
        return null;
    }

    /**
     * Get cart item price by index
     * @param index index of the item (0-based)
     * @return item price
     */
    public String getCartItemPriceByIndex(int index) {
        List<WebElement> priceElements = findElements(cartItemPricesLocator);
        if (index >= 0 && index < priceElements.size()) {
            String price = priceElements.get(index).getText();
            logger.debug("Cart item price at index " + index + ": " + price);
            return price;
        }
        logger.warn("Invalid index for cart item price: " + index);
        return null;
    }

    /**
     * Get cart item button text by index
     * @param index index of the item (0-based)
     * @return button text
     */
    public String getCartItemButtonTextByIndex(int index) {
        By buttonLocator = By.xpath("(//button[contains(@class, 'btn_secondary')])[" + (index + 1) + "]");
        try {
            String buttonText = getText(buttonLocator);
            logger.debug("Cart item button text at index " + index + ": " + buttonText);
            return buttonText;
        } catch (Exception e) {
            logger.warn("Could not get button text for cart item at index: " + index);
            return null;
        }
    }
} 