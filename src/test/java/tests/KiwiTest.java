package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class KiwiTest {

    @Test
    void itShouldOpenMainPage() {
        open("https://www.kiwi.com/en");
        var cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        $(byAttribute("data-test", "LandingSearchButton")).click();
        //1.kliknut na prvy vyskyt/ponuku destinacie
        $(byAttribute("data-test", "PictureCard")).click();
        //2.pockam kym sa mi nacitaju letenky
        var wrapper = $(byAttribute("data-test", "ResultCardWrapper"))
                .waitUntil(Condition.appears, 15000);
        //3.ulozim si cenu letenky do premennej
        var price = wrapper.find("strong[class*='PriceText']").shouldNotBe(Condition.empty).getText();
        //4.otvorim detail letenky a porovnam cenu
        wrapper.click();
        $("div[data-test='ModalFooter'] div[class*=FooterPriceWrapper]")
                .shouldHave(Condition.text(price));
        $("div[data-test='ModalFooter']").find(byText("Book"))
                .shouldBe(Condition.enabled)
                .shouldBe(Condition.visible);
    }
    @Test
    void itShouldFindNomadTripAndCheckPriceOnReservation() {
        open("https://www.kiwi.com/en/nomad");
        var cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        //1.klikni na tlacidlo show popular journeys
        $(byText("Show popular journeys")).click();
        //2.vyber cestu po azii
        $$("div[class*=ExampleItemstyled]")
                .shouldHave(sizeGreaterThan(1))
                .find(Condition.text("Asia"))
                .click();
        //3.pockaj kym sa zobrazi booking button a klikni nan
        $(byAttribute("data-test", "BookingButton"))
                .waitUntil(Condition.appears, 15000)
                .click();
        //4.over ze cena na registracii nie je prazdna
        $("div.ReservationBill-item-price")
                .waitUntil(Condition.appears, 15000)
                .shouldNotBe(Condition.empty);
    }

    @Test
    void itShouldChangeCurrencyAndCheckPrice() {
        open("https://www.kiwi.com/en/");
        var cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
        //1.zmen menu a vyber si japonsky yen
        $(byAttribute("data-test", "Currency-Open")).click();
        $(byAttribute("data-test", "Currency-Item-jpy")).click();
        $(byAttribute("data-test", "Currency")).shouldHave(Condition.text("JPY"));
        //2.ako cielovu destinaciu vyber tokyo
        //2a. zadaj do druheho inputu tokyo
        //2b. zaklikni prvy checkbox
        $$(byAttribute("data-test", "SearchField-input"))
                .get(1)
                .val("Tokyo");
        $("div[data-test='PlacePickerInput-destination'] input").val("Tokyo");
        $(byAttribute("data-test", "PlacePickerRowCheckbox")).click();
        //3.klikni na search button
        $(byText("Search")).click();
        //4.pockaj kym sa nacitaju letenky (vyuzi metodu z prveho testu)
        var wrapper = $(byAttribute("data-test", "ResultCardWrapper"))
                .waitUntil(Condition.appears, 15000);
        //5.over ze cena obsahuje menu japonsky yen
        wrapper.find("strong[class*='PriceText']").shouldHave(Condition.text("¥"));
    }

    @AfterEach
    void quitDriver() {
        WebDriverRunner.getWebDriver().quit();
    }
}
