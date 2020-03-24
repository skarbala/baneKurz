package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class KiwiTest {

    @Test
    void itShouldOpenMainPage() {
        open("https://www.kiwi.com/en");
        Cookie cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        $(byAttribute("data-test", "LandingSearchButton")).click();
        //1.kliknut na prvy vyskyt/ponuku destinacie
        $(byAttribute("data-test", "PictureCard")).click();
        //2.pockam kym sa mi nacitaju letenky
        SelenideElement wrapper = $(byAttribute("data-test", "ResultCardWrapper"))
                .waitUntil(Condition.appears, 15000);
        //3.ulozim si cenu letenky do premennej
        String price = wrapper.find("strong[class*='PriceText']").shouldNotBe(Condition.empty).getText();
        //4.otvorim detail letenky a porovnam cenu
        wrapper.click();
        $("div[data-test='ModalFooter'] div[class*=FooterPriceWrapper]")
                .shouldHave(Condition.text(price));
        $("div[data-test='ModalFooter']").find(byText("Book"))
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.visible);
    }


    public static void main(String[] args) {

    }
}
