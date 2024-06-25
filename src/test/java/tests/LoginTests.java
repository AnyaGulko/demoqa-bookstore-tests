package tests;

import api.AuthorizationApi;
import api.BooksApi;
import api.models.AuthResponseModel;
import helpers.CookieManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@Tag("delete_book_from_list")
@Feature("Книги в профиле")
public class LoginTests extends TasteBase {

    @Test
    @Story("Удаление книг")
    @Owner("Анна Гулько")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Удаление книги из списка в профиле")
    void addBookToCollectionTest() {
        AuthResponseModel response = AuthorizationApi.login(TestData.userName, TestData.password);
        BooksApi.deleteAllBooks(response);
        BooksApi.addBook(response, TestData.gitPocketGuideIsbn);
        CookieManager.setCookiesToWebDriver(response);

        ProfilePage.openProfile()
                .checkUserName(TestData.userName)
                .checkBookName(TestData.gitPocketGuideName)
                .clickOnDeleteButton()
                .closeModalOk()
                .confirmModalWindow()
                .checkList(TestData.gitPocketGuideName);
    }
}
