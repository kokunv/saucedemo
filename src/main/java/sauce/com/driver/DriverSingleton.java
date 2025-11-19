package sauce.com.driver;

import sauce.com.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;

@Slf4j
public class DriverSingleton {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD = new ThreadLocal<>();
    private static final String EDGE_DRIVER_PATH = "src/test/resources/msedgedriver.exe";


    public DriverSingleton() {
    }


    public static WebDriver getDriver() {
        if (DRIVER_THREAD.get() == null) {
            long threadId = Thread.currentThread().threadId();
            log.info("Thread {}  Initializing new driver", threadId);
            TestValueProvider testValueProvider = new TestValueProvider();
            String browser = Optional.ofNullable(testValueProvider.getBrowser())
                    .orElse("edge")
                    .toLowerCase();
            WebDriver driverInstance = createDriver(browser);
            DRIVER_THREAD.set(driverInstance);

            log.info("Thread: {} initialized successful with browser: {}", threadId, browser);
        }
        return DRIVER_THREAD.get();
    }

    private static WebDriver createDriver(String browser) {
        WebDriver driverInstance;

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverInstance = new ChromeDriver();
                break;
            case "edge":
            default:
                System.setProperty("webdriver.edge.driver", EDGE_DRIVER_PATH);
                driverInstance = new EdgeDriver();
                break;
        }
        driverInstance.manage().window().maximize();
        return driverInstance;
    }

    public static void closeDriver() {
        WebDriver driver = DRIVER_THREAD.get();

        if (driver != null) {
            long threadId = Thread.currentThread().threadId();
            log.info("[Thread {}] Quitting driver and cleaning up ThreadLocal storage.", threadId);

            driver.quit();
            DRIVER_THREAD.remove();
        }
    }
}
