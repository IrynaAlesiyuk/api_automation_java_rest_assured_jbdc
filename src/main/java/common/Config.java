package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    private static final String env;

    static {
        env = System.getProperty("env", "dev"); // dev by default
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (is == null) {
                throw new RuntimeException("Config file not found");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty(env + ".base.url");
    }
}
