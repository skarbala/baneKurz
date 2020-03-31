package drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

import static browserstack.BSConfigHelper.getConfigValue;

public class BrowserBase {

    public static String getURL() {
        return getConfigValue("base-url");
    }

    private static DesiredCapabilities capabilities;

    public static DesiredCapabilities getCapabilities() {
        if (capabilities == null) {
            capabilities = new DesiredCapabilities();
        }
        return capabilities;
    }
}
