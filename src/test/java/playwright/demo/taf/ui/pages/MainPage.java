package playwright.demo.taf.ui.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.microsoft.playwright.options.LoadState.NETWORKIDLE;
import static playwright.demo.taf.ui.pages.MainPage.Element.ITEMS_AVAILABILITY;
import static playwright.demo.taf.ui.pages.MainPage.Element.SEARCH;

public class MainPage extends BasePage {

    public MainPage() {
        super();
    }


    @Step("User file search field with criteria `{criteria}` and press `Enter`")
    public MainPage searchProduct(String criteria) {
        getPage().locator(SEARCH.getLocator()).fill(criteria);
        getPage().keyboard().press("Enter");

        return new MainPage();
    }


    @Step("User get info about product presence`")
    public List<String> getProductPrices() {
        page.waitForLoadState(NETWORKIDLE);
        return page.locator(ITEMS_AVAILABILITY.getLocator()).allInnerTexts();
    }

    @Step("User get info about product presence`")
    public void priceIsPresent() {
        page.waitForLoadState(NETWORKIDLE);
        assertThat(page.locator(ITEMS_AVAILABILITY.getLocator())).containsText("грн");
    }


    @Getter
    @AllArgsConstructor
    public enum Element {
        ITEMS_AVAILABILITY("a.price"),
        SEARCH("input.search-text-input"),
        REGISTER_NEW_USER("div.enter-popup-form p a"),
        LOGGED_USER("a[data-toggle='dropdown']");


        private final String locator;
    }
}
