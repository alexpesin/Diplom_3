package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;
    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    private final By loginLinkOnForgotPasswordPage = By.xpath(".//a[@href='/login']");
    private final By recoverButton= By.xpath(".//form//button[text()='Восстановить']");

    public LoginPage clickLoginLinkOnForgotPasswordPage() {
        driver.findElement(loginLinkOnForgotPasswordPage).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }
    public ForgotPasswordPage waitForLoadForgotPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverButton));
        return new ForgotPasswordPage(driver);
    }
}
