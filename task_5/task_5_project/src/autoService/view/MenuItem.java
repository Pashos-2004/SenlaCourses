package autoService.view;

public class MenuItem {
    private String title;
    private Runnable menuItemFunction;
    
    public MenuItem(String title, Runnable menuItemFunction) {
        this.title = title;
        this.menuItemFunction = menuItemFunction;
    }
    
    public void runFunction() {
    	menuItemFunction.run();
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return title;
    }
}