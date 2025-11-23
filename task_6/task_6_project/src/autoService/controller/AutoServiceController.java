package autoService.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import autoService.model.AutoService;
import autoService.model.GarageSpot;
import autoService.model.Master;
import autoService.model.Order;

public class AutoServiceController {
	private static AutoServiceController instance;
    private AutoService autoService;
    
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
}
