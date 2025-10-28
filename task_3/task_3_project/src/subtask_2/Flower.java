package subtask_2;

abstract class Flower {
	protected String name;
    protected double price;
    protected String color;
    
    public Flower(String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getColor() {
        return color;
    }
    
    public double setPrice() {
        return price;
    }
    
    public String setColor() {
        return color;
    }
    
    public abstract String showFlowerInfo();
}

