import org.example.data.User;
import org.example.pom.HeaderPage;
import org.example.pom.HomePage;
import org.example.pom.PersonalAccountPage;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends TestBase {

    @Test
    public void userSighInViaAccountButtonWithValidDataShouldSuccessTest() {
        User user = registerNewUser();

        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());

        headerPage.clickStellarBurgersLink();
        // userLogin
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
    public void userSighInViaPersonalAccountButtonWithValidDataShouldSuccessTest() {
        User user = registerNewUser();

        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());
        //headerPage.clickStellarBurgersLink();
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
