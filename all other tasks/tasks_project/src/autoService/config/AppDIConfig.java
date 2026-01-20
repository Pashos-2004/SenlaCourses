package autoService.config;

import autoService.DI.annotations.Bean;
import autoService.DI.annotations.Configuration;
import autoService.service.CsvService;
import autoService.view.AutoServiceConsoleUI;
import autoService.view.MenuNavigator;

@Configuration
public class AppDIConfig {
    
    @Bean(name = "configManager")
    public ConfigManager configManager() {
        return ConfigManager.getInstance();
    }
    
    @Bean(name = "stateManager")
    public StateManager stateManager() {
        return StateManager.getInstance();
    }
    
    @Bean(name = "csvService")
    public CsvService csvService() {
        return new CsvService();
    }
    
    @Bean(name = "menuNavigator")
    public MenuNavigator menuNavigator() {
        return MenuNavigator.getInstance();
    }
    
    @Bean(name = "consoleUI")
    public AutoServiceConsoleUI consoleUI() {
        return AutoServiceConsoleUI.getInstance();
    }
}