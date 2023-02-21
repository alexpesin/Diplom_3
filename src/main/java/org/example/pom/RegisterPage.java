package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;


    private final By authForm = By.className("Auth_login__3hAey");
    private final By authButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By nameInputField = By.xpath(".//form//label[text()='Имя']/following-sibling::input[@type='text']");
    private final By emailInputField = By.xpath(".//form//label[text()='Email']/following-sibling::input[@type='text']");

    private final By passwordInputField = By.xpath(".//form//input[@type='password']");

    private final By invalidUserPasswordErrorMessage = By.xpath(".//*[text() = 'Некорректный пароль']");
    private final By loginLinkOnRegisterPage = By.xpath(".//a[@href='/login']");


    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //methods
    public RegisterPage setUsername(String username) {
        driver.findElement(nameInputField).sendKeys(username);
        return this;
    }

    public RegisterPage setUserEmail(String userEmail) {
        driver.findElement(emailInputField).sendKeys(userEmail);
        return this;
    }

    public RegisterPage setUserPassword(String userPassword) {
        driver.findElement(passwordInputField).sendKeys(userPassword);
        return this;
    }

    public RegisterPage clickSignInButton() {
        driver.findElement(authButton).click();
        return this;
    }

    public LoginPage signUp(String username, String userEmail, String password) {
        setUsername(username);
        setUserEmail(userEmail);
        setUserPassword(password);
        clickSignInButton();
        return new LoginPage(driver);
    }

    public RegisterPage waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(authForm));
        return new RegisterPage(driver);
    }

    public By getSignUpButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(authButton));
        return authButton;
    }

    public String getInvalidUserPasswordErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(invalidUserPasswordErrorMessage));
        return driver.findElement(invalidUserPasswordErrorMessage).getText();
    }

    public LoginPage clickLoginLinkOnRegisterFormPage() {
        driver.findElement(loginLinkOnRegisterPage).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }
}
