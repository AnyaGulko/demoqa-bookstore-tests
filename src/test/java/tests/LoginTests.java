package tests;

import api.AuthorizationApi;
import api.BooksApi;
import api.models.AuthResponseModel;
import helpers.CookieManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ProfileSteps;


public class LoginTests extends TasteBase {

    @Test
    @DisplayName("Удаление книги из списка в профиле")
    void addBookToCollectionTest() {
        AuthResponseModel response = AuthorizationApi.login(TestData.userName, TestData.password);
        BooksApi.deleteAllBooks(response);
        BooksApi.addBook(response, TestData.gitPocketGuideIsbn);
        CookieManager.setCookiesToWebDriver(response);

        ProfileSteps.openProfile()
                .checkUserName(TestData.userName)
                .checkBookName(TestData.gitPocketGuideName)
                .clickOnDeleteButton()
                .closeModalOk()
                .confirmModalWindow()
                .checkList(TestData.gitPocketGuideName);
    }
}
