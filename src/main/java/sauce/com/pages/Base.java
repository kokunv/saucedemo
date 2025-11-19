package sauce.com.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public abstract class Base {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitUntilElementVisible(WebElement element) {
        log.debug("Wait visibility for element: {}", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementClickable(WebElement element) {
        log.debug("Wait clickable for element: {}", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
