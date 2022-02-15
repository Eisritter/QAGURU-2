package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class AutomationPracticeFormWithPageObjectTests {

    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successFillTest() {
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
        practiceFormPage.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setBirthDate(dateOfBirthMonth, dateOfBirthYear, dateOfBirthDay)
                .setSubject(subjects)
                .setHobbies(hobbies)
                .uploadPicture(fileName)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submitForm();

        //Test that all fields are correct
        practiceFormPage.checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", userEmail)
                .checkForm("Gender", gender)
                .checkForm("Mobile", userNumber)
                .checkForm("Date of Birth", dateOfBirthDay + " " + dateOfBirthMonth + "," + dateOfBirthYear)
                .checkForm("Subjects", subjects)
                .checkForm("Hobbies", hobbies)
                .checkForm("Picture", fileName.getName())
                .checkForm("Address", address)
                .checkForm("State and City", state + " " + city);

        practiceFormPage.closeModal();
    }
}