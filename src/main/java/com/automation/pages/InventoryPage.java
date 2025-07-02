package com.automation.pages;

import com.automation.base.BasePage;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object class for the Sauce Demo Inventory page
 * Contains all inventory-related elements and methods
 */
public class InventoryPage extends BasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(className = "inventory_item_desc")
    private List<WebElement> productDescriptions;

    // Locators for elements not using @FindBy
    private final By pageTitleLocator = By.className("title");
    private final By cartIconLocator = By.className("shopping_cart_link");
    private final By cartBadgeLocator = By.className("shopping_cart_badge");
    private final By menuButtonLocator = By.cssSelector(".bm-burger-button");
    private final By sortDropdownLocator = By.className("product_sort_container");
    private final By inventoryItemsLocator = By.className("inventory_item");
    private final By productNamesLocator = By.className("inventory_item_name");
    private final By productPricesLocator = By.className("inventory_item_price");
    private final By productDescriptionsLocator = By.className("inventory_item_desc");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public InventoryPage(WebDriver driver) {
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
     * Select sort option by visible text
     * @param sortOption sort option to select
     */
    public void selectSortOption(String sortOption) {
        selectByVisibleText(sortDropdownLocator, sortOption);
        logger.info("Selected sort option: " + sortOption);
    }

    /**
     * Get current sort option
     * @return current sort option text
     */
    public String getCurrentSortOption() {
        String currentOption = getText(sortDropdownLocator);
        logger.debug("Current sort option: " + currentOption);
        return currentOption;
    }

    /**
     * Get number of inventory items
     * @return number of inventory items
     */
    public int getInventoryItemCount() {
        List<WebElement> items = findElements(inventoryItemsLocator);
        int count = items.size();
        logger.debug("Inventory item count: " + count);
        return count;
    }

    /**
     * Get all product names
     * @return list of product names
     */
    public List<String> getAllProductNames() {
        List<WebElement> nameElements = findElements(productNamesLocator);
        List<String> names = nameElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Product names: " + names);
        return names;
    }

    /**
     * Get all product prices
     * @return list of product prices
     */
    public List<String> getAllProductPrices() {
        List<WebElement> priceElements = findElements(productPricesLocator);
        List<String> prices = priceElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Product prices: " + prices);
        return prices;
    }

    /**
     * Get all product descriptions
     * @return list of product descriptions
     */
    public List<String> getAllProductDescriptions() {
        List<WebElement> descElements = findElements(productDescriptionsLocator);
        List<String> descriptions = descElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Product descriptions: " + descriptions);
        return descriptions;
    }

    /**
     * Add product to cart by product name
     * @param productName name of the product to add
     */
    public void addProductToCart(String productName) {
        By addToCartButtonLocator = By.xpath("//div[contains(@class, 'inventory_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + productName + "']]//button[contains(@class, 'btn_inventory')]");
        click(addToCartButtonLocator);
        logger.info("Added product to cart: " + productName);
    }

    /**
     * Add product to cart by index
     * @param index index of the product (0-based)
     */
    public void addProductToCartByIndex(int index) {
        By addToCartButtonLocator = By.xpath("(//button[contains(@class, 'btn_inventory')])[" + (index + 1) + "]");
        click(addToCartButtonLocator);
        logger.info("Added product to cart at index: " + index);
    }

    /**
     * Remove product from cart by product name
     * @param productName name of the product to remove
     */
    public void removeProductFromCart(String productName) {
        By removeFromCartButtonLocator = By.xpath("//div[contains(@class, 'inventory_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + productName + "']]//button[contains(@class, 'btn_inventory')]");
        click(removeFromCartButtonLocator);
        logger.info("Removed product from cart: " + productName);
    }

    /**
     * Remove product from cart by index
     * @param index index of the product (0-based)
     */
    public void removeProductFromCartByIndex(int index) {
        By removeFromCartButtonLocator = By.xpath("(//button[contains(@class, 'btn_inventory')])[" + (index + 1) + "]");
        click(removeFromCartButtonLocator);
        logger.info("Removed product from cart at index: " + index);
    }

    /**
     * Click on product name to view details
     * @param productName name of the product to click
     */
    public void clickProductName(String productName) {
        By productNameLocator = By.xpath("//div[contains(@class, 'inventory_item_name') and text()='" + productName + "']");
        click(productNameLocator);
        logger.info("Clicked on product name: " + productName);
    }

    /**
     * Click on product name by index
     * @param index index of the product (0-based)
     */
    public void clickProductNameByIndex(int index) {
        By productNameLocator = By.xpath("(//div[contains(@class, 'inventory_item_name')])[" + (index + 1) + "]");
        click(productNameLocator);
        logger.info("Clicked on product name at index: " + index);
    }

    /**
     * Click on product image
     * @param productName name of the product
     */
    public void clickProductImage(String productName) {
        By productImageLocator = By.xpath("//div[contains(@class, 'inventory_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + productName + "']]//img[contains(@class, 'inventory_item_img')]");
        click(productImageLocator);
        logger.info("Clicked on product image: " + productName);
    }

    /**
     * Click on product image by index
     * @param index index of the product (0-based)
     */
    public void clickProductImageByIndex(int index) {
        By productImageLocator = By.xpath("(//img[contains(@class, 'inventory_item_img')])[" + (index + 1) + "]");
        click(productImageLocator);
        logger.info("Clicked on product image at index: " + index);
    }

    /**
     * Get button text for product
     * @param productName name of the product
     * @return button text (Add to cart / Remove)
     */
    public String getProductButtonText(String productName) {
        By buttonLocator = By.xpath("//div[contains(@class, 'inventory_item') and .//div[contains(@class, 'inventory_item_name') and text()='" + productName + "']]//button[contains(@class, 'btn_inventory')]");
        String buttonText = getText(buttonLocator);
        logger.debug("Button text for " + productName + ": " + buttonText);
        return buttonText;
    }

    /**
     * Get button text for product by index
     * @param index index of the product (0-based)
     * @return button text (Add to cart / Remove)
     */
    public String getProductButtonTextByIndex(int index) {
        By buttonLocator = By.xpath("(//button[contains(@class, 'btn_inventory')])[" + (index + 1) + "]");
        String buttonText = getText(buttonLocator);
        logger.debug("Button text for product at index " + index + ": " + buttonText);
        return buttonText;
    }

    /**
     * Check if product is in cart
     * @param productName name of the product
     * @return true if product is in cart
     */
    public boolean isProductInCart(String productName) {
        String buttonText = getProductButtonText(productName);
        boolean inCart = "Remove".equals(buttonText);
        logger.debug("Product " + productName + " in cart: " + inCart);
        return inCart;
    }

    /**
     * Check if product is in cart by index
     * @param index index of the product (0-based)
     * @return true if product is in cart
     */
    public boolean isProductInCartByIndex(int index) {
        String buttonText = getProductButtonTextByIndex(index);
        boolean inCart = "Remove".equals(buttonText);
        logger.debug("Product at index " + index + " in cart: " + inCart);
        return inCart;
    }

    /**
     * Wait for inventory page to load
     */
    public void waitForInventoryPageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, pageTitleLocator);
        WaitUtils.waitForElementVisible(driver, cartIconLocator);
        WaitUtils.waitForElementVisible(driver, sortDropdownLocator);
        logger.info("Inventory page loaded successfully");
    }

    /**
     * Verify inventory page elements are displayed
     * @return true if all inventory page elements are displayed
     */
    public boolean verifyInventoryPageElements() {
        boolean pageTitle = isPageTitleDisplayed();
        boolean cartIcon = isCartIconDisplayed();
        boolean menuButton = isMenuButtonDisplayed();
        boolean sortDropdown = isElementDisplayed(sortDropdownLocator);
        boolean inventoryItems = getInventoryItemCount() > 0;

        boolean allElementsPresent = pageTitle && cartIcon && menuButton && sortDropdown && inventoryItems;
        logger.info("Inventory page elements verification: " + allElementsPresent);
        return allElementsPresent;
    }

    /**
     * Sort products by name (A to Z)
     */
    public void sortByNameAZ() {
        selectSortOption("Name (A to Z)");
        logger.info("Sorted products by name (A to Z)");
    }

    /**
     * Sort products by name (Z to A)
     */
    public void sortByNameZA() {
        selectSortOption("Name (Z to A)");
        logger.info("Sorted products by name (Z to A)");
    }

    /**
     * Sort products by price (low to high)
     */
    public void sortByPriceLowToHigh() {
        selectSortOption("Price (low to high)");
        logger.info("Sorted products by price (low to high)");
    }

    /**
     * Sort products by price (high to low)
     */
    public void sortByPriceHighToLow() {
        selectSortOption("Price (high to low)");
        logger.info("Sorted products by price (high to low)");
    }

    /**
     * Check if inventory page is displayed
     * @return true if inventory page is displayed
     */
    public boolean isInventoryPageDisplayed() {
        try {
            // First check if we're on the correct URL
            String currentUrl = driver.getCurrentUrl();
            boolean correctUrl = currentUrl.contains("/inventory.html");
            
            // Then check for key elements that should be present on inventory page
            boolean hasCartIcon = isElementDisplayed(cartIconLocator);
            boolean hasMenuButton = isElementDisplayed(menuButtonLocator);
            boolean hasSortDropdown = isElementDisplayed(sortDropdownLocator);
            
            // Check for inventory items (at least one should be present)
            boolean hasInventoryItems = getInventoryItemCount() > 0;
            
            boolean displayed = correctUrl && hasCartIcon && hasMenuButton && hasSortDropdown && hasInventoryItems;
            logger.debug("Inventory page displayed: " + displayed + 
                        " (URL: " + correctUrl + 
                        ", Cart: " + hasCartIcon + 
                        ", Menu: " + hasMenuButton + 
                        ", Sort: " + hasSortDropdown + 
                        ", Items: " + hasInventoryItems + ")");
            return displayed;
        } catch (Exception e) {
            logger.error("Error checking if inventory page is displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get inventory title
     * @return inventory title text
     */
    public String getInventoryTitle() {
        String title = driver.getTitle();
        logger.debug("Inventory title: " + title);
        return title;
    }
} 