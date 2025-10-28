package subtask_2;

import java.math.BigDecimal;

public class Rose extends Flower{

	public boolean isRoseThornRemoved;
	
	public Rose(BigDecimal price, String color, boolean isRoseThornRemoved) {
		super("Роза", price, color);
		this.isRoseThornRemoved = isRoseThornRemoved;
	}

	@Override
	public String toString() {
		
		return String.format("%s %s; цвет : %s; цена : %s;", name,isRoseThornRemoved ? "с шипами":"без шипов",color,price);	
	}
	
	public boolean getIsRoseThornRemoved() {
        return isRoseThornRemoved;
    }
    
    public void setIsRoseThornRemoved(boolean isRoseThornRemoved) {
        this.isRoseThornRemoved = isRoseThornRemoved;
    }
	
}
