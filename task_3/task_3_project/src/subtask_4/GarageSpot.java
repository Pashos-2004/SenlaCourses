package subtask_4;

class GarageSpot {
	private int id;
    private String box;
    private boolean available;
    
    public GarageSpot(int id , String box) {
        this.id = id;
    	this.box = box;
        this.available = true;
    }
    
    public int getId() { 
    	return id; 
    }
    
    public String getBox() { 
    	return box; 
    }
    
    public boolean isAvailable() { 
    	return available; 
    }
    
    public void setAvailable(boolean available) { 
    	this.available = available; 
    }
    
    @Override
    public String toString() {
        return "GarageSpot{id=" + id + ", box='" + box + "', available=" + available + "}";
    }
}