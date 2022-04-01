package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSoftAssertions {
    @BeforeAll
    public static void beforeAll() {
        String browserSize = System.getProperty("browserSize");
        String remoteBrowser = System.getProperty("remoteBrowser", "selenoid.autotests.cloud/wd/hub");
        String remoteBrowserUser = System.getProperty("remoteBrowserUser", "user1");
        String remoteBrowserPassword = System.getProperty("remoteBrowserPassword", "1234");

        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = browserSize;
        Configuration.remote = "https://" + remoteBrowserUser + ":" + remoteBrowserPassword + "@" + remoteBrowser;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void WikiSoftAssertions() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("/selenide/selenide");
        $("h1").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("#wiki-pages-box").$(".js-wiki-more-pages-link").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")).$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
    }
    @AfterEach
    void addAttachments() {
        AttachAllure.screenshotAs("Last screenshot");
        AttachAllure.pageSource();
        AttachAllure.browserConsoleLogs();
        AttachAllure.addVideo();
        closeWebDriver();
    }
}