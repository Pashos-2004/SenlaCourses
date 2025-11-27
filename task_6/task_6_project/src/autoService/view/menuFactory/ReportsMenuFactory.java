package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuFunctions;
import autoService.view.MenuItem;

public class ReportsMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Заказы по дате подачи", () -> MenuFunctions.showOrdersBySubmissionDate()),
            new MenuItem("Заказы по цене", () -> MenuFunctions.showOrdersByPrice()),
            new MenuItem("Текущие заказы по цене", () -> MenuFunctions.showCurrentOrdersByPrice()),
            new MenuItem("Ближайшая свободная дата", () -> MenuFunctions.showNearestAvailableDate())
            
        );
        return new Menu("Просмотр отчетов", items);
    }
}