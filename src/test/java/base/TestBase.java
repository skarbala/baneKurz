package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import drivers.BrowserBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static utils.Utils.generateBuildName;

public class TestBase {

    @BeforeAll
    static void config() {
        BrowserBase.getCapabilities().setCapability("build", generateBuildName());
        Configuration.baseUrl = "https://www.kiwi.com/en";
        Configuration.startMaximized = true;
        Configuration.browser = "drivers.EdgeBrowser";
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        var testName = testInfo.getDisplayName();
        BrowserBase.getCapabilities().setCapability("name", testName);
    }

    @AfterEach
    void quitDriver() {
        WebDriverRunner.getWebDriver().quit();
    }
}
