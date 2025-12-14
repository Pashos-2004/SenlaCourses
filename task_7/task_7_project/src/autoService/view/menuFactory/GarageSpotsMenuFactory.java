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
            new MenuItem("Добавить гараж", () -> MenuFunctions.addGarageSpot()),
            new MenuItem("Удалить гараж", () -> MenuFunctions.removeGarageSpot()),
            new MenuItem("Просмотреть все места", () -> MenuFunctions.showAllGarageSpots()),
            new MenuItem("Свободные места на дату", () -> MenuFunctions.showAvailableSpots()),
            new MenuItem("Добавить место в гараж", () -> MenuFunctions.addPlaceAtGarageSpot()),
            new MenuItem("Удалить место в гаражне", () -> MenuFunctions.deletePlaceAtGarageSpot()),
            new MenuItem("Импортировать данные из CSV", () -> MenuFunctions.importGarageSpotsFromCSV()),
            new MenuItem("Экспортировать в CSV", () -> MenuFunctions.exportGarageSpotsToCSV())
        );
        return new Menu("Управление гаражными местами", items);
    }
}