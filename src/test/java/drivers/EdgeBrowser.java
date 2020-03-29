package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeBrowser implements WebDriverProvider {
    public static final String USERNAME = "matko5";
    public static final String AUTOMATE_KEY = "wba3Hey1qW54Lt5Hz67U";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(URL), getCapabilites());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static DesiredCapabilities getCapabilites() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Edge");
        caps.setCapability("browser_version", "80.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        return caps;
    }
}
