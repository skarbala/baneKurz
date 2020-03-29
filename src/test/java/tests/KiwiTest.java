package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import drivers.BrowserBase;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class KiwiTest {

    @BeforeAll
    static void config() {
        Configuration.baseUrl = "https://www.kiwi.com/en";
        Configuration.startMaximized = true;
        Configuration.browser = "drivers.EdgeBrowser";
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        var testName = testInfo.getDisplayName();
        BrowserBase.getCapabilities().setCapability("name", testName);
    }

    @Test
    @DisplayName("Check that price equals on result and detail page")
    void itShouldCheckThatPriceEqualsOnResultAndDetail() {
        open("/");
        setCookieConsent();
        refresh();
//        $(byDataTestAttribute("LandingSearchButton")).click();
//        //1.kliknut na prvy vyskyt/ponuku destinacie
//        $(byDataTestAttribute("PictureCard")).click();
//        //2.pockam kym sa mi nacitaju letenky
//        var wrapper = $(byDataTestAttribute("ResultCardWrapper"))
//                .waitUntil(Condition.appears, 15000);
//        //3.ulozim si cenu letenky do premennej
//        var price = wrapper.find("strong[class*='PriceText']").shouldNotBe(Condition.empty).getText();
//        //4.otvorim detail letenky a porovnam cenu
//        wrapper.click();
//        $("div[data-test='ModalFooter'] div[class*=FooterPriceWrapper]")
//                .shouldHave(Condition.text(price));
//        $("div[data-test='ModalFooter']").find(byText("Book"))
//                .shouldBe(Condition.enabled)
//                .shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Check that price is displayed on nomad reservation")
    void itShouldFindNomadTripAndCheckPriceOnReservation() {
        open("/nomad");
        setCookieConsent();
        refresh();
        //1.vyber cestu po azii
        $$("div[class*=ExampleItemstyled]")
                .shouldHave(sizeGreaterThan(1))
                .find(Condition.text("Asia"))
                .click();
        //2.pockaj kym sa zobrazi booking button a klikni nan
        $(byDataTestAttribute("BookingButton"))
                .waitUntil(Condition.appears, 15000)
                .click();
        //3.over ze cena na registracii nie je prazdna
        $("div.ReservationBill-item-price")
                .waitUntil(Condition.appears, 15000)
                .shouldNotBe(Condition.empty);
    }

    @Test
    @DisplayName("Check that changed currency is displayed on result")
    void itShouldChangeCurrencyAndCheckPrice() {
        open("/");
        setCookieConsent();
        refresh();
        //1.zmen menu a vyber si japonsky yen
        $(byDataTestAttribute("Currency-Open")).click();
        $(byDataTestAttribute("Currency-Item-jpy")).click();
        $(byDataTestAttribute("Currency")).shouldHave(Condition.text("JPY"));
        //2.ako cielovu destinaciu vyber tokyo
        //2a. zadaj do druheho inputu tokyo
        //2b. zaklikni prvy checkbox
        $$(byDataTestAttribute("SearchField-input"))
                .get(1)
                .val("Tokyo");
        $("div[data-test='PlacePickerInput-destination'] input").val("Tokyo");
        $(byDataTestAttribute("PlacePickerRowCheckbox")).click();
        //3.klikni na search button
        $(byText("Search")).click();
        //4.pockaj kym sa nacitaju letenky (vyuzi metodu z prveho testu)
        var wrapper = $(byDataTestAttribute("ResultCardWrapper"))
                .waitUntil(Condition.appears, 15000);
        //5.over ze cena obsahuje menu japonsky yen
        wrapper.find("strong[class*='PriceText']").shouldHave(Condition.text("¥"));
    }

    @AfterEach
    void quitDriver() {
        WebDriverRunner.getWebDriver().quit();
    }

    private By byDataTestAttribute(String landingSearchButton) {
        return byAttribute("data-test", landingSearchButton);
    }

    private void setCookieConsent() {
        var cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }


}
