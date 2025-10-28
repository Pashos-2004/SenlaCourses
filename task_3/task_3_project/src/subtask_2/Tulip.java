package subtask_2;

public class Tulip extends Flower {
    
    public Tulip(double price,String color) {
        super("Тюльпан", price, color);
    }
    
    @Override
    public String showFlowerInfo() {
    	return String.format("%s ; цвет : %s; цена : %s;", name,color,price);
    }
}