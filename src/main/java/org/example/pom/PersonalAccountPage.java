package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;
    public PersonalAccountPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    private final By logoutButton = By.xpath(".//nav//button[text()='Выход']");
    private final By userNameInputField = By.xpath(".//div[@class='Account_contentBox__2CPm3']//label[text()='Имя']/following-sibling::input[@type='text']");
    private final By userLoginInputField = By.xpath(".//div[@class='Account_contentBox__2CPm3']//label[text()='Логин']/following-sibling::input[@type='text']");

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
    public LoginPage clickLogOutButton() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }
    public PersonalAccountPage waitForLoadPersonalAccountPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return new PersonalAccountPage(driver);
    }
    public String getElementAttributeValue(By locator, String attribute){
        return driver
                .findElement(locator)
                .getAttribute(attribute);
    }

}
