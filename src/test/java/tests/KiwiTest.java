package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class KiwiTest {

    @Test
    void itShouldOpenMainPage() {
        open("https://www.kiwi.com/en");
        Cookie cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        $(byAttribute("data-test", "LandingSearchButton")).click();
        System.out.println("");
        //1.kliknut na prvy vyskyt/ponuku destinacie
        $(byAttribute("data-test", "PictureCard")).click();
        //2.pockam kym sa mi nacitaju letenky
        $(byAttribute("data-test", "ResultCardWrapper"))
                .waitUntil(Condition.appears, 15000);
        //3.ulozim si cenu letenky do premennej
        //4.otvorim detail letenky a porovnam cenu
    }
}
