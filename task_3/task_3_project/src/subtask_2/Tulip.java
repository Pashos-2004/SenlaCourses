package subtask_2;

import java.math.BigDecimal;

public class Tulip extends Flower {
    
    public Tulip(BigDecimal price,String color) {
        super("Тюльпан", price, color);
    }
    
    @Override
    public String toString() {
    	return String.format("%s ; цвет : %s; цена : %s;", name,color,price);
    }
}