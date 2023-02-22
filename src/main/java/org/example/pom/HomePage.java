package org.example.pom;

import io.qameta.allure.Step;
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

    @Step("ожидание загрузки страницы")
    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(90))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerIngredientsContainer));
        return new HomePage(driver);
    }

    @Step("Клик по кнопке «Войти в аккаунт»")
    public LoginPage clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Переход на странцу с формой логина")
    public LoginPage getLoginPage() {
        driver.findElement(loginAccountButton).click();
        return new LoginPage(driver)
                .waitForLoadLoginPage();
    }

    @Step("Проверка, что на главной странице после успешного логина есть кнопка «Оформить заказ»")
    public By getPlaceOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return placeOrderButton;
    }
}
