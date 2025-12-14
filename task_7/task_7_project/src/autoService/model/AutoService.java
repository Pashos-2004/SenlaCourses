package autoService.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AutoService implements Serializable {
	private static final long serialVersionUID = 0L;
    private List<Master> masters = new ArrayList<>();
    private List<GarageSpot> garageSpots = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private int nextMasterId;
    private int nextSpotId;
    private int nextOrderId;
    
    public AutoService() {
        this.nextMasterId = 1;
        this.nextSpotId = 1;
        this.nextOrderId = 1;
    }
    
    public void updateNextMasterId() {
    	int maxId=-1;
    	for(Master master : masters) {
    		if(master.getId()>maxId) maxId=master.getId();
    	}
    	nextMasterId=maxId;
    }
    	
    public void updateNextSpotId() {
    	int maxId=-1;
    	for(GarageSpot spot : garageSpots) {
    		if(spot.getId()>maxId) maxId=spot.getId();
    	}
    	nextSpotId=maxId;
    }
    
    public void updateNextOrderId() {
    	int maxId=-1;
    	for(Order order : orders) {
    		if(order.getId()>maxId) maxId=order.getId();
    	}
    	nextOrderId=maxId;
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
    
    public void addGarageSpot(String box) {
        GarageSpot spot = new GarageSpot(nextSpotId++, box);
        garageSpots.add(spot);
        System.out.println("Добавлено гаражное место: " + spot);
    }
    
    public void addGarageSpot(String box,int countOfPlacesForCars) {
        GarageSpot spot = new GarageSpot(nextSpotId++, box,countOfPlacesForCars);
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
    
    public void addOrder(String clientName, String carModel, String description, int masterId, 
    		int spotId,OrderStatus status,LocalDate planedStartTime , LocalDate startTime , LocalDate estimatedEndTime, BigDecimal price) {
        Master master = findMasterById(masterId);
        GarageSpot spot = findSpotById(spotId);
        
        if(status == null) {
        	System.out.println("Невозможно установить статус заказа");
            return;
        }
        
        if (master == null) {
            System.out.println("Мастер с ID " + masterId + " не найден");
            return;
        }
        if (spot == null) {
            System.out.println("Гаражное место с ID " + spotId + " не найдено");
            return;
        }
        if (!isMasterAvailableOnDate(startTime, estimatedEndTime,master)) {
            System.out.println("Мастер " + master + " занят(a)");
            return;
        }
        if (!isGarageAvailableOnDate(startTime, estimatedEndTime,spot)) {
            System.out.println("Гаражное место " + spot.getBox() + " занято");
            return;
        }
        
        Order order = new Order(nextOrderId++, clientName, carModel, description,status, master, spot, planedStartTime ,startTime, estimatedEndTime, price);
        orders.add(order);
        
        System.out.println("Добавлен заказ: " + order);
    }
    
    public void addOrder(String clientName, String carModel, String description, int masterId, 
    		int spotId,LocalDate planedStartTime , LocalDate startTime , int daysOfWork, BigDecimal price) {
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
        if (!isMasterAvailableOnDate(startTime, startTime.plusDays(daysOfWork),master)) {
            System.out.println("Мастер " + master + " занят(a)");
            return;
        }
        if (!isGarageAvailableOnDate(startTime, startTime.plusDays(daysOfWork),spot)) {
            System.out.println("Гаражное место " + spot.getBox() + " занято");
            return;
        }
        
        Order order = new Order(nextOrderId++, clientName, carModel, description,OrderStatus.PENDING, master, spot, planedStartTime ,startTime, daysOfWork, price);
        orders.add(order);
        
        System.out.println("Добавлен заказ: " + order);
    }
    
    public void addOrder(String clientName, String carModel, String description, int masterId, int spotId, 
    		LocalDate planedStartTime , int daysOfWork, BigDecimal price) {
        addOrder(clientName, carModel, description, masterId, spotId, planedStartTime, planedStartTime, daysOfWork, price);
    }
    
    
    
    private boolean isMasterAvailableOnDate(LocalDate startdate, LocalDate endDate,Master master) {
    	return orders.stream()
        .noneMatch(
            order -> order.getMaster().equals(master)
                && (order.getStatus().equals(OrderStatus.PENDING) || order.getStatus().equals(OrderStatus.IN_PROGRESS))
                && !((order.getStartTime().isAfter(startdate) && order.getStartTime().isAfter(endDate)) || order.getEstimatedEndTime().isBefore(startdate) && order.getEstimatedEndTime().isBefore(endDate) )
        );
    }
    
    private boolean isGarageAvailableOnDate(LocalDate startdate, LocalDate endDate,GarageSpot spot) {
    	return orders.stream()
        .noneMatch(
            order -> order.getGarageSpot().equals(spot)
                && (order.isActive())
                && !((order.getStartTime().isAfter(startdate) && order.getStartTime().isAfter(endDate)) || order.getEstimatedEndTime().isBefore(startdate) && order.getEstimatedEndTime().isBefore(endDate) )
        );
    }
    
    public void removeOrder(Order order) {
    	if(orders.contains(order)) {
    		order.setStatus(OrderStatus.DELETED);
    		System.out.println("Заказ с ID " + order.getId() + " удален");
    	} else {
    		System.out.println("Заказ с ID " + order.getId() + " не найлен");
    	}
    }
    
    public void removeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
        	order.setStatus(OrderStatus.DELETED);
            System.out.println("Заказ с ID " + orderId + " удален");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void completeOrder(Order order) {
        if (order != null) {
            order.setStatus(OrderStatus.COMPLETED);
            System.out.println("Заказ с ID " + order + " завершен");
        } else {
            System.out.println("Заказ с ID " + order + " не найден");
        }
    }
    
    public void completeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.COMPLETED);
            System.out.println("Заказ с ID " + orderId + " завершен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void cancelOrder(Order order) {
        if (order != null) {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("Заказ с ID " + order + " отменен");
        } else {
            System.out.println("Заказ с ID " + order + " не найден");
        }
    }
    
    public void cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("Заказ с ID " + orderId + " отменен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void shiftOrderTime(Order order, int countOfDays, boolean isShiftInFuture) {
        if(order.shiftTime(countOfDays,isShiftInFuture)) {
        	System.out.println("Время выполнения заказа " + order + " сдвинуто на " + countOfDays + " дней");
        }else {
        	System.out.println("невозможно сдвинуть дату окончания до начала выполнения");
        }
    }
    
    public Master findMasterById(int id) {
    	for(Master master : masters) {
        	if(master.getId()==id) return master;
        }
    	return null;
    }
    
    public GarageSpot findSpotById(int id) {
    	for(GarageSpot garageSpot : garageSpots) {
    		if(garageSpot.getId()==id) return garageSpot;
        }
    	return null;
    }
    
    public Order findActiveOrderById(int id) {
    	for(Order order : orders) {
    		if(order.getId()==id) {
    			if(!order.isActive()) {
    				return null;
    			}
    			return order;
    		}
        }
    	return null;
    }
    
    public Order findOrderById(int id) {
    	for(Order order : orders) {
    		if(order.getId()==id) return order;
        }
    	return null;
    }
    
    private Map<GarageSpot,Integer> convertGarageSpotsListToMapForAvailableCheck(){
    	Map<GarageSpot, Integer> garageSportsMap = new HashMap<GarageSpot, Integer>();
    	
    	for(GarageSpot spot : garageSpots) {
    		garageSportsMap.put(spot, spot.getCountOfPlacesForCars());
    	}
    	
    	return garageSportsMap;
    }
    
    private Map<Master,Boolean> convertMastersListToMapForAvailableCheck(){
    	Map<Master, Boolean> garageSportsMap = new HashMap<Master, Boolean>();
    	
    	for(Master master : masters) {
    		garageSportsMap.put(master, true);
    	}
    	
    	return garageSportsMap;
    }
    
    public LocalDate getNearestAvailableDate() {
		LocalDate date= LocalDate.now();
    	while(true) {
    		if(getCountOfAvaliablePlacesOnDate(date)>0)
    			return date;
    		else 
    			date = date.plusDays(1);
    	}
		
	}
    
    public List<GarageSpot> getAvailableGarageSpotsOnDate(LocalDate date){
    	List<GarageSpot> availableGarageSpots = new ArrayList<GarageSpot>();
    	Map<GarageSpot,Integer> garageSpotsMap= convertGarageSpotsListToMapForAvailableCheck();
    	
    	for(Order order : orders) {
    		if(order.isActive()) {
    			if(!(order.getStartTime().isAfter(date) || order.getEstimatedEndTime().isBefore(date))) {
    				garageSpotsMap.put(order.getGarageSpot(),garageSpotsMap.get(order.getGarageSpot())-1);
    			}
    		}
    	}
    	
    	for(Map.Entry<GarageSpot, Integer> entry: garageSpotsMap.entrySet()) {
    		   GarageSpot key = entry.getKey();
    		   int value = entry.getValue();
    		   if(value > 0) availableGarageSpots.add(key);
    	}
    	
    	return availableGarageSpots;
    }
    
    
    public int getCountOfAvaliablePlacesOnDate(LocalDate date) {
    	Map<GarageSpot,Integer> garageSpotsMap = convertGarageSpotsListToMapForAvailableCheck();
    	Map<Master,Boolean> mastersMap = convertMastersListToMapForAvailableCheck();
    	int countOfFreeMasters = 0;
    	int countOfFreeSpots = 0;
    	for(Order order : orders) {
    		if(order.isActive()) {
    			if(!(order.getStartTime().isAfter(date) || order.getEstimatedEndTime().isBefore(date))) {
    				garageSpotsMap.put(order.getGarageSpot(),garageSpotsMap.get(order.getGarageSpot())-1);
    				mastersMap.put(order.getMaster(), false);	
    			}
    		}
    	}
    	
    	for(Map.Entry<GarageSpot, Integer> entry: garageSpotsMap.entrySet()) {
 		   int value = entry.getValue();
 		   if(value > 0) countOfFreeSpots+=value;
    	}
    	
    	for(Map.Entry<Master, Boolean> entry: mastersMap.entrySet()) {
 		   Boolean value = entry.getValue();
 		   if(value) countOfFreeMasters++;
    	}
    	
    	return countOfFreeMasters<countOfFreeSpots ? countOfFreeMasters:countOfFreeSpots  ;
    }
    
    public List<Order> getOrdersPerformedByMaster(Master master){
    	return  orders.stream()
    			.filter(order -> order.getMaster().equals(master) &&  order.isActive()) 
    			.collect(Collectors.toList());
    }
    
    public Master getMasterWorkedOnOrder(Order order) {
    	return order.getMaster();
    }
    
    public List<Order> getActiveOrders() {
        return orders.stream()
        		.filter(order -> order.isActive())
        		.collect(Collectors.toList());
    }
    
    public List<Order> getOrdersSortedBySubmissionDate() {
        return orders.stream()
            .sorted((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
            .collect(Collectors.toList());
    }
    
    public List<Order> getOrdersSortedByEstimatedCompletion() {
        return orders.stream()
            .sorted((o1, o2) -> o1.getEstimatedEndTime().compareTo(o2.getEstimatedEndTime()))
            .collect(Collectors.toList());
    }

    public List<Order> getOrdersSortedByPrice() {
        return orders.stream()
            .sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()))
            .collect(Collectors.toList());
    }
    
    public List<Order> getOrdersSortedByPriceDesc() {
        return orders.stream()
            .sorted((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()))
            .collect(Collectors.toList());
    }

    public List<Order> getOrdersSortedByPlannedStart() {
        return orders.stream()
            .sorted((o1, o2) -> o1.getPlanedStartTime().compareTo(o2.getPlanedStartTime()))
            .collect(Collectors.toList());
    }
    
    public List<Order> getCurrentOrdersSortedByPrice() {
        return orders.stream()
            .filter(order -> order.isActive())
            .sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()))
            .collect(Collectors.toList());
    }
    
    public List<Order> getCurrentOrdersSortedByPriceDesc() {
        return orders.stream()
            .filter(order -> order.isActive())
            .sorted((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()))
            .collect(Collectors.toList());
    }

    public List<Order> getCurrentOrdersSortedBySubmissionDate() {
        return orders.stream()
            .filter(order -> order.isActive())
            .sorted((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
            .collect(Collectors.toList());
    }
    
    public List<Order> getCurrentOrdersSortedByCompletionDate() {
        return orders.stream()
            .filter(order -> order.isActive())
            .sorted((o1, o2) -> o1.getEstimatedEndTime().compareTo(o2.getEstimatedEndTime()))
            .collect(Collectors.toList());
    }

    public List<Order> getOrdersByStatusAndDateRange(OrderStatus status, LocalDate startDate, LocalDate endDate) {
        return orders.stream()
            .filter(order -> order.getStatus().equals(status))
            .filter(order -> !order.getStartTime().isBefore(startDate) && 
                            !order.getStartTime().isAfter(endDate))
            .sorted((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
            .collect(Collectors.toList());
    }
    
    public List<Master> getMastersSortedByName() {
        return masters.stream()
            .sorted((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()))
            .collect(Collectors.toList());
    }

    public List<Master> getMastersSortedByWorkload() {
        return masters.stream()
            .sorted((m1, m2) -> {
                long workload1 = getMasterWorkload(m1);
                long workload2 = getMasterWorkload(m2);
                return Long.compare(workload2, workload1);
            })
            .collect(Collectors.toList());
    }
    
    public int getMasterWorkload(Master master) {
        return (int) orders.stream()
            .filter(order -> order.getMaster().equals(master))
            .filter(order -> order.getStatus() == OrderStatus.PENDING || 
                            order.getStatus() == OrderStatus.IN_PROGRESS)
            .count();
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