package playwright.demo.taf.ui.helpers;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import io.qameta.allure.Attachment;

import java.nio.file.Paths;

import static playwright.demo.taf.ui.config.PlaywrightDriver.getBrowserContext;
import static playwright.demo.taf.ui.config.PlaywrightDriver.getPage;


public class Attach {
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
}