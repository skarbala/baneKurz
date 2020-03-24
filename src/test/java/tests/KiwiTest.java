package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class KiwiTest {

    @Test
    void itShouldOpenMainPage() {
        open("https://www.kiwi.com/en");
        Cookie cookie = new Cookie("cookie_consent","agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        $(byAttribute("data-test","LandingSearchButton")).click();
    }
}
