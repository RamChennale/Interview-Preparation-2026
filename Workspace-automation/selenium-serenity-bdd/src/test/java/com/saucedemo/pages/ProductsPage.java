package com.saucedemo.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.By;

/**
 * Page Object representing the SauceDemo Products (Inventory) page.
 * This page is displayed after a successful login.
 */
public class ProductsPage extends PageObject {

    @FindBy(className = "title")
    private WebElementFacade pageTitle;

    @FindBy(css = ".shopping_cart_link")
    private WebElementFacade cartIcon;

    @FindBy(css = ".shopping_cart_badge")
    private WebElementFacade cartBadge;

    // Generic locator pattern: SauceDemo builds the Add-to-cart button id
    // from the product name, e.g. "add-to-cart-sauce-labs-backpack"
    private String addToCartButtonLocator(String productName) {
        String productId = productName.trim().toLowerCase().replaceAll(" ", "-");
        return "add-to-cart-" + productId;
    }

    public boolean isProductsPageDisplayed() {
        return pageTitle.isCurrentlyVisible() && pageTitle.getText().equalsIgnoreCase("Products");
    }

    public void addProductToCart(String productName) {
        WebElementFacade addToCartButton = $(By.id(addToCartButtonLocator(productName)));
        addToCartButton.waitUntilClickable();
        addToCartButton.click();
    }

    public String getCartItemCount() {
        if (cartBadge.isCurrentlyVisible()) {
            return cartBadge.getText();
        }
        return "0";
    }

    public void clickCartIcon() {
        cartIcon.waitUntilClickable();
        cartIcon.click();
    }
}
