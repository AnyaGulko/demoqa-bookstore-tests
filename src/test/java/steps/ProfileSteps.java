package steps;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ModalOptions;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import pages.ProfilePage;
import tests.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class ProfileSteps {

    ProfilePage profilePage = new ProfilePage();

    @Step("Открыть профиль")
    public static ProfileSteps openProfile() {
        open("/profile");
        return new ProfileSteps();
    }

    @Step("Проверить имя юзера в профиле")
    public ProfileSteps checkUserName(String userName) {
        profilePage.getUserName().shouldHave(text(userName));
        return this;
    }

    @Step("Проверить наличие добавленной книги в списке")
    public ProfileSteps checkBookName(String bookName) {
        profilePage.getReactTable().shouldHave(text(bookName));
        return this;
    }

    @Step("Кликнуть на кнопку \"удалить\"")
    public ProfileSteps clickOnDeleteButton() {
        profilePage.getDeleteButton().click();
        return this;
    }

    @Step("Нажать на \"да\" в модальном окне")
    public ProfileSteps closeModalOk() {
        profilePage.getCloseSmallModalOk().click();
        return this;
    }

    @Step("Закрыть модальное окно подтверждения")
    public ProfileSteps confirmModalWindow() {
        Selenide.confirm(ModalOptions.withExpectedText("Book deleted."));
        return this;
    }

    @Step("Проверить наличие добавленной книги в списке")
    public void checkList(String bookName) {
        profilePage.getReactTable().shouldNotHave(text(bookName));
    }
}
