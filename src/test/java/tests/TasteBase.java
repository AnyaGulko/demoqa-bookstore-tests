package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TasteBase {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl =  "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void shutDown(){
        closeWebDriver();
    }
}
