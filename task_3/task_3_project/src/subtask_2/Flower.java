package subtask_2;

import java.math.BigDecimal;

abstract class Flower {
	protected String name;
    protected BigDecimal price;
    protected String color;
    
    public Flower(String name, BigDecimal price, String color) {
        this.name = name;
        this.price = price;
        this.price.setScale(2);
        this.color = color;
    }
    
    public String getName() {
        return name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public void etColor(String color) {
        this.color = color;
    }
    
    public abstract String showFlowerInfo();
}



