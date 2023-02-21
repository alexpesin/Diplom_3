package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final By burgerIngredientsContainer = By.className("BurgerIngredients_ingredients__1N8v2");
    private final By loginAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By placeOrderButton = By.xpath(".//section[@class='BurgerConstructor_basket__29Cd7 mt-25 ']//button[text()='Оформить заказ']");

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(90))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerIngredientsContainer));
        return new HomePage(driver);
    }

    public LoginPage clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
        return new LoginPage(driver);
    }

    public LoginPage getLoginPage() {
        driver.findElement(loginAccountButton).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }

    public By getPlaceOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return placeOrderButton;
    }
}
