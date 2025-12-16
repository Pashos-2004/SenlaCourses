package autoService.view;

import autoService.controller.AutoServiceController;

public class AutoServiceConsoleUI {
    private static AutoServiceConsoleUI instance;
    private AutoServiceController controller;
    private MenuNavigator navigator;

    
    private AutoServiceConsoleUI() {
        this.controller = AutoServiceController.getInstance();
        this.navigator = MenuNavigator.getInstance();
    }
    
    public static AutoServiceConsoleUI getInstance() {
        if (instance == null) {
            instance = new AutoServiceConsoleUI();
        }
        return instance;
    }
    
    public void run() {
        System.out.println("Добро пожаловать в систему автосервиса!");
        
        navigator.getMainMenu().showMenuSelect();
        
        System.out.println("До свидания!");
    }
    
   
}
