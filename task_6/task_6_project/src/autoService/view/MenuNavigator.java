package autoService.view;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import autoService.view.menuFactory.GarageSpotsMenuFactory;
import autoService.view.menuFactory.MainMenuFactory;
import autoService.view.menuFactory.MastersMenuFactory;
import autoService.view.menuFactory.MenuFactory;
import autoService.view.menuFactory.OrdersMenuFactory;
import autoService.view.menuFactory.ReportsMenuFactory;

public class MenuNavigator {
    private volatile static MenuNavigator instance;
    private MainMenuFactory mainMenu;
    private Map<Integer, MenuFactory> menuFactories;
    private Scanner scanner;
    
    private MenuNavigator() {
        this.scanner = new Scanner(System.in);
        this.menuFactories = new HashMap<>();
        initializeMenuFactories();
    }
    
    public static synchronized MenuNavigator getInstance() {
        if (instance == null) {
            instance = new MenuNavigator();
        }
        return instance;
    }
    
    private void initializeMenuFactories() {
        menuFactories.put(1, new MastersMenuFactory());
        menuFactories.put(2, new GarageSpotsMenuFactory());
        menuFactories.put(3, new OrdersMenuFactory());
        menuFactories.put(4, new ReportsMenuFactory());
        mainMenu = new MainMenuFactory();
    }
    
    public String getFilenameForCSV() {
    	String filename = null;
    	while (filename == null) {
    		System.out.println("Введите имя файла:");
    		filename = getStringInput();
    	}
    	
    	if(filename.length()<4 || !filename.substring(filename.length()-4).equals(".csv")) {
    		return filename+".csv";
    	}
    	
    	return filename;
	}
    
    public Menu getMainMenu() {
		return mainMenu.createMenu();
	}
    
    public Menu getMenu(int menuCode) {
        MenuFactory factory = menuFactories.get(menuCode);
        return factory != null ? factory.createMenu() : null;
    }
    
    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число!");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
    
    public String getStringInput() {
        return scanner.nextLine();
    }
    
    public void enterToContinue() {
    	System.out.println("Нажмите enter для продолжения");
    	 scanner.nextLine();
    }
    
    public LocalDate getDateInput() {
        System.out.print("Введите дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Неверный формат даты! Используется текущая дата.");
            return LocalDate.now();
        }
    }
}