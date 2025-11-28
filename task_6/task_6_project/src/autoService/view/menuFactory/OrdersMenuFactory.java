package autoService.view.menuFactory;

import java.util.Arrays;
import java.util.List;

import autoService.view.Menu;
import autoService.view.MenuFunctions;
import autoService.view.MenuItem;

public class OrdersMenuFactory implements MenuFactory {
    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(
            new MenuItem("Создать заказ", () -> MenuFunctions.createOrder()),
            new MenuItem("Завершить заказ", () -> MenuFunctions.completeOrder()),
            new MenuItem("Отменить заказ", () -> MenuFunctions.cancelOrder()),
            new MenuItem("Удалить заказ", () -> MenuFunctions.removeOrder()),
            new MenuItem("Сдвинуть срок заказа",() -> MenuFunctions.shiftOrderTime()),
            new MenuItem("Просмотреть все заказы", () -> MenuFunctions.showAllOrders()),
            new MenuItem("Импортировать данные из CSV", () -> MenuFunctions.importOrdersFromCSV()),
            new MenuItem("Экспортировать в CSV", () -> MenuFunctions.exportOrdersToCSV())
            
        );
        return new Menu("Управление заказами", items);
    }
}