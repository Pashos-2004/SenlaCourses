package autoService.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import autoService.controller.AutoServiceController;
import autoService.model.GarageSpot;
import autoService.model.Master;

public class AutoServiceConsoleUI {
    private static AutoServiceConsoleUI instance;
    private AutoServiceController controller;
    private MenuNavigator navigator;
    private boolean running;
    
    private AutoServiceConsoleUI() {
        this.controller = AutoServiceController.getInstance();
        this.navigator = MenuNavigator.getInstance();
        this.running = true;
    }
    
    public static AutoServiceConsoleUI getInstance() {
        if (instance == null) {
            instance = new AutoServiceConsoleUI();
        }
        return instance;
    }
    
    public void run() {
        System.out.println("Добро пожаловать в систему автосервиса!");
        
        while (running) {
            showMainMenu();
        }
        
        System.out.println("До свидания!");
    }
    
    private void showMainMenu() {
        Menu menu = navigator.getMenu(0);
        System.out.println(menu);
        int choice = navigator.getIntInput();
        
        switch (choice) {
            case 1 -> showMastersMenu();
            case 2 -> showGarageSpotsMenu();
            case 3 -> showOrdersMenu();
            case 4 -> showReportsMenu();
            case 0 -> running = false;
            default -> System.out.println("Неверный выбор!");
        }
    }
    
    private void showMastersMenu() {
        while (true) {
            Menu menu = navigator.getMenu(1);
            System.out.println(menu);
            int choice = navigator.getIntInput();
            
            switch (choice) {
                case 1 -> addMaster();
                case 2 -> removeMaster();
                case 3 -> showAllMasters();
                case 4 -> showMastersByWorkload();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
    
    private void showGarageSpotsMenu() {
        while (true) {
            Menu menu = navigator.getMenu(2);
            System.out.println(menu);
            int choice = navigator.getIntInput();
            
            switch (choice) {
                case 1 -> addGarageSpot();
                case 2 -> removeGarageSpot();
                case 3 -> showAllGarageSpots();
                case 4 -> showAvailableSpots();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
    
    private void showOrdersMenu() {
        while (true) {
            Menu menu = navigator.getMenu(3);
            System.out.println(menu);
            int choice = navigator.getIntInput();
            
            switch (choice) {
                case 1 -> createOrder();
                case 2 -> completeOrder();
                case 3 -> cancelOrder();
                case 4 -> removeOrder();
                case 5 -> shiftOrderTime();
                case 6 -> showAllOrders();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
    
    private void showReportsMenu() {
        while (true) {
            Menu menu = navigator.getMenu(4);
            System.out.println(menu);
            int choice = navigator.getIntInput();
            
            switch (choice) {
                case 1 -> showOrdersBySubmissionDate();
                case 2 -> showOrdersByPrice();
                case 3 -> showCurrentOrdersByPrice();
                case 4 -> showNearestAvailableDate();
                case 0 -> { return; }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
    
    private void addMaster() {
        System.out.print("Введите имя мастера: ");
        String name = navigator.getStringInput();
        System.out.print("Введите специализацию: ");
        String specialization = navigator.getStringInput();
        controller.addMaster(name, specialization);
    }
    
    private void removeMaster() {
        showAllMasters();
        System.out.print("Введите ID мастера для удаления: ");
        int id = navigator.getIntInput();
        controller.removeMaster(id);
    }
    
    private void showAllMasters() {
    	System.out.println("Все мастра:");
    	InfoDisplayer.printListWithNumeration(controller.getAllMasters());
    }
    
    private void showMastersByWorkload() {
    	System.out.println("Мастра по зугрузке:");
    	System.out.println("Мастера (сортировка по занятости):");
        int counter = 1;
        for(Master master : controller.getMastersSortedByWorkload()) {
        	int workload = controller.getMasterWorkload(master);
        	System.out.println(counter + "."+master + ", текущих заказов: " + workload);
        	counter+=1;
        }
    }
    
    private void addGarageSpot() {
        System.out.print("Введите номер гаража: ");
        String number = navigator.getStringInput();
        controller.addGarageSpot(number);
    }
    
    private void removeGarageSpot() {
        showAllGarageSpots();
        System.out.print("Введите ID гаража для удаления: ");
        int id = navigator.getIntInput();
        controller.removeGarageSpot(id);
    }
    
    private void showAllGarageSpots() {
    	System.out.println("Все гаражи:");
        InfoDisplayer.printListWithNumeration(controller.getAllGarageSpots());
    }
    
    private void showAvailableSpots() {
        LocalDate date = navigator.getDateInput();
        List<GarageSpot> spots = controller.getAvailableGarageSpotsOnDate(date);
        System.out.println("Свободные места на " + date + ":");
        if (spots.isEmpty()) {
            System.out.println("Нет свободных мест");
        } else {
            InfoDisplayer.printListWithNumeration(spots);
        }
    }
    
    private void createOrder() {
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
        
        controller.addOrder(clientName, carModel, description, masterId, 
                          spotId, plannedStart, days, price);
    }
    
    private void completeOrder() {
    	showActiveOrders();
        System.out.print("Введите ID заказа для завершения: ");
        int orderId = navigator.getIntInput();
        controller.completeOrder(orderId);
    }
    
    private void cancelOrder() {
    	showActiveOrders();
        System.out.print("Введите ID заказа для отмены: ");
        int orderId = navigator.getIntInput();
        controller.cancelOrder(orderId);
    }
    
    private void removeOrder() {
    	showActiveOrders();
        System.out.print("Введите ID заказа для удаления: ");
        int orderId = navigator.getIntInput();
        controller.removeOrder(orderId);
    }
    
    private void shiftOrderTime() {
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
    
    private void showActiveOrders() {
    	System.out.println("Активные заказы:");
        InfoDisplayer.printListWithNumeration(controller.getActiveOrders());
    }
    
    private void showAllOrders() {
    	System.out.println("Все заказы:");
        InfoDisplayer.printListWithNumeration(controller.getAllOrders());
    }
    
    private void showOrdersBySubmissionDate() {
    	System.out.println("Заказы (сортировка по дате подачи):");
        InfoDisplayer.printListWithNumeration(controller.getOrdersSortedBySubmissionDate());
    }
    
    private void showOrdersByPrice() {
    	System.out.println("Заказы (сортировка по цене):");
        InfoDisplayer.printListWithNumeration(controller.getOrdersSortedByPrice());
    }
    
    private void showCurrentOrdersByPrice() {
    	System.out.println("Текущие заказы (сортировка по цене):");
        InfoDisplayer.printListWithNumeration(controller.getCurrentOrdersSortedByPrice());
    }
    
    private void showNearestAvailableDate() {
        LocalDate date = controller.getNearestAvailableDate();
        System.out.println("Ближайшая свободная дата: " + date);
    }
}
