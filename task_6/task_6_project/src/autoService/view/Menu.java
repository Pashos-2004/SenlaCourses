package autoService.view;


import java.util.List;

public class Menu {
    private String title;
    private List<MenuItem> items;
    
    public Menu(String title, List<MenuItem> items) {
        this.title = title;
        this.items = items;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<MenuItem> getItems() {
        return items;
    }
    
    public MenuItem getItemByCode(int code) {
        for (MenuItem item : items) {
            if (item.getActionCode() == code) {
                return item;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
    	String menu = "";
        menu+=("\n=== " + title + " ===\n");
        for (MenuItem item : items) {
        	menu+=item+"\n";
        }
        menu+=("Выберите действие: ");
        return menu;
    }
}