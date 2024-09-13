import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

import static constants.TestsData.*;

// toDo: параметризованный тест для getType?

public class IngredientTests {

    Database database = new Database();
    private Ingredient testIngredient = database.availableIngredients().get(0);

    // Проверка: getPrice() возвращает корректную цену
    @Test
    public void getPriceReturnsCorrectPrice() {
        Assert.assertEquals("Ошибка: неверная цена ингредиента", database.availableIngredients().get(0).price, testIngredient.getPrice(), 0);
    }

    // Проверка: getName() возвращает корректное название
    @Test
    public void getNameReturnsCorrectName() {
        Assert.assertEquals("Ошибка: неверное название ингредиента", database.availableIngredients().get(0).name, testIngredient.getName());
    }

    // Проверка: getType() возвращает корректный тип ингредиента
    @Test
    public void getTypeReturnsCorrectType() {
        Assert.assertEquals("Ошибка: неверный тип ингредиента", database.availableIngredients().get(0).type, testIngredient.getType());
    }


}
