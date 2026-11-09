package com.bdd.pom.pages;

import com.bdd.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    // module name shown in the top bar, e.g. Dashboard / Admin / PIM / Leave
    private final By pageHeader = By.cssSelector(".oxd-topbar-header-breadcrumb-module");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // parameterized locator: one method works for every left-menu item
    private By menuItem(String menuName) {
        return By.xpath("//span[normalize-space()='" + menuName + "']");
    }

    public void clickMenu(String menuName) {
        click(menuItem(menuName));
    }

    public String getPageHeader() {
        return getText(pageHeader);
    }
}
