package autoService;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
        
        Order order = new Order(nextOrderId++, clientName, carModel, description, master, spot, planedStartTime ,startTime, daysOfWork, price);
        orders.add(order);
        
        System.out.println("Добавлен заказ: " + order);
    }
    
    public void addOrder(String clientName, String carModel, String description, int masterId, int spotId, 
    		LocalDate planedStartTime , int daysOfWork, BigDecimal price) {
        addOrder(clientName, carModel, description, masterId, spotId, planedStartTime, planedStartTime, daysOfWork, price);
    }
    
    
    
    private boolean isMasterAvailableOnDate(LocalDate startdate, LocalDate endDate,Master master) {
    	for(Order order : orders) {
    		if(order.getMaster().equals(master)) {
    			if(order.getStatus().equals(OrderStatus.PENDING) || order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
    				if(!((order.getStartTime().isAfter(startdate) && order.getStartTime().isAfter(endDate)) || order.getEstimatedEndTime().isBefore(startdate) && order.getEstimatedEndTime().isBefore(endDate) ))
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    private boolean isGarageAvailableOnDate(LocalDate startdate, LocalDate endDate,GarageSpot spot) {
    	for(Order order : orders) {
    		if(order.getGarageSpot().equals(spot)) {
    			if(order.getStatus().equals(OrderStatus.PENDING) || order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
    				if(!((order.getStartTime().isAfter(startdate) && order.getStartTime().isAfter(endDate)) || order.getEstimatedEndTime().isBefore(startdate) && order.getEstimatedEndTime().isBefore(endDate) ))
    				return false;
    			}
    		}
    	}
    	return true;
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
    
    public void completeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.COMPLETED);
            System.out.println("Заказ с ID " + orderId + " завершен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
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
        order.shiftTime(countOfDays,isShiftInFuture);
        System.out.println("Время выполнения заказа " + order + " сдвинуто на " + countOfDays + "дней");
        
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
    
    public Order findOrderById(int id) {
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
    
    
    private Map<GarageSpot,Boolean> convertGarageSpotsListToMapForAvailableCheck(){
    	Map<GarageSpot, Boolean> garageSportsMap = new HashMap<GarageSpot, Boolean>();
    	
    	for(GarageSpot spot : garageSpots) {
    		garageSportsMap.put(spot, true);
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
    	Map<GarageSpot,Boolean> garageSpotsMap= convertGarageSpotsListToMapForAvailableCheck();
    	
    	for(Order order : orders) {
    		if(order.getStatus().equals(OrderStatus.PENDING) || order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
    			if(!(order.getStartTime().isAfter(date) || order.getEstimatedEndTime().isBefore(date))) {
    				garageSpotsMap.put(order.getGarageSpot(),false);
    			}
    		}
    	}
    	
    	for(Map.Entry<GarageSpot, Boolean> entry: garageSpotsMap.entrySet()) {
    		   GarageSpot key = entry.getKey();
    		   Boolean value = entry.getValue();
    		   if(value) availableGarageSpots.add(key);
    	}
    	
    	return availableGarageSpots;
    }
    
    public void showListOfAvailableGarageSpots() {
    	System.out.println("Свободные гаражные места :");
    	List<GarageSpot> availableGarageSpots = getAvailableGarageSpotsOnDate(LocalDate.now());
    	
    	for(GarageSpot spot : availableGarageSpots)
    		System.out.println(spot);
    	
    }
    
    public int getCountOfAvaliablePlacesOnDate(LocalDate date) {
    	Map<GarageSpot,Boolean> garageSpotsMap = convertGarageSpotsListToMapForAvailableCheck();
    	Map<Master,Boolean> mastersMap = convertMastersListToMapForAvailableCheck();
    	int countOfFreeMasters = 0;
    	int countOfFreeSpots = 0;
    	for(Order order : orders) {
    		if(order.getStatus().equals(OrderStatus.PENDING) || order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
    			if(!(order.getStartTime().isAfter(date) || order.getEstimatedEndTime().isBefore(date))) {
    				garageSpotsMap.put(order.getGarageSpot(),false);
    				mastersMap.put(order.getMaster(), false);	
    			}
    		}
    	}
    	
    	for(Map.Entry<GarageSpot, Boolean> entry: garageSpotsMap.entrySet()) {
 		   Boolean value = entry.getValue();
 		   if(value) countOfFreeSpots++;
    	}
    	
    	for(Map.Entry<Master, Boolean> entry: mastersMap.entrySet()) {
 		   Boolean value = entry.getValue();
 		   if(value) countOfFreeMasters++;
    	}
    	
    	return countOfFreeMasters<countOfFreeSpots ? countOfFreeMasters:countOfFreeSpots  ;
    }
    
    public void showOrdersPerformedByMaster(Master master) {
    	System.out.println("Все заказы выполняемые мастором " + master + " :");
    	boolean isHaveOrders = false;
    	for(Order order : orders) {
    		if(order.getMaster().equals(master)) {
    			System.out.println(order);
    			isHaveOrders=true;
    		}
        }
    	if(!isHaveOrders) System.out.println("Мастер свободен");
    }
    
    public void showMasterWorkedOnOrder(Order order) {
    	System.out.println("Мастер раюотающий над заказом " + order + " :");
    	
    	for(Master master : masters) {
    		if(master.equals(order.getMaster())) System.out.println(order);
        }
    }
    
    public void printOrdersSortedBySubmissionDate() {
        System.out.println("Заказы (сортировка по дате подачи):");
        orders.stream()
            .sorted((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
            .forEach(System.out::println);
    }

    public void printOrdersSortedByEstimatedCompletion() {
        System.out.println("Заказы (сортировка по дате выполнения):");
        orders.stream()
            .sorted((o1, o2) -> o1.getEstimatedEndTime().compareTo(o2.getEstimatedEndTime()))
            .forEach(System.out::println);
    }
    
    public void printOrdersSortedByPrice() {
        System.out.println("Заказы (сортировка по цене):");
        orders.stream()
            .sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()))
            .forEach(System.out::println);
    }

    public void printOrdersSortedByPriceDesc() {
        System.out.println("Заказы (сортировка по цене по убыванию):");
        orders.stream()
            .sorted((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()))
            .forEach(System.out::println);
    }

    public void printOrdersSortedByPlannedStart() {
        System.out.println("Заказы (сортировка по запланированной дате начала):");
        orders.stream()
            .sorted((o1, o2) -> o1.getPlanedStartTime().compareTo(o2.getPlanedStartTime()))
            .forEach(System.out::println);
    }

    
    public void printCurrentOrdersSortedByPrice() {
        System.out.println("Текущие заказы (сортировка по цене):");
        orders.stream()
            .filter(order -> order.getStatus() == OrderStatus.PENDING || 
                            order.getStatus() == OrderStatus.IN_PROGRESS)
            .sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()))
            .forEach(System.out::println);
    }

    public void printCurrentOrdersSortedByPriceDesc() {
        System.out.println("Текущие заказы (сортировка по цене по убыванию):");
        orders.stream()
            .filter(order -> order.getStatus() == OrderStatus.PENDING || 
                            order.getStatus() == OrderStatus.IN_PROGRESS)
            .sorted((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()))
            .forEach(System.out::println);
    }

    public void printCompletedOrdersSortedByPrice(LocalDate startDate, LocalDate endDate) {
        System.out.println("Выполненные заказы (сортировка по цене):");
        List<Order> filteredOrders = getFilteredAndSortedOrders(
            order -> order.getStatus() == OrderStatus.COMPLETED &&
                    !order.getStartTime().isBefore(startDate) &&
                    !order.getStartTime().isAfter(endDate),
            (o1, o2) -> o1.getPrice().compareTo(o2.getPrice())
        );
        filteredOrders.forEach(System.out::println);
    }

    public void printMastersSortedByName() {
        System.out.println("Мастера (сортировка по алфавиту):");
        masters.stream()
            .sorted((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()))
            .forEach(System.out::println);
    }

    public void printMastersSortedByWorkload() {
        System.out.println("Мастера (сортировка по занятости):");
        masters.stream()
            .sorted((m1, m2) -> {
                long workload1 = getMasterWorkload(m1);
                long workload2 = getMasterWorkload(m2);
                return Long.compare(workload2, workload1);
            })
            .forEach(master -> {
                long workload = getMasterWorkload(master);
                System.out.println(master + ", текущих заказов: " + workload);
            });
    }

    private long getMasterWorkload(Master master) {
        return orders.stream()
            .filter(order -> order.getMaster().equals(master))
            .filter(order -> order.getStatus() == OrderStatus.PENDING || 
                            order.getStatus() == OrderStatus.IN_PROGRESS)
            .count();
    }

    public void printCurrentOrdersSortedBySubmissionDate() {
        System.out.println("Текущие заказы (сортировка по дате подачи):");
        orders.stream()
            .filter(order -> order.getStatus() == OrderStatus.PENDING || 
                            order.getStatus() == OrderStatus.IN_PROGRESS)
            .sorted((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
            .forEach(System.out::println);
    }

    public void printCurrentOrdersSortedByCompletionDate() {
        System.out.println("Текущие заказы (сортировка по дате выполнения):");
        orders.stream()
            .filter(order -> order.getStatus() == OrderStatus.PENDING || 
                            order.getStatus() == OrderStatus.IN_PROGRESS)
            .sorted((o1, o2) -> o1.getEstimatedEndTime().compareTo(o2.getEstimatedEndTime()))
            .forEach(System.out::println);
    }

    public void printOrdersByStatusAndDateRange(OrderStatus status, LocalDate startDate, LocalDate endDate) {
        System.out.println("Заказы со статусом " + status + " за период с " + startDate + " по " + endDate + ":");
        orders.stream()
            .filter(order -> order.getStatus().equals(status))
            .filter(order -> !order.getStartTime().isBefore(startDate) && 
                            !order.getStartTime().isAfter(endDate))
            .sorted((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()))
            .forEach(System.out::println);
    }

    public void printCompletedOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        printOrdersByStatusAndDateRange(OrderStatus.COMPLETED, startDate, endDate);
    }
    public void printDeletedOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        printOrdersByStatusAndDateRange(OrderStatus.DELETED, startDate, endDate);
    }

    public void printCancelledOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        printOrdersByStatusAndDateRange(OrderStatus.CANCELLED, startDate, endDate);
    }

    public List<Order> getFilteredAndSortedOrders(Predicate<Order> filter, 
                                                 Comparator<Order> comparator) {
        return orders.stream()
            .filter(filter)
            .sorted(comparator)
            .collect(java.util.stream.Collectors.toList());
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