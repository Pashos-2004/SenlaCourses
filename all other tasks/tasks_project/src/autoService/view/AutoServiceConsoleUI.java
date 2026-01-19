package autoService.view;

import autoService.DI.annotations.Autowired;
import autoService.DI.annotations.Component;
import autoService.DI.annotations.Inject;
import autoService.controller.AutoServiceController;

@Component(name = "consoleUI")
public class AutoServiceConsoleUI {
    private static AutoServiceConsoleUI instance;
    
    @Autowired
    private AutoServiceController controller;
    
    @Autowired
    private MenuNavigator navigator;
    
    @Inject
    public AutoServiceConsoleUI(AutoServiceController controller, MenuNavigator navigator) {
        this.controller = controller;
        this.navigator = navigator;
        instance = this;
        System.out.println("AutoServiceConsoleUI инициализирован через DI");
    }
    
    public static AutoServiceConsoleUI getInstance() {
        if (instance == null) {
            synchronized (AutoServiceConsoleUI.class) {
                if (instance == null) {
                    instance = new AutoServiceConsoleUI(
                        AutoServiceController.getInstance(),
                        MenuNavigator.getInstance()
                    );
                }
            }
        }
        return instance;
    }
    
    public void run() {
        System.out.println("Добро пожаловать в систему автосервиса!");
        
        navigator.getMainMenu().showMenuSelect();
        
        System.out.println("До свидания!");
    }
    
   
}
