package autoService.view;

public class MenuItem {
    private String title;
    private int actionCode;
    
    public MenuItem(String title, int actionCode) {
        this.title = title;
        this.actionCode = actionCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getActionCode() {
        return actionCode;
    }
    
    @Override
    public String toString() {
        return actionCode + ". " + title;
    }
}