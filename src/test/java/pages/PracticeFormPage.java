package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {
    // components
    private final CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    private SelenideElement
            headerTitle = $(".main-header"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            subjectsDropdown = $("#subjectsInput"),
            chooseFileButton = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive"),
            closeModalButton = $("#closeLargeModal");


    // actions
    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        headerTitle.shouldHave(text("Practice Form"));
        return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public PracticeFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }
    public PracticeFormPage setGender(String gender) {
        $(byText(gender)).click();
        return this;
    }

    public PracticeFormPage setUserNumber (String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }
    public PracticeFormPage setBirthDate(String dateOfBirthMonth, String dateOfBirthYear, String dateOfBirthDay) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(dateOfBirthMonth, dateOfBirthYear, dateOfBirthDay);
        return this;
    }

    public PracticeFormPage setSubject (String subjectsInput) {
        subjectsDropdown.setValue(subjectsInput).pressEnter();
        return this;
    }
    public PracticeFormPage setHobbies (String hobby) {
        $(byText(hobby)).click();
        return this;
    }

    public PracticeFormPage uploadPicture (File uploadPicture) {
        chooseFileButton.uploadFile(uploadPicture);
        return this;
    }
    public PracticeFormPage setCurrentAddress (String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }
    public PracticeFormPage setState (String state) {
        stateDropdown.scrollTo().click();
        $(byText(state)).click();
        return this;
    }
    public PracticeFormPage setCity (String city) {
        cityDropdown.click();
        $(byText(city)).click();
        return this;
    }
    public PracticeFormPage submitForm() {
        submitButton.click();
        return this;
    }
    public PracticeFormPage checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName)).parent().shouldHave(text(value));
        return this;
    }
    public PracticeFormPage closeModal() {
        closeModalButton.click();
        return this;
    }
}
