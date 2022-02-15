package pages.components;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class CalendarComponent {
    public void setDate(String dateOfBirthMonth, String dateOfBirthYear, String dateOfBirthDay) {
        $(".react-datepicker__month-select").selectOption(dateOfBirthMonth);
        $(".react-datepicker__year-select").selectOption(dateOfBirthYear);
        $(byText(dateOfBirthDay)).click();
    }
}
