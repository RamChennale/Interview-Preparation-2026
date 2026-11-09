package test;
import com.saucedemo.hooks.TestNGBaseTest;
import com.saucedemo.pages.TestNGLoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

    /**
     * Test class for LoginPage.
     * Extends BaseTest, which is assumed to handle WebDriver setup/teardown
     * via @BeforeMethod / @AfterMethod (or @BeforeClass / @AfterClass) and
     * expose a protected `driver` field along with a `baseUrl` property.
     */
    public class TestNGLoginTest extends TestNGBaseTest {

        @Test
        public void verifyAllMenu() {
            TestNGLoginPage verifyHomePage = PageFactory.initElements(driver, TestNGLoginPage.class);
            verifyHomePage.verifyHomePage();
            System.out.println("All menus verified");
        }

    }

