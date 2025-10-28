package subtask_2;

import java.math.BigDecimal;

public class Lily extends Flower{

	public boolean isTerry ;
	
	public Lily (BigDecimal price, String color, boolean isTerry) {
		super("Лилия", price, color);
		this.isTerry = isTerry;
	}

	@Override
	public String toString() {
		
		return String.format("%s %s; цвет : %s; цена : %s;", name,isTerry ? "махровая ":"обычная",color,price);	
	}
	
	public boolean getIsTerry() {
        return isTerry;
    }
    
    public void setaIsTerry(boolean isTerry) {
        this.isTerry = isTerry;
    }
	
}
