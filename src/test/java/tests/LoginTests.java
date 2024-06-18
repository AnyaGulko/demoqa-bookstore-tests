package tests;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;
import com.codeborne.selenide.Condition;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v117.network.model.AuthChallengeResponse;

import static api.specs.TestSpecs.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;


public class LoginTests extends TasteBase{

    @Test
    @DisplayName("Успешная авторизация")
    void addBookToCollectionTest(){
        AuthRequestModel authData = new AuthRequestModel();

        authData.setUserName(TestData.userName);
        authData.setPassword(TestData.password);

        AuthResponseModel response =
                given(requestSpecification)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(statusCodeResponseSpec(200))
                        .extract().as(AuthResponseModel.class);


        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + response.getToken())
                .queryParams("UserId", response.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204)
                .extract().response();



        String isbn = "9781449325862";
        String bookData = format("{\n" +
                "  \"userId\": \"%s\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"%s\"\n" +
                "    }\n" +
                "  ]\n" +
                "}", response.getUserId(), isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + response.getToken())
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().response();




        open("/favicon.png");
        WebDriver.Options options = getWebDriver().manage();
        options.addCookie(new Cookie("userID", response.getUserId()));
        options.addCookie(new Cookie("expires", response.getExpires()));
        options.addCookie(new Cookie("token", response.getToken()));

        open("/profile");
        $(".ReactTable").shouldHave(Condition.text("Git Pocket Guide"));



        String deleteBookData = format("{\n" +
                "  \"userId\": \"%s\",\n" +
                "      \"isbn\": \"%s\"\n" +
                "    }\n", response.getUserId(), isbn);

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + response.getToken())
                .body(deleteBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204)
                .extract().response();

        open("/profile");
        $(".ReactTable").shouldHave(Condition.empty);
    }
}
