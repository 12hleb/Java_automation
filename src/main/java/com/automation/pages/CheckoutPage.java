package com.automation.pages;

import com.automation.base.BasePage;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object class for the Sauce Demo Checkout page
 * Contains all checkout-related elements and methods
 */
public class CheckoutPage extends BasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    // Checkout Information Form Elements
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    // Checkout Overview Elements
    @FindBy(className = "cart_item")
    private List<WebElement> checkoutItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> checkoutItemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> checkoutItemPrices;

    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    // Checkout Complete Elements
    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(className = "pony_express")
    private WebElement ponyExpressImage;

    // Locators for elements not using @FindBy
    private final By pageTitleLocator = By.className("title");
    private final By cartIconLocator = By.className("shopping_cart_link");
    private final By cartBadgeLocator = By.className("shopping_cart_badge");
    private final By menuButtonLocator = By.id("react-burger-menu-btn");
    private final By cancelButtonLocator = By.id("cancel");
    private final By continueButtonLocator = By.id("continue");
    private final By finishButtonLocator = By.id("finish");
    private final By backToProductsButtonLocator = By.id("back-to-products");
    private final By firstNameFieldLocator = By.id("first-name");
    private final By lastNameFieldLocator = By.id("last-name");
    private final By postalCodeFieldLocator = By.id("postal-code");
    private final By errorMessageLocator = By.cssSelector("h3[data-test='error']");
    private final By checkoutItemsLocator = By.className("cart_item");
    private final By checkoutItemNamesLocator = By.className("inventory_item_name");
    private final By checkoutItemPricesLocator = By.className("inventory_item_price");
    private final By subtotalLabelLocator = By.className("summary_subtotal_label");
    private final By taxLabelLocator = By.className("summary_tax_label");
    private final By totalLabelLocator = By.className("summary_total_label");
    private final By completeHeaderLocator = By.className("complete-header");
    private final By completeTextLocator = By.className("complete-text");
    private final By ponyExpressImageLocator = By.className("pony_express");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public CheckoutPage(WebDriver driver) {
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
     * Click cancel button
     */
    public void clickCancel() {
        click(cancelButtonLocator);
        logger.info("Clicked cancel button");
    }

    /**
     * Check if cancel button is displayed
     * @return true if cancel button is displayed
     */
    public boolean isCancelButtonDisplayed() {
        boolean displayed = isElementDisplayed(cancelButtonLocator);
        logger.debug("Cancel button displayed: " + displayed);
        return displayed;
    }

    /**
     * Click continue button
     */
    public void clickContinue() {
        click(continueButtonLocator);
        logger.info("Clicked continue button");
    }

    /**
     * Check if continue button is displayed
     * @return true if continue button is displayed
     */
    public boolean isContinueButtonDisplayed() {
        boolean displayed = isElementDisplayed(continueButtonLocator);
        logger.debug("Continue button displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if continue button is enabled
     * @return true if continue button is enabled
     */
    public boolean isContinueButtonEnabled() {
        boolean enabled = isElementEnabled(continueButtonLocator);
        logger.debug("Continue button enabled: " + enabled);
        return enabled;
    }

    /**
     * Click finish button
     */
    public void clickFinish() {
        click(finishButtonLocator);
        logger.info("Clicked finish button");
    }

    /**
     * Check if finish button is displayed
     * @return true if finish button is displayed
     */
    public boolean isFinishButtonDisplayed() {
        boolean displayed = isElementDisplayed(finishButtonLocator);
        logger.debug("Finish button displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if finish button is enabled
     * @return true if finish button is enabled
     */
    public boolean isFinishButtonEnabled() {
        boolean enabled = isElementEnabled(finishButtonLocator);
        logger.debug("Finish button enabled: " + enabled);
        return enabled;
    }

    /**
     * Click back to products button
     */
    public void clickBackToProducts() {
        click(backToProductsButtonLocator);
        logger.info("Clicked back to products button");
    }

    /**
     * Check if back to products button is displayed
     * @return true if back to products button is displayed
     */
    public boolean isBackToProductsButtonDisplayed() {
        boolean displayed = isElementDisplayed(backToProductsButtonLocator);
        logger.debug("Back to products button displayed: " + displayed);
        return displayed;
    }

    // Checkout Information Form Methods

    /**
     * Enter first name
     * @param firstName first name to enter
     */
    public void enterFirstName(String firstName) {
        type(firstNameFieldLocator, firstName);
        logger.info("Entered first name: " + firstName);
    }

    /**
     * Enter last name
     * @param lastName last name to enter
     */
    public void enterLastName(String lastName) {
        type(lastNameFieldLocator, lastName);
        logger.info("Entered last name: " + lastName);
    }

    /**
     * Enter postal code
     * @param postalCode postal code to enter
     */
    public void enterPostalCode(String postalCode) {
        type(postalCodeFieldLocator, postalCode);
        logger.info("Entered postal code: " + postalCode);
    }

    /**
     * Fill checkout information form
     * @param firstName first name
     * @param lastName last name
     * @param postalCode postal code
     */
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        logger.info("Filled checkout information form");
    }

    /**
     * Get first name field value
     * @return first name field value
     */
    public String getFirstName() {
        String firstName = getAttribute(firstNameFieldLocator, "value");
        logger.debug("First name: " + firstName);
        return firstName;
    }

    /**
     * Get last name field value
     * @return last name field value
     */
    public String getLastName() {
        String lastName = getAttribute(lastNameFieldLocator, "value");
        logger.debug("Last name: " + lastName);
        return lastName;
    }

    /**
     * Get postal code field value
     * @return postal code field value
     */
    public String getPostalCode() {
        String postalCode = getAttribute(postalCodeFieldLocator, "value");
        logger.debug("Postal code: " + postalCode);
        return postalCode;
    }

    /**
     * Check if first name field is displayed
     * @return true if first name field is displayed
     */
    public boolean isFirstNameFieldDisplayed() {
        boolean displayed = isElementDisplayed(firstNameFieldLocator);
        logger.debug("First name field displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if last name field is displayed
     * @return true if last name field is displayed
     */
    public boolean isLastNameFieldDisplayed() {
        boolean displayed = isElementDisplayed(lastNameFieldLocator);
        logger.debug("Last name field displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if postal code field is displayed
     * @return true if postal code field is displayed
     */
    public boolean isPostalCodeFieldDisplayed() {
        boolean displayed = isElementDisplayed(postalCodeFieldLocator);
        logger.debug("Postal code field displayed: " + displayed);
        return displayed;
    }

    /**
     * Get error message text
     * @return error message text
     */
    public String getErrorMessage() {
        String errorText = getText(errorMessageLocator);
        logger.info("Error message: " + errorText);
        return errorText;
    }

    /**
     * Check if error message is displayed
     * @return true if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        boolean displayed = isElementDisplayed(errorMessageLocator);
        logger.debug("Error message displayed: " + displayed);
        return displayed;
    }

    // Checkout Overview Methods

    /**
     * Get number of checkout items
     * @return number of checkout items
     */
    public int getCheckoutItemCount() {
        List<WebElement> items = findElements(checkoutItemsLocator);
        int count = items.size();
        logger.debug("Checkout item count: " + count);
        return count;
    }

    /**
     * Get all checkout item names
     * @return list of checkout item names
     */
    public List<String> getAllCheckoutItemNames() {
        List<WebElement> nameElements = findElements(checkoutItemNamesLocator);
        List<String> names = nameElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Checkout item names: " + names);
        return names;
    }

    /**
     * Get all checkout item prices
     * @return list of checkout item prices
     */
    public List<String> getAllCheckoutItemPrices() {
        List<WebElement> priceElements = findElements(checkoutItemPricesLocator);
        List<String> prices = priceElements.stream()
            .map(WebElement::getText)
            .toList();
        logger.debug("Checkout item prices: " + prices);
        return prices;
    }

    /**
     * Get subtotal text
     * @return subtotal text
     */
    public String getSubtotalText() {
        String subtotal = getText(subtotalLabelLocator);
        logger.debug("Subtotal: " + subtotal);
        return subtotal;
    }

    /**
     * Get tax text
     * @return tax text
     */
    public String getTaxText() {
        String tax = getText(taxLabelLocator);
        logger.debug("Tax: " + tax);
        return tax;
    }

    /**
     * Get total text
     * @return total text
     */
    public String getTotalText() {
        String total = getText(totalLabelLocator);
        logger.debug("Total: " + total);
        return total;
    }

    /**
     * Extract subtotal amount
     * @return subtotal amount as double
     */
    public double getSubtotalAmount() {
        String subtotalText = getSubtotalText();
        String amount = subtotalText.replace("Item total: $", "");
        double subtotal = Double.parseDouble(amount);
        logger.debug("Subtotal amount: $" + subtotal);
        return subtotal;
    }

    /**
     * Extract tax amount
     * @return tax amount as double
     */
    public double getTaxAmount() {
        String taxText = getTaxText();
        String amount = taxText.replace("Tax: $", "");
        double tax = Double.parseDouble(amount);
        logger.debug("Tax amount: $" + tax);
        return tax;
    }

    /**
     * Extract total amount
     * @return total amount as double
     */
    public double getTotalAmount() {
        String totalText = getTotalText();
        String amount = totalText.replace("Total: $", "");
        double total = Double.parseDouble(amount);
        logger.debug("Total amount: $" + total);
        return total;
    }

    // Checkout Complete Methods

    /**
     * Get complete header text
     * @return complete header text
     */
    public String getCompleteHeader() {
        String header = getText(completeHeaderLocator);
        logger.debug("Complete header: " + header);
        return header;
    }

    /**
     * Get complete text
     * @return complete text
     */
    public String getCompleteText() {
        String text = getText(completeTextLocator);
        logger.debug("Complete text: " + text);
        return text;
    }

    /**
     * Check if complete header is displayed
     * @return true if complete header is displayed
     */
    public boolean isCompleteHeaderDisplayed() {
        boolean displayed = isElementDisplayed(completeHeaderLocator);
        logger.debug("Complete header displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if complete text is displayed
     * @return true if complete text is displayed
     */
    public boolean isCompleteTextDisplayed() {
        boolean displayed = isElementDisplayed(completeTextLocator);
        logger.debug("Complete text displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if pony express image is displayed
     * @return true if pony express image is displayed
     */
    public boolean isPonyExpressImageDisplayed() {
        boolean displayed = isElementDisplayed(ponyExpressImageLocator);
        logger.debug("Pony express image displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if checkout is complete
     * @return true if checkout is complete
     */
    public boolean isCheckoutComplete() {
        boolean complete = isCompleteHeaderDisplayed() && isCompleteTextDisplayed() && isPonyExpressImageDisplayed();
        logger.debug("Checkout complete: " + complete);
        return complete;
    }

    /**
     * Wait for checkout page to load
     */
    public void waitForCheckoutPageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, pageTitleLocator);
        WaitUtils.waitForElementVisible(driver, cartIconLocator);
        logger.info("Checkout page loaded successfully");
    }

    /**
     * Wait for checkout information page to load
     */
    public void waitForCheckoutInformationPageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, firstNameFieldLocator);
        WaitUtils.waitForElementVisible(driver, lastNameFieldLocator);
        WaitUtils.waitForElementVisible(driver, postalCodeFieldLocator);
        logger.info("Checkout information page loaded successfully");
    }

    /**
     * Wait for checkout overview page to load
     */
    public void waitForCheckoutOverviewPageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, subtotalLabelLocator);
        WaitUtils.waitForElementVisible(driver, taxLabelLocator);
        WaitUtils.waitForElementVisible(driver, totalLabelLocator);
        logger.info("Checkout overview page loaded successfully");
    }

    /**
     * Wait for checkout complete page to load
     */
    public void waitForCheckoutCompletePageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, completeHeaderLocator);
        WaitUtils.waitForElementVisible(driver, completeTextLocator);
        WaitUtils.waitForElementVisible(driver, ponyExpressImageLocator);
        logger.info("Checkout complete page loaded successfully");
    }

    /**
     * Verify checkout information page elements are displayed
     * @return true if all checkout information page elements are displayed
     */
    public boolean verifyCheckoutInformationPageElements() {
        boolean pageTitle = isPageTitleDisplayed();
        boolean cartIcon = isCartIconDisplayed();
        boolean menuButton = isMenuButtonDisplayed();
        boolean firstNameField = isFirstNameFieldDisplayed();
        boolean lastNameField = isLastNameFieldDisplayed();
        boolean postalCodeField = isPostalCodeFieldDisplayed();
        boolean continueButton = isContinueButtonDisplayed();
        boolean cancelButton = isCancelButtonDisplayed();

        boolean allElementsPresent = pageTitle && cartIcon && menuButton && firstNameField && 
                                   lastNameField && postalCodeField && continueButton && cancelButton;
        logger.info("Checkout information page elements verification: " + allElementsPresent);
        return allElementsPresent;
    }

    /**
     * Verify checkout overview page elements are displayed
     * @return true if all checkout overview page elements are displayed
     */
    public boolean verifyCheckoutOverviewPageElements() {
        boolean pageTitle = isPageTitleDisplayed();
        boolean cartIcon = isCartIconDisplayed();
        boolean menuButton = isMenuButtonDisplayed();
        boolean subtotalLabel = isElementDisplayed(subtotalLabelLocator);
        boolean taxLabel = isElementDisplayed(taxLabelLocator);
        boolean totalLabel = isElementDisplayed(totalLabelLocator);
        boolean finishButton = isFinishButtonDisplayed();
        boolean cancelButton = isCancelButtonDisplayed();
        boolean checkoutItems = getCheckoutItemCount() > 0;

        boolean allElementsPresent = pageTitle && cartIcon && menuButton && subtotalLabel && 
                                   taxLabel && totalLabel && finishButton && cancelButton && checkoutItems;
        logger.info("Checkout overview page elements verification: " + allElementsPresent);
        return allElementsPresent;
    }

    /**
     * Verify checkout complete page elements are displayed
     * @return true if all checkout complete page elements are displayed
     */
    public boolean verifyCheckoutCompletePageElements() {
        boolean completeHeader = isCompleteHeaderDisplayed();
        boolean completeText = isCompleteTextDisplayed();
        boolean ponyExpressImage = isPonyExpressImageDisplayed();
        boolean backToProductsButton = isBackToProductsButtonDisplayed();

        boolean allElementsPresent = completeHeader && completeText && ponyExpressImage && backToProductsButton;
        logger.info("Checkout complete page elements verification: " + allElementsPresent);
        return allElementsPresent;
    }

    /**
     * Check if checkout information page is displayed
     * @return true if checkout information page is displayed
     */
    public boolean isCheckoutInformationPageDisplayed() {
        boolean displayed = isElementDisplayed(firstNameFieldLocator) && 
                           isElementDisplayed(lastNameFieldLocator) && 
                           isElementDisplayed(postalCodeFieldLocator);
        logger.debug("Checkout information page displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if checkout overview page is displayed
     * @return true if checkout overview page is displayed
     */
    public boolean isCheckoutOverviewPageDisplayed() {
        boolean displayed = isElementDisplayed(subtotalLabelLocator) && 
                           isElementDisplayed(taxLabelLocator) && 
                           isElementDisplayed(totalLabelLocator);
        logger.debug("Checkout overview page displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if order confirmation page is displayed
     * @return true if order confirmation page is displayed
     */
    public boolean isOrderConfirmationPageDisplayed() {
        boolean displayed = isElementDisplayed(completeHeaderLocator) && 
                           isElementDisplayed(completeTextLocator);
        logger.debug("Order confirmation page displayed: " + displayed);
        return displayed;
    }

    /**
     * Get order confirmation message
     * @return order confirmation message
     */
    public String getOrderConfirmationMessage() {
        String message = getText(completeHeaderLocator);
        logger.debug("Order confirmation message: " + message);
        return message;
    }
} 