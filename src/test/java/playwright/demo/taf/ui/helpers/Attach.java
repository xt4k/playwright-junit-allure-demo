package playwright.demo.taf.ui.helpers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import playwright.demo.taf.ui.tests.TestBase;

import java.nio.file.Paths;

import static playwright.demo.taf.ui.config.PlaywrightDriver.getBrowserContext;
import static playwright.demo.taf.ui.config.PlaywrightDriver.getPage;


public class Attach {
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{shotName}", type = "image/png")
    public static byte[] screenshotAs(String shotName) {
        byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./build/screenshot/%s.png".formatted(shotName))));
        return screenshot;
    }

    public static void stopTracing(String traceArchiveName) {
        getBrowserContext().tracing()
                .stop(new Tracing.StopOptions()
                        .setPath(Paths.get("./build/tracing/%s.zip".formatted(traceArchiveName))));
        getBrowserContext().close();
    }

    public static void captureVideo(Browser browser, String videoPath) {

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
        Page page = context.newPage();

    }
}