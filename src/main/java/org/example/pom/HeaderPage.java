package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {
    private final WebDriver driver;
    private final By stellarBurgersLink = By.className("AppHeader_header__logo__2D0X2");
    private final By personalAccountLink = By.xpath(".//a[@href='/account']");

    private final By burgerBuilderLink = By.xpath(".//nav//p[text()='Конструктор']/parent::a");

    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("переход на главную страницу")
    public HomePage clickStellarBurgersLink() {
        driver.findElement(stellarBurgersLink).click();
        return new HomePage(driver)
                .waitForLoadHomePage();
    }

    @Step("Кликнуть на кнопку «Личный кабинет»")
    public LoginPage clickPersonalAccountLink() {
        driver.findElement(personalAccountLink).click();
        return new LoginPage(driver);
    }

    @Step("переход в конструктор")
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
