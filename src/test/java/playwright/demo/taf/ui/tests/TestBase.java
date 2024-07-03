package playwright.demo.taf.ui.tests;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import playwright.demo.taf.ui.config.Configs;
import playwright.demo.taf.ui.config.PlaywrightDriver;
import playwright.demo.taf.ui.helpers.Attach;
import playwright.demo.taf.ui.pages.MainPage;

import java.util.Date;
import java.util.Optional;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static playwright.demo.taf.ui.config.Configs.pwDriverConfig;
import static playwright.demo.taf.ui.config.PlaywrightDriver.closeBrowser;
import static playwright.demo.taf.ui.config.PlaywrightDriver.quitPlaywright;


public abstract class TestBase {
    protected static final Logger log = LoggerFactory.getLogger(TestBase.class);
    private final String shotName = "-%s".formatted(new Date().toInstant()).replace(":", "-");
    protected MainPage mainPage = new MainPage();

    @RegisterExtension
    AfterTestExecutionCallback callback =
            context -> {
                Optional<Throwable> exception = context.getExecutionException();
                if (exception.isPresent()) {
                    Attach.screenshotAs("Screenshot-fail-" + shotName);
                    Attach.stopTracing("Trace-fail-" + shotName);
                }
            };

    @BeforeAll
    public static void setup() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", System.getProperty("os.name"))
                        .put("Version", System.getProperty("os.version"))
                        .put("Browser", StringUtils.capitalize(Configs.pwDriverConfig.browser()))
                        .put("Site URL", Configs.pwDriverConfig.siteUrl())
                        .put("", "")
                        .build(),
                Configs.pwDriverConfig.allureResultsDir() + "/");
    }

    @Step("user navigated to website")
    public static void navigateToSiteUrl() {
        PlaywrightDriver.openPage(pwDriverConfig.siteUrl());
    }

    @AfterAll
    public static void tearDown() {
        quitPlaywright();
    }

    @BeforeEach
    public void startDriver() {
        PlaywrightDriver.setupDriver();
        navigateToSiteUrl();
    }

    @AfterEach()
    public void afterEach() {
        Attach.stopTracing("Trace-" + shotName);
        closeBrowser();
    }
}
