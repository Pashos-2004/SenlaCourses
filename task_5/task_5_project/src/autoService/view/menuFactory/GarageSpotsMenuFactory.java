package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuFunctions;
import autoService.view.MenuItem;

public class GarageSpotsMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Добавить гаражное место", () -> MenuFunctions.addGarageSpot()),
            new MenuItem("Удалить гаражное место", () -> MenuFunctions.removeGarageSpot()),
            new MenuItem("Просмотреть все места", () -> MenuFunctions.showAllGarageSpots()),
            new MenuItem("Свободные места на дату", () -> MenuFunctions.showAvailableSpots())
        );
        return new Menu("Управление гаражными местами", items);
    }
}