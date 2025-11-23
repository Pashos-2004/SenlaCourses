package autoService;

class GarageSpot {
	private int id;
    private String box;
    
    public GarageSpot(int id , String box) {
        this.id = id;
    	this.box = box;
    }
    
    public int getId() { 
    	return id; 
    }
    
    public String getBox() { 
    	return box; 
    }
    
    @Override
    public String toString() {
        return "Гаражное место {id=" + id + ", бокс : '" + box + "'}";
    }
}