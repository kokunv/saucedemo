package sauce.com.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class TestValueProvider {

    Properties properties;
    private static final String CONFIG_PROPERTIES_PATH = "src/test/resources/config.properties";

    public TestValueProvider() {
        properties = new Properties();
        try (
                FileInputStream fileInputStream = new FileInputStream(CONFIG_PROPERTIES_PATH)) {
            properties.load(fileInputStream);

        } catch (IOException err) {
            log.error("ERROR Reading {} ", err.getMessage() + " use system env");
            throw new RuntimeException("Cannot read config file: " + err.getMessage());
        }
    }

    public String getBaseUIUrl() {
        return properties != null ? properties.getProperty("base.ui.url") : System.getenv("BASE_UI_URL");
    }

    public String getUserName() {
        return properties != null ? properties.getProperty("user.name") : System.getenv("USER_NAME");
    }

    public String getUserPassword() {
        return properties != null ? properties.getProperty("user.password") : System.getenv("USER_PASSWORD");
    }

    public String getBrowser() {
        return properties != null ? properties.getProperty("browser.name") : System.getenv("BROWSER_NAME");
    }

}
