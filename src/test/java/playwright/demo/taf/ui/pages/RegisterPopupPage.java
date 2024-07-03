package playwright.demo.taf.ui.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import static playwright.demo.taf.ui.pages.RegisterPopupPage.Element.*;


@Log
public class RegisterPopupPage extends BasePage {

    public RegisterPopupPage() {
        super();
    }

    @Step("Fill register form for:`{email}`")
    public MainPage fillRegisterForm(String email, String password) {
        return fillEmail(email)
                .fillPassword(password)
                .fillPasswordConfirmation(password)
                .registerUser();
    }

    @Step("Fill register form for:`{email}` without correct password")
    public String fillRegisterForm(String email) {
        fillEmail(email)
                .fillPassword(" ")
                .fillPasswordConfirmation(" ");
        page.locator(REGISTER.getLocator()).click();
        String errorText = page.locator(PASSWORD_SHOULD_BE_EQUAL_CONFIRMATION.getLocator()).textContent();

        log.info("errorText: " + errorText);
        return errorText;
    }

    @Step("Fill user email:`{email}`")
    public RegisterPopupPage fillEmail(String email) {
        page.locator(EMAIL.getLocator()).fill(email);
        return this;
    }

    @Step("User click `register` button")
    public MainPage registerUser() {
        page.locator(REGISTER.getLocator()).click();
        return new MainPage();
    }

    @Step("User fill password")
    public RegisterPopupPage fillPassword(String password) {
        page.locator(PASSWORD.getLocator()).fill(password);
        return this;
    }

    @Step("User fill password confirmation")
    public RegisterPopupPage fillPasswordConfirmation(String password) {
        page.locator(PASSWORD_CONFIRMATION.getLocator()).fill(password);
        return this;
    }

    @Getter
    @AllArgsConstructor
    public enum Element {
        EMAIL("#register-form-email"),
        PASSWORD("#register-form-password"),
        PASSWORD_CONFIRMATION("#register-form-password_repeat"),
        REGISTER("#register-form button.form-btn"),
        PASSWORD_SHOULD_BE_EQUAL_CONFIRMATION("div[class*='has-error'] div[class*='afterLeft']");

        private final String locator;
    }
}
