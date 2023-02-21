import org.example.pom.BurgerBuilderPage;
import org.junit.Assert;
import org.junit.Test;


public class BurgerBuilderTest extends TestBase {

    /*
     * таб с булками выбран по дефолту,  а дефолтный таб не кликабельный
     * поэтому добавил проверку и перехожу на другой
     */
    @Test

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

        Assert.assertTrue("Пользователь должен перейти в раздел 'Булки'", isAttributePresent(burgerBuilderPage.getBunsTab(), attributeName, value));
    }

    @Test
    public void getSauceTabShouldSuccessTest() {
        BurgerBuilderPage burgerBuilderPage = new BurgerBuilderPage(getDriver());
        burgerBuilderPage
                .clickSauceTab();
        String attributeName = "class";
        String value = "tab_type_current";
        Assert.assertTrue("Пользователь должен перейти в раздел 'Соусы'", isAttributePresent(burgerBuilderPage.getSauceTab(), attributeName, value));
    }

    @Test
    public void getFillingTabShouldSuccessTest() {
        BurgerBuilderPage burgerBuilderPage = new BurgerBuilderPage(getDriver());
        burgerBuilderPage
                .clickFillingTab();
        String attributeName = "class";
        String value = "tab_type_current";
        Assert.assertTrue("Пользователь должен перейти в раздел 'Начинки'", isAttributePresent(burgerBuilderPage.getFillingTab(), attributeName, value));
    }
}
