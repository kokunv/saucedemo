package sauce.com.pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
@Getter
public class LoginPage extends Base {

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='user-name' and contains(@class, 'input_error')]")
    private WebElement usernameErrorIcon;

    @FindBy(xpath = "//input[@id='password' and contains(@class, 'input_error')]")
    private WebElement passwordErrorIcon;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement appLogo;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        log.debug("LoginPage initialized");
    }


    public void clickLoginButton() {
        waitUntilElementClickable(loginButton);
        loginButton.click();
        log.info("Click login button");
    }


    public MainPage login(String username, String password) {
        log.info("Start login with username: {}", username);
        waitUntilElementVisible(usernameInput);
        usernameInput.sendKeys(username);
        waitUntilElementVisible(passwordInput);
        passwordInput.sendKeys(password);
        waitUntilElementClickable(loginButton);
        loginButton.click();
        log.info("Login success");

        return new MainPage(driver);
    }
}
