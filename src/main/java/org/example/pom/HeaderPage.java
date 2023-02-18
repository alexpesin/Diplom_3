package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {
    private final WebDriver driver;
    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    private final By stellarBurgersLink = By.className("AppHeader_header__logo__2D0X2");
    private final By personalAccountLink = By.xpath(".//a[@href='/account']");
    //private final By burgerBuilderLink = By.xpath(".//nav//p[text()='Конструктор']");
    private final By burgerBuilderLink = By.xpath(".//nav//p[text()='Конструктор']/parent::a");

    public HomePage clickStellarBurgersLink() {
        driver.findElement(stellarBurgersLink).click();
        return new HomePage(driver)
                .waitForLoadHomePage();
    }
    public LoginPage clickPersonalAccountLink() {
        driver.findElement(personalAccountLink).click();
        return new LoginPage(driver);
    }
    public HomePage clickBurgerBuilderLink() {
        driver.findElement(burgerBuilderLink).click();
        return new HomePage(driver)
                .waitForLoadHomePage();
    }
    public By getBurgerBuilderLink() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerBuilderLink));
        return burgerBuilderLink;
    }
}
