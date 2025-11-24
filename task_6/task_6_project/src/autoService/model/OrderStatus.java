package autoService.model;

public enum OrderStatus {
    PENDING("На рассмотрении"), 
    IN_PROGRESS("В работе"),
    COMPLETED("Завершён"),
    CANCELLED("Отменён"),
    DELETED("Удалён");
    
    private String title;
    
    OrderStatus(String title) {
    	this.title = title;
	}

	@Override
    public String toString() {
        return title;
    }
	
	public static OrderStatus getOrderStatusByStr(String title) {
		for(OrderStatus status : values()) {
			if(status.toString().equals(title)) return status;
		}
		return null;
	}
}
