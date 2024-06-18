package api;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;
import io.qameta.allure.Step;

import static api.specs.TestSpecs.requestSpecification;
import static api.specs.TestSpecs.statusCodeResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    @Step("Успешная авторизация")
    public static AuthResponseModel login(String userName, String password) {
        AuthRequestModel authData = new AuthRequestModel();

        authData.setUserName(userName);
        authData.setPassword(password);

        return given(requestSpecification)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(statusCodeResponseSpec(200))
                .extract().as(AuthResponseModel.class);
    }
}
