import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.data.User;
import org.example.data.UserGenerator;
import org.example.pom.HomePage;
import org.example.pom.LoginPage;
import org.example.pom.RegisterPage;
import org.junit.Assert;
import org.junit.Test;


public class RegistrationTest extends TestBase {


    @Test
    @DisplayName("Регистрация нового пользователя с невалидными данными выдает ошибку")
    @Description("Проверка ошибки для некорректного пароля. Минимальный пароль — шесть символов")
    public void userSighUpWithInvalidPasswordLessThenSixSymbolsShouldFailTest() {
        int invalidPasswordLessThenSixSymbols = UserGenerator.generateUserPasswordLength(1, 5);
        String name = UserGenerator.generateUserName();
        String email = UserGenerator.generateUserEmail();
        String password = UserGenerator.generateUserPassword(invalidPasswordLessThenSixSymbols);
        User user = new User(name, email, password);

        HomePage homePage = new HomePage(getDriver());
        RegisterPage registerPage = new RegisterPage(getDriver());
        homePage
                .getLoginPage()
                .getRegisterPage()
                .setUsername(user.getName())
                .setUserEmail(user.getEmail())
                .setUserPassword(user.getPassword())
                .clickSignInButton();
        Assert.assertTrue("Пользователь должен остаться на той же странице", isElementPresent(registerPage.getSignUpButton()));
        String expectedErrorMessage = "Некорректный пароль";
        String actualErrorMessage = registerPage.getInvalidUserPasswordErrorMessage();
        Assert.assertEquals("Неверное сообщение об ошибке: ", expectedErrorMessage, actualErrorMessage);

    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Description("Проверка что пользователь успешно зарегистрирован")
    public void userSighUpValidDataShouldSuccessTest() {
        int validPassword = UserGenerator.generateUserPasswordLength(6, 15);
        String name = UserGenerator.generateUserName();
        String email = UserGenerator.generateUserEmail();
        String password = UserGenerator.generateUserPassword(validPassword);
        User user = new User(name, email, password);

        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        homePage
                .getLoginPage()
                .getRegisterPage()
                .signUp(user.getName(), user.getEmail(), user.getPassword())
                .waitForLoadLoginPage();

        Assert.assertTrue("Пользователь должен перейти на страницу логина", isElementPresent(loginPage.getLoginButton()));

    }

}
