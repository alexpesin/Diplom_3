package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final By loginLinkOnForgotPasswordPage = By.xpath(".//a[@href='/login']");
    private final By recoverButton = By.xpath(".//form//button[text()='Восстановить']");

    public ForgotPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку Войти на форме Восстановление пароля")
    public LoginPage clickLoginLinkOnForgotPasswordPage() {
        driver.findElement(loginLinkOnForgotPasswordPage).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }

    @Step("Ожидание загрузки страницы с формой восстановления пароля")
    public ForgotPasswordPage waitForLoadForgotPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverButton));
        return new ForgotPasswordPage(driver);
    }
}
