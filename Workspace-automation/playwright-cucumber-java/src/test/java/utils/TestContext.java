package utils;

import com.microsoft.playwright.Page;

/**
 * Holds the single Page instance (and factory) for the current scenario.
 * Cucumber-picocontainer creates one TestContext per scenario and injects
 * the same instance into every step definition class constructor, so all
 * step classes share the same Playwright Page.
 */
public class TestContext {

    private Page page;
    private final PlaywrightFactory playwrightFactory;

    public TestContext() {
        this.playwrightFactory = new PlaywrightFactory();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public PlaywrightFactory getPlaywrightFactory() {
        return playwrightFactory;
    }
}
