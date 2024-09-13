import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import praktikum.Burger;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Database database = new Database();

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredient2;

    @Spy
    Burger testBurger;

    // Проверка: setBuns() добавляет в модель булочку с правильным названием
    @Test
    public void setBunsAddsCorrectBun() {
        String bun_name = database.availableBuns().get(0).name;
        testBurger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn(bun_name);
        Assert.assertEquals("Ошибка: добавлена булочка с неверным названием", bun_name, testBurger.bun.getName());
    }

    // Проверка: addIngredient() добавляет в модель ингредиент с правильным названием
    @Test
    public void addIngredientAddsCorrectIngredient() {
        String ingredient_name = database.availableIngredients().get(0).name;
        testBurger.addIngredient(ingredient);
        Mockito.when(ingredient.getName()).thenReturn(ingredient_name);

        Assert.assertEquals("Ошибка: ингредиент не был добавлен", 1, testBurger.ingredients.size());
        Assert.assertEquals("Ошибка: добавлен ингредиент с неверным названием", ingredient_name, testBurger.ingredients.get(0).getName());
    }

    // Проверка: removeIngredient() удаляет выбранный ингредиент из модели
    @Test
    public void removeIngredientRemovesCorrectIngredient() {
        String ingredient_name = database.availableIngredients().get(1).name;
        testBurger.addIngredient(ingredient);
        testBurger.addIngredient(ingredient2);
        Mockito.when(ingredient2.getName()).thenReturn(ingredient_name);

        testBurger.removeIngredient(0);
        Assert.assertEquals("Ошибка: неверное количество ингридиентов", 1, testBurger.ingredients.size());
        Assert.assertEquals("Ошибка: удалён ингредиент с неверным названием", ingredient_name, testBurger.ingredients.get(0).getName());
    }

    // Проверка: moveIngredient() перемещает выбранный ингредиент на выбранную позицию
    @Test
    public void moveIngredientMovesIngredientToCorrectPosition() {
        String ingredient_name = database.availableIngredients().get(1).name;
        testBurger.addIngredient(ingredient);
        testBurger.addIngredient(ingredient2);
        testBurger.addIngredient(ingredient);

        Mockito.when(ingredient2.getName()).thenReturn(ingredient_name);

        testBurger.moveIngredient(1, 0);
        Assert.assertEquals("Ошибка: количество ингредиентов изменилось после перемещения", 3, testBurger.ingredients.size());
        Assert.assertEquals("Ошибка: некорректный порядок элементов после перемещения", ingredient_name, testBurger.ingredients.get(0).getName());
    }

    // Проверка: getPrice() возвращает корректную цену
    @Test
    public void getPriceReturnsCorrectPrice() {
        float bun_price = database.availableBuns().get(1).price;
        float ingredient_price = database.availableIngredients().get(2).price;
        testBurger.setBuns(bun);
        testBurger.addIngredient(ingredient);
        testBurger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(bun_price);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredient_price);
        float expected = 1000.0f;
        float actual = testBurger.getPrice();
        Assert.assertEquals("Ошибка: цена не совпадает с ожидаемой", expected, actual, 0);
    }

    // Проверка: getPrice() вызывает getPrice() класса Bun один раз
    @Test
    public void getPriceInvokesBunGetPriceOneTime() {
        testBurger.setBuns(bun);
        testBurger.getPrice();
        Mockito.verify(bun, Mockito.times(1)).getPrice();
    }

    // Проверка: getReceipt() вызывает getName() класса Bun два раза
    @Test
    public void getReceiptInvokesBunGetNameTwoTimes() {
        String bun_name = database.availableBuns().get(2).name;
        testBurger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn(bun_name);
        testBurger.getReceipt();
        Mockito.verify(bun, Mockito.times(2)).getName();
    }

    // Проверка: getReceipt() вызывает getPrice() один раз
    @Test
    public void getReceiptInvokesGetPriceOneTime() {
        String bun_name = database.availableBuns().get(2).name;
        testBurger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn(bun_name);
        testBurger.getReceipt();
        Mockito.verify(testBurger, Mockito.times(1)).getPrice();
    }

    // Проверка: getReceipt() возвращает корректную цену
    @Test
    public void getReceiptReturnsCorrectPrice() {
        testBurger.setBuns(bun);
        Mockito.when(testBurger.getPrice()).thenReturn(3000.0f);

        String actual = testBurger.getReceipt();
        Assert.assertTrue("Ошибка: неверно указана цена", actual.contains("Price: 3000,0"));
    }


}
