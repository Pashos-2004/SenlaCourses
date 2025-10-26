package subtask_4;

class AutoService {
    private java.util.List<Master> masters;
    private java.util.List<GarageSpot> garageSpots;
    private java.util.List<Order> orders;
    private int nextMasterId;
    private int nextSpotId;
    private int nextOrderId;
    
    public AutoService() {
        this.masters = new java.util.ArrayList<>();
        this.garageSpots = new java.util.ArrayList<>();
        this.orders = new java.util.ArrayList<>();
        this.nextMasterId = 1;
        this.nextSpotId = 1;
        this.nextOrderId = 1;
    }
    
    public void addMaster(String name, String specialization) {
        Master master = new Master(nextMasterId++, name, specialization);
        masters.add(master);
        System.out.println("Добавлен мастер: " + master);
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
    
    public void removeGarageSpot(int spotId) {
        boolean removed = garageSpots.removeIf(spot -> spot.getId() == spotId);
        if (removed) {
            System.out.println("Гаражное место с ID " + spotId + " удалено");
        } else {
            System.out.println("Гаражное место с ID " + spotId + " не найдено");
        }
    }
    
    public void addOrder(String clientName, String carModel, String description, 
                         int masterId, int spotId) {
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
        if (!spot.isAvailable()) {
            System.out.println("Гаражное место " + spot.getBox() + " занято");
            return;
        }
        
        Order order = new Order(nextOrderId++, clientName, carModel, description, master, spot);
        orders.add(order);
        spot.setAvailable(false);
        System.out.println("Добавлен заказ: " + order);
    }
    
    public void removeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            orders.remove(order);
            order.getGarageSpot().setAvailable(true);
            System.out.println("Заказ с ID " + orderId + " удален");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void completeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.COMPLETED);
            order.getGarageSpot().setAvailable(true);
            System.out.println("Заказ с ID " + orderId + " завершен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.CANCELLED);
            order.getGarageSpot().setAvailable(true);
            System.out.println("Заказ с ID " + orderId + " отменен");
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    public void shiftOrderTime(int orderId, java.time.Duration duration) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.shiftTime(duration);
            System.out.println("Время выполнения заказа " + orderId + " сдвинуто на " + duration);
        } else {
            System.out.println("Заказ с ID " + orderId + " не найден");
        }
    }
    
    private Master findMasterById(int id) {
        return masters.stream()
                .filter(master -> master.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    private GarageSpot findSpotById(int id) {
        return garageSpots.stream()
                .filter(spot -> spot.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    private Order findOrderById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
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
}