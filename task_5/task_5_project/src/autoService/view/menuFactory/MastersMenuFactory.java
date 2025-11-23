package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuItem;

public class MastersMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Добавить мастера", 1),
            new MenuItem("Удалить мастера", 2),
            new MenuItem("Просмотреть всех мастеров", 3),
            new MenuItem("Мастера по загруженности", 4),
            new MenuItem("Назад", 0)
        );
        return new Menu("Управление мастерами", items);
    }
}
