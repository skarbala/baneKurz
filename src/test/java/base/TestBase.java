package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import drivers.BrowserBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import static utils.Utils.generateBuildName;

@ExtendWith(Watcher.class)
public class TestBase {

    private static SessionId sessionId;

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

    public static SessionId getSessionId() {
        return sessionId;
    }

    @AfterEach
    void quitDriver() {
        sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        WebDriverRunner.getWebDriver().quit();
    }
}
