package helpers;

import api.models.AuthResponseModel;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CookieManager {
    @Step("Установить Cookie")
    public static void setCookiesToWebDriver(AuthResponseModel authData) {
        open("/favicon.png");
        WebDriver.Options options = getWebDriver().manage();
        options.addCookie(new Cookie("userID", authData.getUserId()));
        options.addCookie(new Cookie("expires", authData.getExpires()));
        options.addCookie(new Cookie("token", authData.getToken()));
    }
}
