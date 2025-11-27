package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuFunctions;
import autoService.view.MenuItem;

public class MastersMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Добавить мастера", () -> MenuFunctions.addMaster()),
            new MenuItem("Удалить мастера", () -> MenuFunctions.removeMaster()),
            new MenuItem("Просмотреть всех мастеров", () -> MenuFunctions.showAllMasters()),
            new MenuItem("Мастера по загруженности", () -> MenuFunctions.showMastersByWorkload()),
            new MenuItem("Импортировать данные из CSV", () -> MenuFunctions.importMastersFromCSV()),
            new MenuItem("Экспортировать в CSV", () -> MenuFunctions.exportMastersToCSV())
        );
        return new Menu("Управление мастерами", items);
    }
}
