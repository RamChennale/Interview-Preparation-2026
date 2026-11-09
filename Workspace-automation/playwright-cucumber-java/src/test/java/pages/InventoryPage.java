package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object for https://www.saucedemo.com/inventory.html
 */
public class InventoryPage {

    private final Page page;

    private final String cartIcon = ".shopping_cart_link";
    private final String inventoryUrlFragment = "inventory.html";
    private final String productName = "#add-to-cart-sauce-labs-backpack";

    public InventoryPage(Page page) {
        this.page = page;
    }

    /**
     * Builds a dynamic XPath locator for the "Add to cart" button that
     * belongs to the inventory item whose visible name matches productName.
     */
    private String addToCartButtonFor(String productName) {
        return "//div[@class='inventory_item_name' and text()='" + productName + "']"
                + "/ancestor::div[@class='inventory_item']//button";
    }

    public void addProductToCart() {
        page.click(productName);
    }

    public void clickCartIcon() {
        page.click(cartIcon);
    }

    public boolean isOnInventoryPage() {
        return page.url().contains(inventoryUrlFragment);
    }
}
