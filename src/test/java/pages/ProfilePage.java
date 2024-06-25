package pages;

import com.codeborne.selenide.ModalOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement reactTable = $(".ReactTable"),
            deleteButton = $("#delete-record-undefined"),
            closeSmallModalOk = $("#closeSmallModal-ok"),
            userName = $("#userName-value");

    @Step("Открыть профиль")
    public static ProfilePage openProfile() {
        open("/profile");
        return new ProfilePage();
    }

    @Step("Проверить имя юзера в профиле")
    public ProfilePage checkUserName(String username) {
        userName.shouldHave(text(username));
        return this;
    }

    @Step("Проверить наличие добавленной книги в списке")
    public ProfilePage checkBookName(String bookName) {
        reactTable.shouldHave(text(bookName));
        return this;
    }

    @Step("Кликнуть на кнопку \"удалить\"")
    public ProfilePage clickOnDeleteButton() {
        deleteButton.click();
        return this;
    }

    @Step("Нажать на \"да\" в модальном окне")
    public ProfilePage closeModalOk() {
        closeSmallModalOk.click();
        return this;
    }

    @Step("Закрыть модальное окно подтверждения")
    public ProfilePage confirmModalWindow() {
        Selenide.confirm(ModalOptions.withExpectedText("Book deleted."));
        return this;
    }

    @Step("Проверить наличие добавленной книги в списке")
    public void checkList(String bookName) {
        reactTable.shouldNotHave(text(bookName));
    }
}
