import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pom.BurgerBuilderPage;
import org.junit.Assert;
import org.junit.Test;


public class BurgerBuilderTest extends TestBase {

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка, что пользователь может переходить к разделу «Булки» по клику. Дефолтный раздел некликабелен." +
            "поэтому есть проверка на дефолтность и если раздел дефолтный, то сначала перехожу на другой")
    public void getBunsTabShouldSuccessTest() {
        BurgerBuilderPage burgerBuilderPage = new BurgerBuilderPage(getDriver());
        String attributeName = "class";
        String value = "tab_type_current";
        if (isAttributePresent(burgerBuilderPage.getBunsTab(), attributeName, value)) {
            burgerBuilderPage
                    .clickSauceTab();
        }
        burgerBuilderPage
                .clickBunsTab();

        Assert.assertTrue("Пользователь должен перейти в раздел «Булки»", isAttributePresent(burgerBuilderPage.getBunsTab(), attributeName, value));
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка, что пользователь может переходить к разделу «Соусы» по клику по клику")
    public void getSauceTabShouldSuccessTest() {
        BurgerBuilderPage burgerBuilderPage = new BurgerBuilderPage(getDriver());
        burgerBuilderPage
                .clickSauceTab();
        String attributeName = "class";
        String value = "tab_type_current";
        Assert.assertTrue("Пользователь должен перейти в раздел 'Соусы'", isAttributePresent(burgerBuilderPage.getSauceTab(), attributeName, value));
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка, что пользователь может переходить к разделу «Начинки» по клику")
    public void getFillingTabShouldSuccessTest() {
        BurgerBuilderPage burgerBuilderPage = new BurgerBuilderPage(getDriver());
        burgerBuilderPage
                .clickFillingTab();
        String attributeName = "class";
        String value = "tab_type_current";
        Assert.assertTrue("Пользователь должен перейти в раздел 'Начинки'", isAttributePresent(burgerBuilderPage.getFillingTab(), attributeName, value));
    }
}
