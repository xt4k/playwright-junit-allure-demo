package playwright.demo.taf.ui.config;


import com.microsoft.playwright.*;
import io.qameta.allure.Step;

import java.nio.file.Paths;

public class PlaywrightDriver {
    public static PlaywrightDriver playwrightDriver;
    public static ThreadLocal<Playwright> pw = new ThreadLocal<>();
    public static ThreadLocal<Browser> br = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> bc = new ThreadLocal<>();
    private static ThreadLocal<Page> pg = new ThreadLocal<>();
    public Page page;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;


    private PlaywrightDriver() {
        playwright = Playwright.create();
        pw.set(playwright);

        browser = createBrowser();
        br.set(browser);

        browserContext = startVideoRecoding(browser);//browser.newContext();
        startTracing();
        bc.set(browserContext);

        page = getBrowser().newPage();
        pg.set(page);
    }

    public static Playwright getPlaywright() {
        return pw.get();
    }

    public static Browser getBrowser() {
        return br.get();
    }

    public static BrowserContext getBrowserContext() {
        return bc.get();
    }

    public static Page getPage() {
        return pg.get();
    }

    public static void setupDriver() {
        playwrightDriver = new PlaywrightDriver();
    }

    @Step("open url: {url}")
    public static void openPage(String url) {
        if (getPage() != null) {
            getPage().navigate(url);
        } else
            getBrowser().newPage().navigate(url);
    }

    public static void closeBrowser() {
        getBrowser().close();
        getPage().close();
    }

    public static void quitPlaywright() {
        if (getPage() != null) {
            getPlaywright().close();
        }

    }

    private Browser createBrowser() {
        PlaywrightDriverConfig config = Configs.pwDriverConfig;
        BrowserType browserType;
        switch (config.browser().toLowerCase()) {
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;
            case "chromium":
            default:
                browserType = playwright.chromium();
                break;
        }

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options.setHeadless(config.headless());
        return browserType.launch(options);
    }

    public BrowserContext startVideoRecoding(Browser browser) {
        return browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("./build/videos/")));
    }


    public void startTracing() {
        browserContext.tracing()
                .start(new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(false));

    }

}
