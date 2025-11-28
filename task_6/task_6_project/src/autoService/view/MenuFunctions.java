package autoService.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import autoService.controller.AutoServiceController;
import autoService.model.GarageSpot;
import autoService.model.Master;

public class MenuFunctions {
	private static MenuNavigator navigator = MenuNavigator.getInstance();
	private static AutoServiceController controller = AutoServiceController.getInstance();

	public static void showSubMenuSelect(int index) {
    	navigator.getMenu(index).showMenuSelect();
	}

	public static void addMaster() {
		MenuNavigator navigator = MenuNavigator.getInstance();
		System.out.print("Введите имя мастера: ");
		String name = navigator.getStringInput();
		System.out.print("Введите специализацию: ");
		String specialization = navigator.getStringInput();
		controller.addMaster(name, specialization);
	}

	public static void removeMaster() {
		showAllMasters();
		System.out.print("Введите ID мастера для удаления: ");
		int id = navigator.getIntInput();
		controller.removeMaster(id);
	}

	public static void showAllMasters() {
		System.out.println("Все мастра:");
		InfoDisplayer.printListWithNumeration(controller.getAllMasters());
	}

	public static void showMastersByWorkload() {
		System.out.println("Мастра по зугрузке:");
		System.out.println("Мастера (сортировка по занятости):");
		int counter = 1;
		for (Master master : controller.getMastersSortedByWorkload()) {
			int workload = controller.getMasterWorkload(master);
			System.out.println(counter + "." + master + ", текущих заказов: " + workload);
			counter += 1;
		}
	}

	public static void addGarageSpot() {
		System.out.print("Введите номер гаража: ");
		String number = navigator.getStringInput();
		controller.addGarageSpot(number);
	}

	public static void removeGarageSpot() {
		showAllGarageSpots();
		System.out.print("Введите ID гаража для удаления: ");
		int id = navigator.getIntInput();
		controller.removeGarageSpot(id);
	}

	public static void showAllGarageSpots() {
		System.out.println("Все гаражи:");
		InfoDisplayer.printListWithNumeration(controller.getAllGarageSpots());
	}

	public static void showAvailableSpots() {
		LocalDate date = navigator.getDateInput();
		List<GarageSpot> spots = controller.getAvailableGarageSpotsOnDate(date);
		System.out.println("Свободные места на " + date + ":");
		if (spots.isEmpty()) {
			System.out.println("Нет свободных мест");
		} else {
			InfoDisplayer.printListWithNumeration(spots);
		}
	}

	public static void createOrder() {
		System.out.print("Введите имя клиента: ");
		String clientName = navigator.getStringInput();
		System.out.print("Введите модель автомобиля: ");
		String carModel = navigator.getStringInput();
		System.out.print("Введите описание работ: ");
		String description = navigator.getStringInput();

		showAllMasters();
		System.out.print("Введите ID мастера: ");
		int masterId = navigator.getIntInput();

		showAllGarageSpots();
		System.out.print("Введите ID гаража: ");
		int spotId = navigator.getIntInput();

		System.out.print("Введите планируемую дату начала: ");
		LocalDate plannedStart = navigator.getDateInput();

		System.out.print("Введите количество дней работы: ");
		int days = navigator.getIntInput();

		System.out.print("Введите стоимость: ");
		BigDecimal price = BigDecimal.valueOf(navigator.getIntInput());

		controller.addOrder(clientName, carModel, description, masterId, spotId, plannedStart, days, price);
	}

	public static void completeOrder() {
		showActiveOrders();
		System.out.print("Введите ID заказа для завершения: ");
		int orderId = navigator.getIntInput();
		controller.completeOrder(orderId);
	}

	public static void cancelOrder() {
		showActiveOrders();
		System.out.print("Введите ID заказа для отмены: ");
		int orderId = navigator.getIntInput();
		controller.cancelOrder(orderId);
	}

	public static void removeOrder() {
		showActiveOrders();
		System.out.print("Введите ID заказа для удаления: ");
		int orderId = navigator.getIntInput();
		controller.removeOrder(orderId);
	}

	public static void shiftOrderTime() {
		showActiveOrders();
		System.out.print("Введите ID заказа: ");
		int orderId = navigator.getIntInput();
		System.out.print("Введите количество дней: ");
		int days = navigator.getIntInput();
		System.out.print("Сдвинуть вперед? (Д/н): ");
		String buff = navigator.getStringInput();
		boolean forward = buff.equals("Д") || buff.equals("д");
		controller.shiftOrderTime(controller.findActiveOrderById(orderId), days, forward);
	}

	public static void showActiveOrders() {
		System.out.println("Активные заказы:");
		InfoDisplayer.printListWithNumeration(controller.getActiveOrders());
	}

	public static void showAllOrders() {
		System.out.println("Все заказы:");
		InfoDisplayer.printListWithNumeration(controller.getAllOrders());
	}

	public static void showOrdersBySubmissionDate() {
		System.out.println("Заказы (сортировка по дате подачи):");
		InfoDisplayer.printListWithNumeration(controller.getOrdersSortedBySubmissionDate());
	}

	public static void showOrdersByPrice() {
		System.out.println("Заказы (сортировка по цене):");
		InfoDisplayer.printListWithNumeration(controller.getOrdersSortedByPrice());
	}

	public static  void showCurrentOrdersByPrice() {
		System.out.println("Текущие заказы (сортировка по цене):");
		InfoDisplayer.printListWithNumeration(controller.getCurrentOrdersSortedByPrice());
	}

	public static void showNearestAvailableDate() {
		LocalDate date = controller.getNearestAvailableDate();
		System.out.println("Ближайшая свободная дата: " + date);
	}
	
	public static void importMastersFromCSV() {
    	String filename = navigator.getFilenameForCSV();
    	try {
			controller.importMastersFromCSV(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println("Операция завершена");
    }
    
	public static void importOrdersFromCSV() {
    	String filename = navigator.getFilenameForCSV();
    	try {
			controller.importOrdersFromCSV(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println("Операция завершена");
    }
    
	public static void importGarageSpotsFromCSV() {
    	String filename = navigator.getFilenameForCSV();
    	try {
			controller.importGarageSpotsFromCSV(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println("Операция завершена");
    }
    
	public static void exportMastersToCSV() {
    	String filename = navigator.getFilenameForCSV();
    	try {
			controller.exportMastersToCSV(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println("Операция завершена");
    }
    
	public static void exportOrdersToCSV() {
    	String filename = navigator.getFilenameForCSV();
    	try {
			controller.exportOrdersSpotsToCSV(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println("Операция завершена");
    }
    
	public static void exportGarageSpotsToCSV() {
    	String filename = navigator.getFilenameForCSV();
    	try {
			controller.exportGarageSpotsToCSV(filename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println("Операция завершена");
    }
}
