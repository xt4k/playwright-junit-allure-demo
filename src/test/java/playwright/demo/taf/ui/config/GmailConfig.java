package playwright.demo.taf.ui.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/gmail.properties")
public interface GmailConfig extends Config {

    @Key("email")
    String email();

    @Key("psw")
    String psw();


    @Key("first.name")
    String firstName();

    @Key("last.name")
    String lastName();
}
