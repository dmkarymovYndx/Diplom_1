import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.*;

@RunWith(Parameterized.class)
public class BurgerDifferentNumberOfIngredientsTest {

    Database database = new Database();

    private final int numberOfIngredients;

    private Burger testBurger;

    public BurgerDifferentNumberOfIngredientsTest(int numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() {
        testBurger = new Burger();
    }

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Parameterized.Parameters
    public static Object[] getParameters() {
        return new Object[] {0, 1, 2};
    }

    // Проверка: getPrice() вызывает getPrice() класса Ingredient столько раз, сколько ингредиентов в модели
    @Test
    public void getPriceInvokesIngredientGetPriceCorrectAmountOfTimes() {

        testBurger.setBuns(bun);
        for (int i = 0; i < numberOfIngredients; i++) {
            testBurger.addIngredient(ingredient);
        }
        testBurger.getPrice();

        Mockito.verify(ingredient, Mockito.times(numberOfIngredients)).getPrice();
    }

    // Проверка: getReceipt() вызывает getType() класса Ingredient столько раз, сколько ингредиентов в модели
    @Test
    public void getReceiptInvokesIngredientGetTypeCorrectAmountOfTimes() {

        String bun_name = database.availableBuns().get(1).name;
        String ingredient_name = database.availableIngredients().get(3).name;
        IngredientType ingredient_type = database.availableIngredients().get(3).type;

        testBurger.setBuns(bun);
        for (int i = 0; i < numberOfIngredients; i++) {
            testBurger.addIngredient(ingredient);
        }

        Mockito.when(bun.getName()).thenReturn(bun_name);
        Mockito.when(ingredient.getType()).thenReturn(ingredient_type);
        Mockito.when(ingredient.getName()).thenReturn(ingredient_name);

        testBurger.getReceipt();

        Mockito.verify(ingredient, Mockito.times(numberOfIngredients)).getType();

    }

    // Проверка: getPrice() вызывает getName() класса Ingredient столько раз, сколько ингредиентов в модели
    @Test
    public void getReceiptInvokesIngredientGetNameCorrectAmountOfTimes() {

        String bun_name = database.availableBuns().get(1).name;
        String ingredient_name = database.availableIngredients().get(3).name;
        IngredientType ingredient_type = database.availableIngredients().get(3).type;

        testBurger.setBuns(bun);
        for (int i = 0; i < numberOfIngredients; i++) {
            testBurger.addIngredient(ingredient);
        }

        Mockito.when(bun.getName()).thenReturn(bun_name);
        Mockito.when(ingredient.getType()).thenReturn(ingredient_type);
        Mockito.when(ingredient.getName()).thenReturn(ingredient_name);

        testBurger.getReceipt();

        Mockito.verify(ingredient, Mockito.times(numberOfIngredients)).getName();

    }

}
