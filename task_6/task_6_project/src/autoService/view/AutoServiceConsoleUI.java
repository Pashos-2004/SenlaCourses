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
