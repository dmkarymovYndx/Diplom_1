import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;

public class BunTests {

    Database database = new Database();
    private Bun testBun = database.availableBuns().get(0);

    // Проверка: getName() возвращает корректное название
    @Test
    public void getNameReturnsBunName() {
        Assert.assertEquals("Ошибка: название булочки не совпадает с ожидаемым", database.availableBuns().get(0).name, testBun.getName());
    }

    // Проверка: getPrice() возвращает корректную цену
    @Test
    public void getPriceReturnsBunPrice() {
        Assert.assertEquals("Ошибка: цена булочки не совпадает с ожидаемой", database.availableBuns().get(0).price, testBun.getPrice(), 0);
    }

}
