package playwright.demo.taf.ui.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/playwright.properties")
public interface PlaywrightDriverConfig extends Config {

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("page_loading_strategy")
    String pageLoadingStrategy();

    @Key("locale")
    String locale();

    @Key("language")
    String language();

    @Key("remote_execution")
    String remoteExecution();

    @Key("timeout")
    Integer timeout();

    @Key("base_url")
    String siteUrl();

    @Key("allure_results_directory")
    String allureResultsDir();
}
