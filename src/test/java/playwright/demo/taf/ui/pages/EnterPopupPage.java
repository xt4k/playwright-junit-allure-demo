package playwright.demo.taf.ui.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static playwright.demo.taf.ui.pages.EnterPopupPage.Element.REGISTER_NEW_USER;


public class EnterPopupPage extends BasePage {

    public EnterPopupPage() {
        super();
    }

    @Step("User going to register")
    public RegisterPopupPage registerNewUser() {
        page.locator(REGISTER_NEW_USER.getLocator()).click();
        return new RegisterPopupPage();
    }


    @Getter
    @AllArgsConstructor
    public enum Element {
        REGISTER_NEW_USER("div.enter-popup-form p a");

        private final String locator;
    }
}
