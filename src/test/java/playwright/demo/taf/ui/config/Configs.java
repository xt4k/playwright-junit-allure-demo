package playwright.demo.taf.ui.config;

import org.aeonbits.owner.ConfigFactory;

public class Configs {
    public static PlaywrightDriverConfig pwDriverConfig = ConfigFactory.create(PlaywrightDriverConfig.class, System.getProperties());

    public static GmailConfig gmail = ConfigFactory.create(GmailConfig.class, System.getProperties());


}
