package api;

import api.models.AddBookRequestModel;
import api.models.AuthResponseModel;
import io.qameta.allure.Step;

import java.util.List;

import static api.specs.TestSpecs.requestSpecification;
import static api.specs.TestSpecs.statusCodeResponseSpec;
import static io.restassured.RestAssured.given;

public class BooksApi {
    @Step("Удалить все книги в списке")
    public static void deleteAllBooks(AuthResponseModel authData) {
        given(requestSpecification)
                .header("Authorization", "Bearer " + authData.getToken())
                .queryParams("UserId", authData.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponseSpec(204));
    }

    @Step("Добавить книгу")
    public static void addBook(AuthResponseModel authData, String isbn){
        AddBookRequestModel request = new AddBookRequestModel();

        request.setUserId(authData.getUserId());
        request.setCollectionOfIsbns(List.of(new AddBookRequestModel.Isbn(isbn)));

        given(requestSpecification)
                .header("Authorization", "Bearer " + authData.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponseSpec(201));
    }
}
