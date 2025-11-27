package autoService.view;


import java.util.List;

public class Menu {
    private String title;
    private List<MenuItem> items;
    
    public Menu(String title, List<MenuItem> items) {
        this.title = title;
        this.items = items;
    }
    
    public void runMenuItemFunctionAt(int index) {
    	items.get(index).runFunction();
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<MenuItem> getItems() {
        return items;
    }
    
    
    public void showMenuSelect() {
    	while(true) {
    		System.out.println(this.toString());
    		int choice = MenuNavigator.getInstance().getIntInput();
    		if(choice==0) {
    			return;
    		}else if(choice<=items.size() && choice > 0) {
    			items.get(choice-1).runFunction();
    		}else {
    			System.out.println("Неверный выбор!");
    		}
    	}
    }
    
    @Override
    public String toString() {
    	String menu = "";
        menu+=("\n=== " + title + " ===\n");
        int counter = 1;
        for (MenuItem item : items) {
        	menu+=counter+". "+ item+"\n";
        	counter++;
        }
        menu+=("0. Выход\n");
        menu+=("Выберите действие: ");
        return menu;
    }
}