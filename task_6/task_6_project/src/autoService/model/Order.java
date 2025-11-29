package autoService.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
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
    
    public Order(int id, String clientName, String carModel, String description, OrderStatus status,
                 Master master, GarageSpot garageSpot,LocalDate planedStartTime ,LocalDate startTime,LocalDate estimatedEndTime, BigDecimal price) {
        this.id = id;
        this.clientName = clientName;
        this.carModel = carModel;
        this.description = description;
        this.master = master;
        this.garageSpot = garageSpot;
        this.status = status;
        this.planedStartTime = planedStartTime;
        this.startTime = startTime;
        this.estimatedEndTime = estimatedEndTime;
        this.price = price;
    }
    
    
    public Order(int id, String clientName, String carModel, String description, 
            Master master, GarageSpot garageSpot, LocalDate planedStartTime , int daysOfWork, BigDecimal price) {
    	this(id,clientName,carModel,description, OrderStatus.PENDING,master,garageSpot,planedStartTime ,planedStartTime,planedStartTime, price);
    	this.estimatedEndTime = this.estimatedEndTime.plusDays(daysOfWork);
	}
    
    public Order(int id, String clientName, String carModel, String description, OrderStatus status,
            Master master, GarageSpot garageSpot, LocalDate planedStartTime,LocalDate startTime  , int daysOfWork, BigDecimal price) {
    	this(id,clientName,carModel,description,status,master,garageSpot,planedStartTime ,planedStartTime,planedStartTime, price);
    	this.estimatedEndTime = this.estimatedEndTime.plusDays(daysOfWork);
	}
    
    public int getId() { 
    	return id; 
    }
    
    public void setClientName(String clientName) { 
    	this.clientName =  clientName; 
    }
    
    public void setCarModel(String carModel) { 
    	this.carModel =  carModel; 
    }
    
    public void setDescription(String description) { 
    	this.description =  description; 
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
    
    public void setMaster(Master master) { 
    	this.master = master;
    }
    
    public GarageSpot getGarageSpot() { 
    	return garageSpot; 
    }
    
    public void setGarageSpot(GarageSpot spot) { 
    	this.garageSpot = spot;
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
    
    public void setStartTime(LocalDate startTime) { 
        this.startTime = startTime; 
    }
    
    public void setEstimatedEndTime(LocalDate estimatedEndTime) { 
        this.estimatedEndTime = estimatedEndTime; 
    }
    
    public void setPlanedStartTime (LocalDate planedStartTime ) { 
        this.planedStartTime = planedStartTime; 
    }
    
    public boolean shiftTime(int countOfDays, boolean isShiftInFuture) {
        if(isShiftInFuture) {
        	this.estimatedEndTime = this.estimatedEndTime.plusDays(countOfDays);
        	return true;
        } else {
        	if(this.estimatedEndTime.minusDays(countOfDays).isAfter(startTime)) {
        		this.estimatedEndTime = this.estimatedEndTime.minusDays(countOfDays);
        		return true;
        	}
        	return false;
        }
        
    }
    
    public boolean isPending() {
        return status == OrderStatus.PENDING;
    }
    
    public boolean isInProgress() {
    	return status.equals(OrderStatus.IN_PROGRESS);
    }
    
    public boolean isActive() {
        return status.equals(OrderStatus.PENDING) || status.equals(OrderStatus.IN_PROGRESS);
    }
    
    public boolean isCompleted() {
        return status.equals(OrderStatus.COMPLETED);
    }
    
    public boolean isCancelled() {
        return status.equals(OrderStatus.CANCELLED);
    }
    
    public boolean isDeleted() {
        return status.equals(OrderStatus.DELETED);
    }
    
    @Override
    public String toString() {
        return "Заказ {id=" + id + ", Клиент : '" + clientName + "', машина : '" + carModel + 
               "', статус заказа : " + status + ", мастер : " + master.getName() + 
               ", " + garageSpot +", планируема дата начала работ : "+planedStartTime +", фактическая дата начала работ : "+startTime+ ", дата окончания работ : "+ estimatedEndTime + ", цена : "+price+"}";
    }
    
}