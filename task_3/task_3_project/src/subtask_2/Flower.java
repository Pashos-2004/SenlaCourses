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

class Rose extends Flower{

	public boolean isRoseThornRemoved;
	
	public Rose(double price, String color, boolean isRoseThornRemoved) {
		super("Роза", price, color);
		this.isRoseThornRemoved = isRoseThornRemoved;
	}

	@Override
	public String showFlowerInfo() {
		
		return String.format("%s %s; цвет : %s; цена : %s;", name,isRoseThornRemoved ? "с шипами":"без шипов",color,price);	
	}
	
	public boolean getIsRoseThornRemoved() {
        return isRoseThornRemoved;
    }
    
    public void setIsRoseThornRemoved(boolean isRoseThornRemoved) {
        this.isRoseThornRemoved = isRoseThornRemoved;
    }
	
}

class Lily extends Flower{

	public boolean isTerry ;
	
	public Lily (double price, String color, boolean isTerry) {
		super("Лилия", price, color);
		this.isTerry = isTerry;
	}

	@Override
	public String showFlowerInfo() {
		
		return String.format("%s %s; цвет : %s; цена : %s;", name,isTerry ? "махровая ":"обычная",color,price);	
	}
	
	public boolean getIsTerry() {
        return isTerry;
    }
    
    public void setaIsTerry(boolean isTerry) {
        this.isTerry = isTerry;
    }
	
}

class Tulip extends Flower {
    
    public Tulip(double price,String color) {
        super("Тюльпан", price, color);
    }
    
    @Override
    public String showFlowerInfo() {
    	return String.format("%s ; цвет : %s; цена : %s;", name,color,price);
    }
}

