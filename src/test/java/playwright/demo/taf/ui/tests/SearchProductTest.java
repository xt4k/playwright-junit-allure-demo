package playwright.demo.taf.ui.tests;


import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import playwright.demo.taf.ui.annotations.JiraIssue;
import playwright.demo.taf.ui.annotations.JiraIssues;
import playwright.demo.taf.ui.annotations.Layer;

import java.util.List;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.CharSet.EMPTY;


@Epic("Yuriy_L for Playwright taf project demo")
@Feature("This test-set to check functionality `Search product`")
@Story("Web UI tests")
@Tags({@Tag("search-product"), @Tag("regression")})
@Layer("WEB_UI")
@JiraIssues({@JiraIssue("AUTO-011"), @JiraIssue("AUTO-012")})
@Severity(BLOCKER)
@Link(name = "Website", url = "https://example.com/")
@Issue("AUTH-456")
@TmsLink("TMS-789")
public class SearchProductTest extends TestBase {
    @ParameterizedTest
    @CsvSource({
            "Apple iPhone 15, true",
            "Galaxy S24,      true",
            "Nokia 3310,      false"
    })
    @Tag("positive")
    @Owner("yuriy_l")
    @DisplayName("Successful product search")
    void searchProductHappyPathTest(String searchCriteria, boolean isAvailable) {
        List<String> actualPrices = mainPage.searchProduct(searchCriteria)
                .getProductPrices();
        log.info("all for no: " + actualPrices.size());
        log.info("prices: " + actualPrices);

        String description = "Search product: `%s` is%s present on market".formatted(searchCriteria, isAvailable ? EMPTY : " not");
        if (isAvailable) {
            Assertions.assertThat(actualPrices)
                    .as(description)
                    .anyMatch(item -> parseInt(item.split(" ")[0]) > 0);
        } else {
            Assertions.assertThat(actualPrices)
                    .as(description)
                    .isEmpty();
        }
    }

    @Test
    @Owner("yuriy_l")
    @Tag("failed")
    @DisplayName("Example of UI test that find bug: Expected that product will not be present, but it present")
    void searchProductBugTest() {
        String searchCriteria = "monitor audio";

        List<String> actualPrices = mainPage.searchProduct(searchCriteria)
                .getProductPrices();
        log.info("prices: " + actualPrices);

        Assertions.assertThat(actualPrices)
                .as("Search product: `%s` is not present on market".formatted(searchCriteria))
                .isEmpty();
    }


}
