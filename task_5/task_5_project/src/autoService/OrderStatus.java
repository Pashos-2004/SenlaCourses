package autoService;

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
        return "'"+title+"'";
    }
    
}
