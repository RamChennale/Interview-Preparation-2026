package xxPlaywrightExamples;

import com.microsoft.playwright.Page;
import org.junit.Assert;

public class Page_Interface_PreDefined {

    private final Page page;
    String userName_Locator;
    String loginButton_Locator;
    String confirm_Locator ;


    public Page_Interface_PreDefined(Page page) {
        this.page = page;
    }

    public void Page_Interface_IMP_Functions(){
        page.navigate("http://www.google.com");

        page.fill(userName_Locator, "Ram");

        page.click(loginButton_Locator);

       String expectedMessage =  page.textContent(confirm_Locator).trim();

       /* Assertion */
        Assert.assertEquals("Order confirmation message did not match",
                expectedMessage, page.textContent(confirm_Locator).trim());


        page.close();

    }
}
