package autoService.controller;

import java.awt.Container;
import java.math.BigDecimal;
import java.security.cert.CertPathValidatorResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import autoService.exception.CsvOperationException;
import autoService.exception.ParsingException;
import autoService.model.AutoService;
import autoService.model.GarageSpot;
import autoService.model.Master;
import autoService.model.Order;
import autoService.model.OrderStatus;
import autoService.service.CsvService;

public class AutoServiceController {
	private static AutoServiceController instance;
    private  AutoService autoService;
    
    private AutoServiceController() {
        this.autoService = new AutoService();
    }
    
    public static AutoServiceController getInstance() {
        if (instance == null) {
            instance = new AutoServiceController();
        }
        return instance;
    }
	
    public AutoService getAutoService() {
    	return autoService;
    } 
    
    public void addMaster(String name, String specialization) {
        autoService.addMaster(name, specialization);
    }
    
    public void removeMaster(int masterId) {
        autoService.removeMaster(masterId);
    }
    
    public List<Master> getAllMasters() {
        return autoService.getMasters();
    }
    
    public Master findMasterById(int id) {
        return autoService.findMasterById(id);
    }
    
    public void addGarageSpot(String number) {
        autoService.addGarageSpot(number);
    }
    
    public void removeGarageSpot(int spotId) {
        autoService.removeGarageSpot(spotId);
    }
    
    public List<GarageSpot> getAllGarageSpots() {
        return autoService.getGarageSpots();
    }
    
    public void addOrder(String clientName, String carModel, String description, 
                        int masterId, int spotId, LocalDate plannedStart, 
                        int daysOfWork, BigDecimal price) {
        autoService.addOrder(clientName, carModel, description, masterId, 
                           spotId, plannedStart, daysOfWork, price);
    }
    
    public void completeOrder(int orderId) {
        autoService.completeOrder(orderId);
    }
    
    public void cancelOrder(int orderId) {
        autoService.cancelOrder(orderId);
    }
    
    public void removeOrder(int orderId) {
        autoService.removeOrder(orderId);
    }
    
    public void completeOrder(Order order) {
        autoService.completeOrder(order);
    }
    
    public void cancelOrder(Order order) {
        autoService.cancelOrder(order);
    }
    
    public void removeOrder(Order order) {
        autoService.removeOrder(order);
    }
    
    public void shiftOrderTime(Order order, int days, boolean shiftToFuture) {
       autoService.shiftOrderTime(order, days, shiftToFuture);
    }
    
    public List<Order> getAllOrders() {
        return autoService.getOrders();
    }
    
    public Order findOrderById(int id) {
        return autoService.findOrderById(id);
    }
    
    public Order findActiveOrderById(int id) {
        return autoService.findActiveOrderById(id);
    }
    
    public List<Order> getActiveOrders() {
        return autoService.getActiveOrders();
    }
    
    public List<Order> getOrdersSortedBySubmissionDate() {
        return autoService.getOrdersSortedBySubmissionDate();
    }
    
    public List<Order> getOrdersSortedByPrice() {
        return autoService.getOrdersSortedByPrice();
    }
    
    public List<Order> getCurrentOrdersSortedByPrice() {
        return autoService.getCurrentOrdersSortedByPrice();
    }
    
    public List<Master> getMastersSortedByWorkload() {
        return autoService.getMastersSortedByWorkload();
    }
    
    public List<GarageSpot> getAvailableGarageSpotsOnDate(LocalDate date) {
        return autoService.getAvailableGarageSpotsOnDate(date);
    }
    
    public int getMasterWorkload(Master master) {
        return autoService.getMasterWorkload(master);
    }
    
    public LocalDate getNearestAvailableDate() {
        return autoService.getNearestAvailableDate();
    }
    
    public List<Order> getOrdersPerformedByMaster(Master master) {
        return autoService.getOrdersPerformedByMaster(master);
    }
    
    public void importMastersFromCSV(String filename) {
    	try {
    		List<Map<String, String>> data = CsvService.readFromCSV(filename);
    		for(Map<String, String> row : data) {
    			
    			importMasterFromMap(row);
    		}
    		
		}catch (ParsingException e) {
			throw new CsvOperationException("Ошибка при импорте мастеров из CSV, данные повреждены: " + e.getMessage(),e);
		}catch (Exception e) {
			throw new CsvOperationException("Ошибка при импорте мастеров из CSV: " + e.getMessage(),e);
		}
    	finally {
			autoService.updateNextMasterId();
		}
    }
    
    private void importMasterFromMap(Map<String, String> row) {
    	try{
    		int id = Integer.parseInt(row.get("id"));
    		String name = row.get("name");
    		String specialization = row.get("specialization");
    		Master master = findMasterById(id);
    		if(master!=null) {
    			master.setName(name);
    			master.setSpecialization(specialization);
    			return;
    		}
    		autoService.addMaster(name, specialization);
    		
    	}catch (Exception e) {
			throw new ParsingException(e.getMessage(),e);
		}
        
    }
    
    public void importGarageSpotsFromCSV(String filename) {
    	try {
    		List<Map<String, String>> data = CsvService.readFromCSV(filename);
    		for(Map<String, String> row : data) {
    			importGarageSpotFromMap(row);
    		}
    		
		}catch (ParsingException e) {
			throw new CsvOperationException("Ошибка при импорте гаражей из CSV, данные повреждены: " + e.getMessage(),e);
		}catch (Exception e) {
			throw new CsvOperationException("Ошибка при импорте гаражей из CSV: " + e.getMessage(),e);
		}
    	finally {
			autoService.updateNextSpotId();
		}
    }
    
    private void importGarageSpotFromMap(Map<String, String> row) {
    	try{
    		int id = Integer.parseInt(row.get("id"));
    		String box = row.get("box");
    		GarageSpot spot = autoService.findSpotById(id);
    		if(spot!=null) {
    			spot.setBox(box);
     			return;
    		}
    		autoService.addGarageSpot(box);
    		
    	}catch (Exception e) {
    		throw new ParsingException(e.getMessage(),e);
		}
        
    }
    
    public void importOrdersFromCSV(String filename) {
    	try {
    		List<Map<String, String>> data = CsvService.readFromCSV(filename);
    		for(Map<String, String> row : data) {
    			importOrderFromMap(row);
    		}
    		
		}catch (ParsingException e) {
			throw new CsvOperationException("Ошибка при импорте заказов из CSV, данные повреждены: " + e.getMessage(),e);
		}catch (Exception e) {
			throw new CsvOperationException("Ошибка при импорте заказов из CSV: " + e.getMessage(),e);
		}
    	finally {
			autoService.updateNextSpotId();
		}
    }
    
    private void importOrderFromMap(Map<String, String> row) {
    	try{
    		int id = Integer.parseInt(row.get("id"));
    		String clientName = row.get("clientName");
    	    String carModel = row.get("carModel");
    	    String description = row.get("description");
    	    Master master = autoService.findMasterById(Integer.parseInt(row.get("masterId")));
    	    GarageSpot spot = autoService.findSpotById(Integer.parseInt(row.get("garageSpotId")));
    	    OrderStatus status = OrderStatus.getOrderStatusByStr(row.get("status"));
    	    LocalDate startTime = LocalDate.parse(row.get("startTime"));
    	    LocalDate planedStartTime = LocalDate.parse(row.get("planedStartTime")); 
    	    LocalDate estimatedEndTime = LocalDate.parse(row.get("estimatedEndTime")); 
    	    BigDecimal price = new BigDecimal(row.get("price"));

    	    Order order = autoService.findOrderById(id);
    		if(order!=null) {
    			if(status == null) throw new ParsingException("Не удалось преобразовать статус");
    			if(master == null) throw new ParsingException("Не удалось найти мастера");
    			if(spot == null) throw new ParsingException("Не удалось найти гараж");
    			if(startTime.isAfter(estimatedEndTime)) throw new ParsingException("Проблема с датами начала и окончания работ");
    			order.setClientName(clientName);
    			order.setCarModel(carModel);
    			order.setDescription(description);
    			order.setStatus(status);
    			order.setMaster(master);
    			order.setGarageSpot(spot);
    			order.setStartTime(startTime);
    			order.setPlanedStartTime(planedStartTime);
    			order.setEstimatedEndTime(estimatedEndTime);
    			order.setPrice(price);
     			return;
    		}
    		
    		autoService.addOrder(clientName, carModel, description,Integer.parseInt(row.get("masterId")) , Integer.parseInt(row.get("garageSpotId")), 
    				status, planedStartTime, startTime, estimatedEndTime,  price);
    		
    	}catch (Exception e) {
    		throw new ParsingException(e.getMessage(),e);
		}
        
    }
    
    public void exportMastersToCSV(String filename) {
		String header = "id,name,specialization";
		List<Master> masters = autoService.getMasters();
		List<String> data = new ArrayList<String>(masters.size());
		for(Master master : masters) {
			data.add(master.getId()+","+master.getName()+","+master.getSpecialization());
		}
		
		try {
			CsvService.writeIntoCSV(filename,header,data);
		}catch (Exception e) {
			throw new CsvOperationException("Ошибка при экспорте мастеров в CSV: " + e.getMessage(),e);
		}
	}
    
    public void exportGarageSpotsToCSV(String filename) {
		String header = "id,box";
		List<GarageSpot> spots = autoService.getGarageSpots();
		List<String> data = new ArrayList<String>(spots.size());
		for(GarageSpot spot : spots) {
			data.add(spot.getId()+","+spot.getBox());
		}
		
		try {
			CsvService.writeIntoCSV(filename,header,data);
		}catch (Exception e) {
			throw new CsvOperationException("Ошибка при экспорте гаражных мест в CSV: " + e.getMessage(),e);
		}
	}
    
    public void exportOrdersSpotsToCSV(String filename) {
		String header = "id,clientName,carModel,description,masterId,garageSpotId,status,startTime,planedStartTime,estimatedEndTime,price";
		List<Order> orders = autoService.getOrders();
		List<String> data = new ArrayList<String>(orders.size());
		for(Order order : orders) {
			data.add(order.getId()+","+order.getClientName()+","+order.getCarModel()+","+order.getDescription()+","+order.getMaster().getId()+","+
					order.getGarageSpot().getId()+","+order.getStatus()+","+order.getStartTime()+","+order.getPlanedStartTime()+","+order.getEstimatedEndTime()+","+order.getPrice());
		}
		
		try {
			CsvService.writeIntoCSV(filename,header,data);
		}catch (Exception e) {
			throw new CsvOperationException("Ошибка при экспорте заказов в CSV: " + e.getMessage(),e);
		}	
	}
    
    
}
