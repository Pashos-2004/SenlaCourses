package subtask_4;

enum OrderStatus {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED
}


class Order {
    private int id;
    private String clientName;
    private String carModel;
    private String description;
    private Master master;
    private GarageSpot garageSpot;
    private OrderStatus status;
    private java.time.LocalDateTime startTime;
    private java.time.LocalDateTime estimatedEndTime;
    
    public Order(int id, String clientName, String carModel, String description, 
                 Master master, GarageSpot garageSpot) {
        this.id = id;
        this.clientName = clientName;
        this.carModel = carModel;
        this.description = description;
        this.master = master;
        this.garageSpot = garageSpot;
        this.status = OrderStatus.PENDING;
        this.startTime = java.time.LocalDateTime.now();
        this.estimatedEndTime = this.startTime.plusHours(2);
    }
    
    public Order(int id, String clientName, String carModel, String description, 
            Master master, GarageSpot garageSpot, int hoursOfWork) {
	   this.id = id;
	   this.clientName = clientName;
	   this.carModel = carModel;
	   this.description = description;
	   this.master = master;
	   this.garageSpot = garageSpot;
	   this.status = OrderStatus.PENDING;
	   this.startTime = java.time.LocalDateTime.now();
	   this.estimatedEndTime = this.startTime.plusHours(hoursOfWork);
	}
    
    public Order(int id, String clientName, String carModel, String description, 
            Master master, GarageSpot garageSpot, int hoursOfWork, int daysOfWork) {
	   this.id = id;
	   this.clientName = clientName;
	   this.carModel = carModel;
	   this.description = description;
	   this.master = master;
	   this.garageSpot = garageSpot;
	   this.status = OrderStatus.PENDING;
	   this.startTime = java.time.LocalDateTime.now();
	   this.estimatedEndTime = this.startTime.plusHours(hoursOfWork);
	   this.estimatedEndTime = this.estimatedEndTime.plusDays(daysOfWork);
	}
    
    public int getId() { 
    	return id; 
    
    }
    
    public String getClientName() { 
    	return clientName; 
    }
    
    public String getCarModel() { 
    	return carModel; 
    }
    
    public String getDescription() { 
    	return description; 
    }
    
    public Master getMaster() { 
    	return master;
    }
    
    public GarageSpot getGarageSpot() { 
    	return garageSpot; 
    }
    
    public OrderStatus getStatus() {
    	return status; 
    }
    
    public java.time.LocalDateTime getStartTime() {
    	return startTime; 
    }
    
    public java.time.LocalDateTime getEstimatedEndTime() {
    	return estimatedEndTime; 
    }
    
    public void setStatus(OrderStatus status) { 
    	this.status = status; 
    }
    
    public void setEstimatedEndTime(java.time.LocalDateTime estimatedEndTime) { 
        this.estimatedEndTime = estimatedEndTime; 
    }
    
    public void shiftTime(java.time.Duration duration) {
        this.estimatedEndTime = this.estimatedEndTime.plus(duration);
    }
    
    @Override
    public String toString() {
        return "Order{id=" + id + ", client='" + clientName + "', car='" + carModel + 
               "', status=" + status + ", master=" + master.getName() + 
               ", spot=" + garageSpot.getBox() + "}";
    }
    
}