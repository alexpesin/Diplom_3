package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By logoutButton = By.xpath(".//nav//button[text()='Выход']");
    private final By userNameInputField = By.xpath(".//div[@class='Account_contentBox__2CPm3']//label[text()='Имя']/following-sibling::input[@type='text']");
    private final By userLoginInputField = By.xpath(".//div[@class='Account_contentBox__2CPm3']//label[text()='Логин']/following-sibling::input[@type='text']");

    public PersonalAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public By getUserNameInputField() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(userNameInputField));
        return userNameInputField;
    }

    public By getUserLoginInputField() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(userLoginInputField));
        return userLoginInputField;
    }

    @Step("Клик по кнопке Выхода из аккаунта")
    public LoginPage clickLogOutButton() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }

    @Step("Ожидание загрузки страницы Личного кабинета")
    public PersonalAccountPage waitForLoadPersonalAccountPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return new PersonalAccountPage(driver);
    }

    public String getElementAttributeValue(By locator, String attribute) {
        return driver
                .findElement(locator)
                .getAttribute(attribute);
    }

}
