package browserstack;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class BSConfigHelper {
    private static final Config config = ConfigFactory.load("bs.conf");

    public static String getConfigValue(String key) {
        return config.getString(key);
    }
}
