package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerBuilderPage {
    private final WebDriver driver;
    private final By burgerIngredientsSection = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']");
    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingTab = By.xpath(".//span[text()='Начинки']/parent::div");

    public BurgerBuilderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("переход к разделу «Булки»")
    public BurgerBuilderPage clickBunsTab() {
        driver.findElement(bunsTab).click();
        return new BurgerBuilderPage(driver);
    }

    @Step("переход к разделу «Соусы»")
    public BurgerBuilderPage clickSauceTab() {
        driver.findElement(sauceTab).click();
        return new BurgerBuilderPage(driver);
    }

    @Step("переход к разделу «Начинки»")
    public BurgerBuilderPage clickFillingTab() {
        driver.findElement(fillingTab).click();
        return new BurgerBuilderPage(driver);
    }

    @Step("проверка, выбран ли раздел «Соусы»")
    public By getSauceTab() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceTab));
        return sauceTab;
    }

    @Step("проверка, выбран ли раздел «Начинки»")
    public By getFillingTab() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingTab));
        return fillingTab;
    }

    @Step("проверка, выбран ли раздел «Булки»")
    public By getBunsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
        return bunsTab;
    }

}
