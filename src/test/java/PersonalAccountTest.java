import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.data.User;
import org.example.pom.HeaderPage;
import org.example.pom.HomePage;
import org.example.pom.LoginPage;
import org.example.pom.PersonalAccountPage;
import org.junit.Assert;
import org.junit.Test;

public class PersonalAccountTest extends TestBase {
    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    @Description("Проверка, что залогиненный пользователь может успешно войти в «Личный кабинет»")
    public void openPersonalAccountAsLoggedUserShouldSuccessTest() {
        HomePage homePage = new HomePage(getDriver());
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(getDriver());
        User user = registerNewUser();
        userSignIn(user);
        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
        openPersonalAccount();
        String expectedUserNameAttributeValue = user.getName();
        String actualUserNameAttributeValue = personalAccountPage.getElementAttributeValue(personalAccountPage.getUserNameInputField(), "value");
        Assert.assertEquals("Ожидаемое имя залогиненного пользователя не соответсвует актуальному", expectedUserNameAttributeValue, actualUserNameAttributeValue);

        String expectedUserEmailAttributeValue = user.getEmail();
        String actualUserEmailAttributeValue = personalAccountPage.getElementAttributeValue(personalAccountPage.getUserLoginInputField(), "value");
        Assert.assertEquals("Ожидаемый логин (email) залогиненного пользователя не соответсвует актуальному", expectedUserEmailAttributeValue, actualUserEmailAttributeValue);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверка, что залогиненный пользователь может перейти из «Личного кабинета» в конструктор")
    public void openBurgerBuilderPageFromPersonalAccountPageShouldSuccessTest() {
        HomePage homePage = new HomePage(getDriver());
        HeaderPage headerPage = new HeaderPage(getDriver());

        User user = registerNewUser();
        userSignIn(user);
        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
        openPersonalAccount();
        headerPage.clickBurgerBuilderLink();

        String attributeName = "class";
        String value = "link_active";
        Assert.assertTrue(isAttributePresent(headerPage.getBurgerBuilderLink(), attributeName, value));
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Проверка выхода из аккаунта по кнопке «Выйти» в личном кабинете")
    public void signOutFromPersonalAccountPageShouldSuccessTest() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());

        User user = registerNewUser();
        userSignIn(user);
        Assert.assertTrue("Пользователь должен перейти на главную страницу ", isElementPresent(homePage.getPlaceOrderButton()));
        openPersonalAccount()
                .clickLogOutButton();
        Assert.assertTrue("Пользователь должен перейти на логин страницу ", isElementPresent(loginPage.getLoginButton()));
    }
}
