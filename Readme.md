## This project has UI tests for login to the website https://www.saucedemo.com/ 

## Technology and Patterns
* ** Test Automation tool: Selenium WebDriver;
* **Project Builder: Maven;
* **Browsers: Firefox; Edge; Chrome
* **Locators: XPath;
* **Test Runner: JUnit;
* **Assertions: AssertJ;
* **Design pattern:
    * **Page Object Model - use for organisational element
    * **Singleton - for managing the browser and executing parallel tests

## Key Features of the Framework
  what we have in this project
* package driver where we manage our browsers
* package pages where we describe our elements and methods for them, and some business logic that is needed for our tests
* package utils where we have a class testValueProvider that gives data from local properties or the system
* and the main package with our tests and testRunner
    



