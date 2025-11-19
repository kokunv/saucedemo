package tests;

import sauce.com.pages.LoginPage;
import sauce.com.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends TestRunner {

    public static Stream<Arguments> usernameData() {
        return Stream.of(
                Arguments.of("visual_user", testValueProvider.getUserPassword()),
                Arguments.of("standard_user", testValueProvider.getUserPassword()),
                Arguments.of("performance_glitch_user", testValueProvider.getUserPassword())
        );
    }

    @Test
    public void smokeTest() {
        LoginPage loginPage = loadApplication();

        assertThat(loginPage.getAppLogo().isDisplayed()).isTrue();
    }

    @Test
    public void loginWithEmptyCredentials() {

        LoginPage loginPage = loadApplication();

        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage().isDisplayed()).isTrue();
        assertThat(loginPage.getUsernameErrorIcon().isDisplayed()).isTrue();
        assertThat(loginPage.getPasswordErrorIcon().isDisplayed()).isTrue();

    }

    @Test
    public void loginWithoutPassword() {

        LoginPage loginPage = loadApplication();

        loginPage.login("standard_user", "");

        assertThat(loginPage.getErrorMessage().isDisplayed()).isTrue();
        assertThat(loginPage.getPasswordErrorIcon().isDisplayed()).isTrue();

    }

    @ParameterizedTest
    @MethodSource("usernameData")
    public void successfulLogin(String username, String password) {
        LoginPage loginPage = loadApplication();

        MainPage mainPage = loginPage.login(username, password);

        assertThat(mainPage.getAppLogo().isDisplayed()).isTrue();
        assertThat(mainPage.getShoppingCartLink().isDisplayed()).isTrue();
    }

    @Test
    public void loginLockOutUser() {
        LoginPage loginPage = loadApplication();

        loginPage.login("locked_out_user", testValueProvider.getUserPassword());

        assertThat(loginPage.getErrorMessage().isDisplayed()).isTrue();
        assertThat(loginPage.getUsernameErrorIcon().isDisplayed()).isTrue();
    }
}
