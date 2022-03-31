package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class AutomationPracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        String browserSize = System.getProperty("browserSize");

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    @Disabled
    void successFillTest() {
        step("Open home page", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Enter name", () -> {
            $("#firstName").setValue("Roma");
        });

        step("Enter lastname", () -> {
            $("#lastName").setValue("TestLast");
        });

        step("Enter email", () -> {
            $("#userEmail").setValue("roma@test.com");
        });

        step("Enter user number", () -> {
            $("#userNumber").setValue("0123456789");
        });

        step("Select gender", () -> {
            $("#gender-radio-1").doubleClick();
        });

        step("Select  birthdate", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOptionByValue("4");
            $(".react-datepicker__year-select").selectOptionByValue("1980");
            $(".react-datepicker__day--025").click();
        });

        step("Enter subjects", () -> {
            $("#subjectsInput").setValue("Physics").pressEnter();
        });

        step("Enter hobbies", () -> {
            $("[for='hobbies-checkbox-1']").click();
        });


        step("Enter address", () -> {
            $("#currentAddress").setValue("Some address");
        });

        step("Select state", () -> {
            $("#react-select-3-input").setValue("NCR").pressEnter();
        });

        step("Select city", () -> {
            $("#react-select-4-input").setValue("Delhi").pressEnter();
        });


        step("submit click", () -> {
            $("#submit").click();
        });

        step("Form check", () -> {
            $(".table-responsive").shouldHave(text("Anton Donsk"));
            $(".table-responsive").shouldHave(text("Alex85@mmail.com"));
            $(".table-responsive").shouldHave(text("1234567890"));
            $(".table-responsive").shouldHave(text("25 may,1980"));
            $(".table-responsive").shouldHave(text("Physics"));
            $(".table-responsive").shouldHave(text("Sports"));
//            $(".table-responsive").shouldHave(text("ava.jpg"));
            $(".table-responsive").shouldHave(text("Moskov"));
            $(".table-responsive").shouldHave(text("NCR Delhi"));
        });

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