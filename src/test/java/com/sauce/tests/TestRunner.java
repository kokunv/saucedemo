package com.sauce.tests;

import com.sauce.driver.DriverSingleton;
import com.sauce.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import com.sauce.utils.TestValueProvider;

@Slf4j
public class TestRunner {

    protected static boolean isTestSuccessful = false;

    protected WebDriver driver;
    protected static TestValueProvider testValueProvider = new TestValueProvider();


    @BeforeEach
    public void setUp(){
        driver = DriverSingleton.getDriver();
        log.info("Get driver");

    }

    @AfterEach
    public void afterEach(TestInfo testInfo){
        if (!isTestSuccessful){
            log.error("Test_name: {}  fail", testInfo.getDisplayName());
            log.error("Test_method: {}  fail",testInfo.getTestMethod());
        }

        DriverSingleton.closeDriver();
        log.info("Close driver");
    }


    protected LoginPage loadApplication(){
        driver.get(testValueProvider.getBaseUIUrl());
        log.info("Opening url: {}",testValueProvider.getBaseUIUrl());
        return new LoginPage(driver);
    }


}
