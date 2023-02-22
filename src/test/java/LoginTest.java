import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.data.User;
import org.example.pom.HeaderPage;
import org.example.pom.HomePage;
import org.example.pom.PersonalAccountPage;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends TestBase {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка, что пользователь может успешно войти на сайт под своим логином через кнопку «Войти в аккаунт» на главной странице")
    public void userSighInViaAccountButtonWithValidDataShouldSuccessTest() {
        User user = registerNewUser();

        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());

        headerPage.clickStellarBurgersLink();
        homePage
                .clickLoginAccountButton()
                .waitForLoadLoginPage()
                .setUserEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .clickLoginButton();
        waitForLoadHomePage();


        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка, что пользователь может успешно войти на сайт под своим логином через кнопку «Личный кабинет»")
    public void userSighInViaPersonalAccountButtonWithValidDataShouldSuccessTest() {
        User user = registerNewUser();

        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());

        headerPage
                .clickPersonalAccountLink()
                .waitForLoadLoginPage()
                .setUserEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .clickLoginButton();
        waitForLoadHomePage();

        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка, что пользователь может успешно войти на сайт под своим логином через кнопку в форме регистрации")
    public void userSighInViaRegisterFormPageLoginButtonWithValidDataShouldSuccessTest() {
        User user = registerNewUser();

        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());
        headerPage.clickStellarBurgersLink();

        headerPage
                .clickPersonalAccountLink()
                .waitForLoadLoginPage()
                .clickRegisterLink()
                .waitForLoadRegisterPage()
                .clickLoginLinkOnRegisterFormPage()
                .waitForLoadLoginPage()
                .setUserEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .clickLoginButton();
        waitForLoadHomePage();

        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка, что пользователь может успешно войти на сайт под своим логином через кнопку в форме восстановления пароля")
    public void userSighInViaForgotPasswordPageLoginButtonWithValidDataShouldSuccessTest() {
        User user = registerNewUser();

        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());
        headerPage.clickStellarBurgersLink();

        headerPage
                .clickPersonalAccountLink()
                .waitForLoadLoginPage()
                .clickForgotPasswordLink()
                .waitForLoadForgotPasswordPage()
                .clickLoginLinkOnForgotPasswordPage()
                .waitForLoadLoginPage()
                .setUserEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .clickLoginButton();
        waitForLoadHomePage();

        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
        //переход в личный кабинет залогиненного пользователя
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(getDriver());
        headerPage
                .clickPersonalAccountLink();
        personalAccountPage
                .waitForLoadPersonalAccountPage();

        String expectedUserNameAttributeValue = user.getName();
        String actualUserNameAttributeValue = personalAccountPage.getElementAttributeValue(personalAccountPage.getUserNameInputField(), "value");
        Assert.assertEquals("Ожидаемое имя залогиненного пользователя не соответсвует актуальному", expectedUserNameAttributeValue, actualUserNameAttributeValue);

        String expectedUserEmailAttributeValue = user.getEmail();
        String actualUserEmailAttributeValue = personalAccountPage.getElementAttributeValue(personalAccountPage.getUserLoginInputField(), "value");
        Assert.assertEquals("Ожидаемый логин (email) залогиненного пользователя не соответсвует актуальному", expectedUserEmailAttributeValue, actualUserEmailAttributeValue);
    }

}
