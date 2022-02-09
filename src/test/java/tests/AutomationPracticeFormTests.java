package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class AutomationPracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successFillTest() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        //Complete all fields on form
        $("#firstName").setValue("Roma");
        $("#lastName").setValue("TestLast");
        $("#userEmail").setValue("roma@test.com");
        $x("//*[@for='gender-radio-1']").click(); //choose male
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day--022").click();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $x("//*[@for='hobbies-checkbox-3']").click(); //choose music
        $("#uploadPicture").uploadFile(new File("src/test/data/PictureForTests.jpg"));
        $("#currentAddress").setValue("Some address");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        //Test that all fields are correct
        $(".table-responsive").shouldHave(
                text("Roma TestLast"),
                text("roma@test.com"),
                text("Male"),
                text("0123456789"),
                text("22 April,1997"),
                text("Arts"),
                text("Music"),
                text("PictureForTests.jpg"),
                text("Some address"),
                text("Haryana Karnal")
        );
        $("#closeLargeModal").click();
    }
}
