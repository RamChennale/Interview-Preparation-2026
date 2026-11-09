package com.saucedemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object representing the SauceDemo Cart page.
 */
public class CartPage extends PageObject {

    @FindBy(css = ".cart_item")
    private List<WebElementFacade> cartItems;

    @FindBy(css = ".inventory_item_name")
    private List<WebElementFacade> cartItemNames;

    @FindBy(id = "checkout")
    private WebElementFacade checkoutButton;

    public List<String> getCartItemNames() {
        return cartItemNames.stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public boolean isItemInCart(String productName) {
        return getCartItemNames().stream()
                .anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    public void clickCheckoutButton() {
        checkoutButton.waitUntilClickable();
        checkoutButton.click();
    }
}
