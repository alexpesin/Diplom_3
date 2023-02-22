import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.example.data.User;
import org.example.data.UserGenerator;
import org.example.pom.HeaderPage;
import org.example.pom.HomePage;
import org.example.pom.LoginPage;
import org.example.pom.PersonalAccountPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {
    private static WebDriver driver;
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public static void setUp() {
        //запуск Chrome браузера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //запуск Yandex браузера

        /*System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();*/

         /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);*/

       /* WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();*/
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Step("ожидание загрузки главной страницы")
    public static void waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(90))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("BurgerIngredients_ingredients__1N8v2")));
    }

    @Step("Регистрация пользователя")
    public static User registerNewUser() {
        User user = UserGenerator.generateUser();

        HomePage homePage = new HomePage(getDriver());
        homePage
                .getLoginPage()
                .getRegisterPage()
                .signUp(user.getName(), user.getEmail(), user.getPassword())
                .waitForLoadLoginPage();
        return new User(user.getName(), user.getEmail(), user.getPassword());
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getLOGIN_PAGE() {
        return BASE_URL + "login";
    }

    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAttributePresent(By locator, String attribute, String value) {

        boolean result = false;
        try {
            String attr = driver
                    .findElement(locator)
                    .getAttribute(attribute);
            if (attr.contains(value)) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    @Step("Логин на сайт под пользователем")
    public HomePage userSignIn(User user) {
        LoginPage loginPage = new LoginPage(driver);
        getLOGIN_PAGE();
        loginPage
                .waitForLoadLoginPage()
                .setUserEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .clickLoginButton();
        return new HomePage(driver)
                .waitForLoadHomePage();
    }

    @Step("Открыть Личный кабинет пользователя")
    public PersonalAccountPage openPersonalAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage
                .clickPersonalAccountLink();
        personalAccountPage
                .waitForLoadPersonalAccountPage();
        return new PersonalAccountPage(driver)
                .waitForLoadPersonalAccountPage();
    }

    @Before
    public void openHomePage() {
        setUp();
        getDriver().get(getBASE_URL());
        waitForLoadHomePage();
    }

    @After
    public void tearDownAfterTest() {
        getDriver().quit();
    }
}
