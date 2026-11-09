package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Page Object for https://www.saucedemo.com/cart.html
 */
public class CartPage {

    private final Page page;

    private final String cartItemNames = ".inventory_item_name";
    private final String checkoutButton = "#checkout";

    public CartPage(Page page) {
        this.page = page;
    }

    public boolean isProductInCart(String productName) {
        Locator items = page.locator(cartItemNames);
        List<String> names = items.allTextContents();
        return names.contains(productName);
    }

    public void clickCheckout() {
        page.click(checkoutButton);
    }
}
