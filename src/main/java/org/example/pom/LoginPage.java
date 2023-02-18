package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    By loginButton = By.xpath(".//form//button[text()='Войти']");
    By registerLink = By.cssSelector("a.Auth_link__1fOlj[href ='/register']");
    By emailInputField = By.xpath(".//form//label[text()='Email']/following-sibling::input[@type='text']");
    By passwordInputField = By.xpath(".//form//input[@type='password']");
    By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password']");

    public LoginPage waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return new LoginPage(driver);
    }
    public By getLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return loginButton;
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public RegisterPage clickRegisterLink() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver).waitForLoadRegisterPage();
    }
    public RegisterPage getRegisterPage() {
        driver.findElement(registerLink).click();
        return new RegisterPage(driver).waitForLoadRegisterPage();
    }

    public LoginPage setUserEmail(String userEmail) {
        driver.findElement(emailInputField).sendKeys(userEmail);
        return this;
    }

    public LoginPage setUserPassword(String userPassword) {
        driver.findElement(passwordInputField).sendKeys(userPassword);
        return this;
    }
    public ForgotPasswordPage clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver)
                .waitForLoadForgotPasswordPage();
    }



}
