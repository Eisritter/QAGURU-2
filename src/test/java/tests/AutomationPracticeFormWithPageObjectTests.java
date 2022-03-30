package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.PracticeFormPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class AutomationPracticeFormWithPageObjectTests {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    void successFillTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        String firstName = "Roma";
        String lastName = "TestLast";
        String userEmail = "roma@test.com";
        String gender = "Male";
        String userNumber = "0123456789";
        String dateOfBirthDay = "22";
        String dateOfBirthMonth = "April";
        String dateOfBirthYear = "1997";
        String subjects = "Arts";
        String hobbies = "Music";
        File fileName = new File("src/test/resources/img/PictureForTests.jpg");
        String address = "Some address";
        String state = "Haryana";
        String city = "Karnal";

        practiceFormPage.openPage();
        //Complete all fields on form
        step("Insert First Name " + firstName, () -> {
            practiceFormPage.setFirstName(firstName);
        });
        step("Insert Last Name " + lastName, () -> {
            practiceFormPage.setLastName(lastName);
        });
        step("Insert User Email " + userEmail, () -> {
            practiceFormPage.setUserEmail(userEmail);
        });
        step("Check Gender " + gender, () -> {
            practiceFormPage.setGender(gender);
        });
        step("Insert User Number " + userNumber, () -> {
            practiceFormPage.setUserNumber(userNumber);
        });
        step("Check Birthday Date " + dateOfBirthDay + dateOfBirthMonth + dateOfBirthYear, () -> {
            practiceFormPage.setBirthDate(dateOfBirthMonth, dateOfBirthYear, dateOfBirthDay);
        });
        step("Check Subject " + subjects, () -> {
            practiceFormPage.setSubject(subjects);
        });
        step("Check Hobbies " + hobbies, () -> {
            practiceFormPage.setHobbies(hobbies);
        });
//        step("Upload Picture " + fileName, () -> {
//            practiceFormPage.uploadPicture(fileName);
//        });
        step("Insert Current Address " + address, () -> {
            practiceFormPage.setCurrentAddress(address);
        });
        step("Insert State " + state, () -> {
            practiceFormPage.setState(state);
        });
        step("Insert City " + city, () -> {
            practiceFormPage.setCity(city);
        });
        step("Click Button ", () -> {
            practiceFormPage.submitForm();
        });

//                .setLastName(lastName)
//                .setUserEmail(userEmail)
//                .setGender(gender)
//                .setUserNumber(userNumber)
//                .setBirthDate(dateOfBirthMonth, dateOfBirthYear, dateOfBirthDay)
//                .setSubject(subjects)
//                .setHobbies(hobbies)
//                .uploadPicture(fileName)
//                .setCurrentAddress(address)
//                .setState(state)
//                .setCity(city)
//                .submitForm();


        //Test that all fields are correct
        step("Assert data Modal Table", () -> {
        practiceFormPage.checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", userEmail)
                .checkForm("Gender", gender)
                .checkForm("Mobile", userNumber)
                .checkForm("Date of Birth", dateOfBirthDay + " " + dateOfBirthMonth + "," + dateOfBirthYear)
                .checkForm("Subjects", subjects)
                .checkForm("Hobbies", hobbies)
                //.checkForm("Picture", fileName.getName())
                .checkForm("Address", address)
                .checkForm("State and City", state + " " + city);

        practiceFormPage.closeModal();
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