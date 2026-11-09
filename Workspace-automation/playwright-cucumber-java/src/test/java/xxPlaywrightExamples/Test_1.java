package xxPlaywrightExamples;
import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test_1 {
        public static void main(String[] args) {
            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                Page page = browser.newPage();

                    page.navigate("https://playwright.dev");
                    System.out.println(page.title());
                page.click("//*[@id=\"__docusaurus_skipToContent_fallback\"]/header/div/div/a");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("ex_1.png")));

        }
    }
}
