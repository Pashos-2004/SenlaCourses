package subtask_4;

class GarageSpot {
	private int id;
    private String box;
    private boolean isAvailable;
    
    public GarageSpot(int id , String box) {
        this.id = id;
    	this.box = box;
        this.isAvailable = true;
    }
    
    public int getId() { 
    	return id; 
    }
    
    public String getBox() { 
    	return box; 
    }
    
    public boolean getIsAvailable() { 
    	return isAvailable; 
    }
    
    public void setIsAvailable(boolean isAvailable) { 
    	this.isAvailable = isAvailable; 
    }
    
    @Override
    public String toString() {
        return "GarageSpot{id=" + id + ", box='" + box + "', available=" + isAvailable + "}";
    }
}