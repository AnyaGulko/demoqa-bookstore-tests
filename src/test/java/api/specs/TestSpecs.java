package api.specs;

import helpers.extensions.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class TestSpecs {
    public static RequestSpecification requestSpecification = with()
            .filter(CustomAllureListener.withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);

    public static ResponseSpecification statusCodeResponseSpec(int statusCode) {

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
        return responseSpecification;
    }
}
