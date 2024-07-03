package playwright.demo.taf.ui.pages;


import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import playwright.demo.taf.ui.config.PlaywrightDriver;

import static playwright.demo.taf.ui.pages.BasePage.Locator.LOGIN;
import static playwright.demo.taf.ui.pages.MainPage.Element.LOGGED_USER;


public abstract class BasePage {
    public Page page;

    public BasePage() {
        page = PlaywrightDriver.getPage();
    }


    protected Page getPage() {
        return page = PlaywrightDriver.getPage();
    }

    @Step("User click `login` button")
    public EnterPopupPage clickLoginButton() {
        getPage().locator(LOGIN.getLocator()).click();
        return new EnterPopupPage();
    }

    @Step("Get User account name")
    public String getUserName() {
        return page.locator(LOGGED_USER.getLocator()).innerText();
    }


    @Getter
    @AllArgsConstructor
    public enum Locator {
        LOGIN("span.user-info a");

        private final String locator;
    }

}
