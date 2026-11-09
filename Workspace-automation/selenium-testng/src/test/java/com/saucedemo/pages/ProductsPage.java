package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object representing the SauceDemo Products (Inventory) page.
 * This page is displayed after a successful login.
 */
public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // SauceDemo builds the Add-to-cart button id from the product name,
    // e.g. "add-to-cart-sauce-labs-backpack"
    private String addToCartButtonLocator(String productName) {
        String productId = productName.trim().toLowerCase().replaceAll(" ", "-");
        return "add-to-cart-" + productId;
    }

    public boolean isProductsPageDisplayed() {
        return waitUntilVisible(pageTitle).getText().equalsIgnoreCase("Products");
    }

    public void addProductToCart(String productName) {
        WebElement addToCartButton = driver.findElement(By.id(addToCartButtonLocator(productName)));
        waitUntilClickable(addToCartButton).click();
    }

    public String getCartItemCount() {
        try {
            return cartBadge.getText();
        } catch (Exception notDisplayed) {
            return "0";
        }
    }

    public void clickCartIcon() {
        waitUntilClickable(cartIcon).click();
    }
}
