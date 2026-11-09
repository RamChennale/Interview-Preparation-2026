package pages;

import com.microsoft.playwright.Page;

/**
 * Page Object covering the three checkout screens:
 *  - checkout-step-one.html  (Your Information)
 *  - checkout-step-two.html  (Overview / Finish)
 *  - checkout-complete.html  (Thank you page)
 */
public class CheckoutPage {

    private final Page page;

    private final String firstNameInput = "#first-name";
    private final String lastNameInput = "#last-name";
    private final String zipInput = "#postal-code";
    private final String continueButton = "#continue";
    private final String finishButton = "#finish";
    private final String completeHeader = ".complete-header";

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void enterCheckoutInfo(String firstName, String lastName, String zip) {
        page.fill(firstNameInput, firstName);
        page.fill(lastNameInput, lastName);
        page.fill(zipInput, zip);
    }

    public void clickContinue() {
        page.click(continueButton);
    }

    public void clickFinish() {
        page.click(finishButton);
    }

    public String getOrderConfirmationMessage() {
        return page.textContent(completeHeader).trim();
    }
}
