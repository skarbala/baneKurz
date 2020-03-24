package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class KiwiTest {

    @Test
    void itShouldOpenMainPage() {
        open("https://www.kiwi.com/en");
        $(byAttribute("data-test", "LandingSearchButton")).click();
    }
}
