package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuItem;

public class ReportsMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Заказы по дате подачи", 1),
            new MenuItem("Заказы по цене", 2),
            new MenuItem("Текущие заказы по цене", 3),
            new MenuItem("Ближайшая свободная дата", 4),
            new MenuItem("Назад", 0)
        );
        return new Menu("Просмотр отчетов", items);
    }
}