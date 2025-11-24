package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuItem;

public class GarageSpotsMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Добавить гаражное место", 1),
            new MenuItem("Удалить гаражное место", 2),
            new MenuItem("Просмотреть все места", 3),
            new MenuItem("Свободные места на дату", 4),
            new MenuItem("Экспортировать в CSV", 5),
            new MenuItem("Импортировать из CSV", 6),
            new MenuItem("Назад", 0)
        );
        return new Menu("Управление гаражными местами", items);
    }
}