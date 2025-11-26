package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuFunctions;
import autoService.view.MenuItem;
import autoService.view.MenuNavigator;

public class MainMenuFactory implements MenuFactory {
	
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Управление мастерами", () -> MenuFunctions.showSubMenuSelect(1)),
            new MenuItem("Управление гаражными местами", () -> MenuFunctions.showSubMenuSelect(2)),
            new MenuItem("Управление заказами", () -> MenuFunctions.showSubMenuSelect(3)),
            new MenuItem("Просмотр отчетов", () -> MenuFunctions.showSubMenuSelect(4))
        );
        return new Menu("Главное меню автосервиса", items);
    }
}
