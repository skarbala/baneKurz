package drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserBase {
    private static final String USERNAME = "matko5";
    private static final String AUTOMATE_KEY = "wba3Hey1qW54Lt5Hz67U";

    public static String getURL() {
        return URL;
    }

    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private static DesiredCapabilities capabilities;

    public static DesiredCapabilities getCapabilities() {
        if (capabilities == null) {
            capabilities = new DesiredCapabilities();
        }
        return capabilities;
    }
}
