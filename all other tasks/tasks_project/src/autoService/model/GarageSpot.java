package autoService.model;

import java.io.Serializable;

public class GarageSpot implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
    private String box;
    private int countOfPlacesForCars;
    
    public GarageSpot(int id , String box) {
        this.id = id;
    	this.box = box;
    	countOfPlacesForCars = 1 ;
    }
    
    public GarageSpot(int id , String box, int countOfPlacesForCars) {
        this.id = id;
    	this.box = box;
    	this.countOfPlacesForCars = countOfPlacesForCars ;
    }
    
    public int getId() { 
    	return id; 
    }
    
    public String getBox() { 
    	return box; 
    }
    
    public int getCountOfPlacesForCars() { 
    	return countOfPlacesForCars; 
    }
    
    public void setCountOfPlacesForCars(int countOfPlacesForCars) { 
    	this.countOfPlacesForCars = countOfPlacesForCars;
    }
    
    public void setBox(String box) { 
    	this.box = box;
    }
    
    @Override
    public String toString() {
        return "Гаражное место {id=" + id + ", бокс : '" + box + ", мест для машин : '"+ countOfPlacesForCars + "'}";
    }
}