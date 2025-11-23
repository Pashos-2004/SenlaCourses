package autoService;

import java.time.Duration;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class Order {
    private int id;
    private String clientName;
    private String carModel;
    private String description;
    private Master master;
    private GarageSpot garageSpot;
    private OrderStatus status;
    private LocalDate startTime;
    private LocalDate planedStartTime;
    private LocalDate estimatedEndTime;
    private BigDecimal price;
    
    public Order(int id, String clientName, String carModel, String description, 
                 Master master, GarageSpot garageSpot,LocalDate planedStartTime ,LocalDate startTime, BigDecimal price) {
        this.id = id;
        this.clientName = clientName;
        this.carModel = carModel;
        this.description = description;
        this.master = master;
        this.garageSpot = garageSpot;
        this.status = OrderStatus.PENDING;
        this.planedStartTime = planedStartTime;
        this.startTime = startTime;
        this.estimatedEndTime = startTime;
        this.price = price;
    }
    
    
    public Order(int id, String clientName, String carModel, String description, 
            Master master, GarageSpot garageSpot, LocalDate planedStartTime , int daysOfWork, BigDecimal price) {
    	this(id,clientName,carModel,description,master,garageSpot,planedStartTime ,planedStartTime, price);
    	this.estimatedEndTime = this.estimatedEndTime.plusDays(daysOfWork);
	}
    
    public Order(int id, String clientName, String carModel, String description, 
            Master master, GarageSpot garageSpot, LocalDate planedStartTime,LocalDate startTime  , int daysOfWork, BigDecimal price) {
    	this(id,clientName,carModel,description,master,garageSpot,planedStartTime ,planedStartTime, price);
    	this.estimatedEndTime = this.estimatedEndTime.plusDays(daysOfWork);
	}
    
    public int getId() { 
    	return id; 
    }
    
    public String getClientName() { 
    	return clientName; 
    }
    
    public BigDecimal getPrice() {
    	return price;
	}
    
    public void setPrice(BigDecimal price) {
		this.price = price;
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
    
    public LocalDate getStartTime() {
    	return startTime; 
    }
    
    public LocalDate getEstimatedEndTime() {
    	return estimatedEndTime; 
    }
    
    public LocalDate getPlanedStartTime () {
    	return planedStartTime; 
    }
    
    public void setStatus(OrderStatus status) { 
    	this.status = status; 
    }
    
    public void setEstimatedEndTime(LocalDate estimatedEndTime) { 
        this.estimatedEndTime = estimatedEndTime; 
    }
    
    public void setPlanedStartTime (LocalDate planedStartTime ) { 
        this.planedStartTime = planedStartTime; 
    }
    
    public void shiftTime(int countOfDays, boolean isShiftInFuture) {
        if(isShiftInFuture) this.estimatedEndTime = this.estimatedEndTime.plusDays(countOfDays);
        else this.estimatedEndTime = this.estimatedEndTime.minusDays(countOfDays);
    }
    
    @Override
    public String toString() {
        return "Заказ {id=" + id + ", Клиент : '" + clientName + "', машина : '" + carModel + 
               "', статус заказа : " + status + ", мастер : " + master.getName() + 
               ", " + garageSpot +", планируема дата начала работ : "+planedStartTime +", фактическая дата начала работ : "+startTime+ ", дата окончания работ : "+ estimatedEndTime + ", цена : "+price+"}";
    }
    
}