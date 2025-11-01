package autoService;

import java.time.Duration;
import java.util.List;

class AutoService {
    private List<Master> masters = new java.util.ArrayList<>();
    private List<GarageSpot> garageSpots = new java.util.ArrayList<>();
    private List<Order> orders = new java.util.ArrayList<>();
    private int nextMasterId;
    private int nextSpotId;
    private int nextOrderId;
    
    public AutoService() {
        this.nextMasterId = 1;
        this.nextSpotId = 1;
        this.nextOrderId = 1;
    }
    
    public void addMaster(String name, String specialization) {
        Master master = new Master(nextMasterId++, name, specialization);
        masters.add(master);
        System.out.println("Добавлен мастер: " + master);
    }
    
    public void removeMaster(Master master) {
        
        if (masters.contains(master)) {
        	masters.remove(master);
            System.out.println("Мастер с ID " + master + " удален");
        } else {
            System.out.println("Мастер с ID " + master + " не найден");
        }
    }
    
    public void removeMaster(int masterId) {
        boolean removed = masters.removeIf(master -> master.getId() == masterId);
        if (removed) {
            System.out.println("Мастер с ID " + masterId + " удален");
        } else {
            System.out.println("Мастер с ID " + masterId + " не найден");
        }
    }
    
    public void addGarageSpot(String number) {
        GarageSpot spot = new GarageSpot(nextSpotId++, number);
        garageSpots.add(spot);
        System.out.println("Добавлено гаражное место: " + spot);
    }
    
    public void removeGarageSpot(GarageSpot garageSpot) {
        
        if (garageSpots.contains(garageSpot)) {
        	garageSpots.remove(garageSpot);
            System.out.println("Гаражное место  " + garageSpot + " удалено");
        } else {
            System.out.println("Гаражное место  " + garageSpot + " не найдено");
        }
    }
    
    public void removeGarageSpot(int spotId) {
        boolean removed = garageSpots.removeIf(spot -> spot.getId() == spotId);
        if (removed) {
            System.out.println("Гаражное место с ID " + spotId + " удалено");
        } else {
            System.out.println("Гаражное место с ID " + spotId + " не найдено");
        }
    }
    
    public void addOrder(String clientName, String carModel, String description, 
                         int masterId, int spotId, int hoursOfWork, int daysOfWork) {
        Master master = findMasterById(masterId);
        GarageSpot spot = findSpotById(spotId);
        
        if (master == null) {
            System.out.println("Мастер с ID " + masterId + " не найден");
            return;
        }
        if (spot == null) {
            System.out.println("Гаражное место с ID " + spotId + " не найдено");
            return;
        }
        if (!spot.getIsAvailable()) {
            System.out.println("Гаражное место " + spot.getBox() + " занято");
            return;
        }
        
        Order order = new Order(nextOrderId++, clientName, carModel, description, master, spot, hoursOfWork, daysOfWork);
        orders.add(order);
        spot.setIsAvailable(false);
        System.out.println("Добавлен заказ: " + order);
    }
    
    public void removeOrder(Order order) {
    	if(orders.contains(order)) {
    		orders.remove(order);
    		order.getGarageSpot().setIsAvailable(true);
    		System.out.println("Заказ с ID " + order.getId() + " удален");
    	} else {
    		System.out.println("Заказ с ID " + order.getId() + " не найлен");
    	}
    }
    
    public void removeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            orders.remove(order);
            order.getGarageSpot().setIsAvailable(true);
            System.out.println("Заказ с ID " + orderId + " удален");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void completeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.COMPLETED);
            order.getGarageSpot().setIsAvailable(true);
            System.out.println("Заказ с ID " + orderId + " завершен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.CANCELLED);
            order.getGarageSpot().setIsAvailable(true);
            System.out.println("Заказ с ID " + orderId + " отменен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void shiftOrderTime(int orderId, Duration duration) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.shiftTime(duration);
            System.out.println("Время выполнения заказа " + orderId + " сдвинуто на " + duration);
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    private Master findMasterById(int id) {
    	for(Master master : masters) {
        	if(master.getId()==id) return master;
        }
    	return null;
    }
    
    private GarageSpot findSpotById(int id) {
    	for(GarageSpot garageSpot : garageSpots) {
    		if(garageSpot.getId()==id) return garageSpot;
        }
    	return null;
    }
    
    private Order findOrderById(int id) {
    	for(Order order : orders) {
    		if(order.getId()==id) return order;
        }
    	return null;
    }
    
    public void printListOfMasters() {
        System.out.println("Мастера :");
        for(Master master : masters) {
        	System.out.println(master);
        }
    }
    
    public void printListOfGarageSpots() {
        System.out.println("Гаражные места :");
        for(GarageSpot garageSpot : garageSpots) {
        	System.out.println(garageSpot);
        }
    }
    
    public void printListOfOrders() {
        System.out.println("Заказы : ");
        for(Order order : orders) {
        	System.out.println(order);
        }
    }
    
    
    public List<Master> getMasters() {
		return  masters;
	}
    
    public List<Order> getOrders() {
		return orders;
	}
    
    public List<GarageSpot> getGarageSpots() {
		return garageSpots;
	}
}