package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuItem;

public class MainMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Управление мастерами", 1),
            new MenuItem("Управление гаражными местами", 2),
            new MenuItem("Управление заказами", 3),
            new MenuItem("Просмотр отчетов", 4),
            new MenuItem("Выход", 0)
        );
        return new Menu("Главное меню автосервиса", items);
    }
}
