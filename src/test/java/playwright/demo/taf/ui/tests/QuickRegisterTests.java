package playwright.demo.taf.ui.tests;


import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import playwright.demo.taf.ui.annotations.JiraIssue;
import playwright.demo.taf.ui.annotations.JiraIssues;
import playwright.demo.taf.ui.annotations.Layer;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Yuriy_L for Playwright taf project demo")
@Feature("This test-set to check functionality `Site quick User registration`")
@Story("Web UI tests")
@Tags({@Tag("quick-register"), @Tag("regression")})
@Layer("WEB_UI")
@JiraIssues({@JiraIssue("AUTO-003"), @JiraIssue("AUTO-004")})
@Severity(CRITICAL)
@Link(name = "Website", url = "https://example.com/")
@Issue("AUTH-123")
@TmsLink("TMS-456")
public class QuickRegisterTests extends TestBase {

    private Faker faker = new Faker();
    private final String password = faker.internet().password();
    private String email = faker.internet().emailAddress();

    @Test
    @Tag("positive")
    @Owner("yuriy_l")
    @DisplayName("Successful quick register")
    void quickRegisterHappyPathTest() {
        String registeredUser = mainPage.clickLoginButton()
                .registerNewUser()
                .fillRegisterForm(email, password)
                .getUserName();
        assertThat(registeredUser)
                .as("quick registered user")
                .isSubstringOf(email);
    }

    @Test
    @Owner("yuriy_l")
    @Tag("negative")
    @DisplayName("Negative test for `quick register` - too short password")
    void quickRegisterNegativeTest() {
        String message = mainPage.clickLoginButton()
                .registerNewUser()
                .fillRegisterForm(email);

        assertThat(message)
                .as("quick registered user error")
                .isNotEmpty();
    }

}
