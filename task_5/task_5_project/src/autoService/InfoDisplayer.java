package autoService;

import java.time.LocalDate;
import java.util.List;

public class InfoDisplayer {
	
	public static <T> void  printListWithNumeration(List<T> list) {
		long counter = 1;
		for(T element : list) {
			System.out.println(counter+"."+ element);
			counter+=1;
		}
	}
	
	public static void printListOfAvailableGarageSpots(AutoService autoService) {
    	System.out.println("Свободные гаражные места :");
    	autoService.getAvailableGarageSpotsOnDate(LocalDate.now()).stream()
    		.forEach(System.out::println);
    }
	
	public static void printOrdersPerformedByMaster(AutoService autoService, Master master) {
    	System.out.println("Все заказы выполняемые мастором " + master + " :");
    	List<Order> ordersPerformedByMaster = autoService.getOrdersPerformedByMaster(master);
    	
    	if(ordersPerformedByMaster.size()>0) {
    		printListWithNumeration(ordersPerformedByMaster);
    	}
    	else {
    		System.out.println("Мастер свободен");
    	}
	}
	
	public static void printMasterWorkedOnOrder(AutoService autoService, Order order) {
    	System.out.println("Мастер раюотающий над заказом " + order + " :");
    	System.out.println(autoService.getMasterWorkedOnOrder(order));
    }
	
	public static void printOrdersSortedBySubmissionDate(AutoService autoService) {
        System.out.println("Заказы (сортировка по дате подачи):");
        printListWithNumeration(autoService.getOrdersSortedBySubmissionDate());
    }
	
	public static void printOrdersSortedByEstimatedCompletion(AutoService autoService) {
        System.out.println("Заказы (сортировка по дате выполнения):");
        printListWithNumeration(autoService.getOrdersSortedByEstimatedCompletion());
    }
	
	public static void printOrdersSortedByPrice(AutoService autoService) {
		System.out.println("Заказы (сортировка по цене):");
		printListWithNumeration(autoService.getOrdersSortedByPrice());
	}
	
	public static void printOrdersSortedByPriceDesc(AutoService autoService) {
        System.out.println("Заказы (сортировка по цене по убыванию):");
        printListWithNumeration(autoService.getOrdersSortedByPriceDesc());
    }
	
	public static void printOrdersSortedByPlannedStart(AutoService autoService) {
        System.out.println("Заказы (сортировка по запланированной дате начала):");
        printListWithNumeration(autoService.getOrdersSortedByPlannedStart());
    }
	
	public static void printCurrentOrdersSortedByPrice(AutoService autoService) {
        System.out.println("Текущие заказы (сортировка по цене):");
        printListWithNumeration(autoService.getCurrentOrdersSortedByPrice());
    }
	
	public static void printCurrentOrdersSortedByPriceDesc(AutoService autoService) {
        System.out.println("Текущие заказы (сортировка по цене по убыванию):");
        printListWithNumeration(autoService.getCurrentOrdersSortedByPrice());
    }
	
	public static void printCurrentOrdersSortedBySubmissionDate(AutoService autoService) {
        System.out.println("Текущие заказы (сортировка по дате подачи):");
        printListWithNumeration(autoService.getCurrentOrdersSortedBySubmissionDate());
    }
	
	public static void printCurrentOrdersSortedByCompletionDate(AutoService autoService) {
        System.out.println("Текущие заказы (сортировка по дате выполнения):");
        printListWithNumeration(autoService.getCurrentOrdersSortedByCompletionDate());
    }
	
	private static void printOrdersByStatusAndDateRange(AutoService autoService, OrderStatus status, LocalDate startDate, LocalDate endDate) {
        System.out.println("Заказы со статусом " + status + " за период с " + startDate + " по " + endDate + ":");
        printListWithNumeration(autoService.getOrdersByStatusAndDateRange(status, startDate, endDate));
    }
	
	public static void printCompletedOrdersByDateRange(AutoService autoService, LocalDate startDate, LocalDate endDate) {
        printOrdersByStatusAndDateRange(autoService, OrderStatus.COMPLETED, startDate, endDate);
    }
    public static void printDeletedOrdersByDateRange(AutoService autoService, LocalDate startDate, LocalDate endDate) {
        printOrdersByStatusAndDateRange(autoService, OrderStatus.DELETED, startDate, endDate);
    }

    public static void printCancelledOrdersByDateRange(AutoService autoService, LocalDate startDate, LocalDate endDate) {
        printOrdersByStatusAndDateRange(autoService, OrderStatus.CANCELLED, startDate, endDate);
    }
    
    public static void printMastersSortedByName(AutoService autoService) {
        System.out.println("Мастера (сортировка по алфавиту):");
        printListWithNumeration(autoService.getMastersSortedByName());
    }
    
    public static void printMastersSortedByWorkload(AutoService autoService) {
        System.out.println("Мастера (сортировка по занятости):");
        int counter = 1;
        for(Master master : autoService.getMastersSortedByWorkload()) {
        	int workload = autoService.getMasterWorkload(master);
        	System.out.println(counter + "."+master + ", текущих заказов: " + workload);
        	counter+=1;
        }
    }
    
    public static void printListOfMasters(AutoService autoService) {
        System.out.println("Мастера :");
        printListWithNumeration(autoService.getMasters());
    }
    
    public static void printListOfGarageSpots(AutoService autoService) {
        System.out.println("Гаражные места :");
        printListWithNumeration(autoService.getGarageSpots());
    }
    
    public static void printListOfOrders(AutoService autoService) {
        System.out.println("Заказы : ");
        printListWithNumeration(autoService.getOrders());
    }
    
    
}
