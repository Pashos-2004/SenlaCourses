package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuItem;

public class OrdersMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Создать заказ", 1),
            new MenuItem("Завершить заказ", 2),
            new MenuItem("Отменить заказ", 3),
            new MenuItem("Удалить заказ", 4),
            new MenuItem("Сдвинуть срок заказа", 5),
            new MenuItem("Просмотреть все заказы", 6),
            new MenuItem("Экспортировать в CSV", 7),
            new MenuItem("Импортировать из CSV", 8),
            new MenuItem("Назад", 0)
        );
        return new Menu("Управление заказами", items);
    }
}