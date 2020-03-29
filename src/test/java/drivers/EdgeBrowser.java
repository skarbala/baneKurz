package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static drivers.BrowserBase.getURL;

public class EdgeBrowser implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(getURL()), getCapabilites());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static DesiredCapabilities getCapabilites() {
        DesiredCapabilities caps = BrowserBase.getCapabilities();
        caps.setCapability("browser", "Edge");
        caps.setCapability("browser_version", "80.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        return caps;
    }
}
