package autoService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestRun {

	public static void main(String args[]) {
		System.out.println("== Тест работы автосервиса ==");
        
		AutoService autoService = new AutoService();
        
		autoService.addMaster("Булгаков Даниил Никитич", "Моторист");
		autoService.addMaster("Евсеев Александр Максимович", "Кузов");
		autoService.addMaster("Соколов Егор Иванович ", "Электрик");
		autoService.addMaster("Негодин Витор Олегович ", "шиномонтажник");
		System.out.println();
		
		autoService.addGarageSpot("A1");
		autoService.addGarageSpot("A2");
		autoService.addGarageSpot("B1");
		autoService.addGarageSpot("B2");
		System.out.println();
		
		autoService.addOrder("Олег Васильев", "Toyota Camry", "Замена масла", 1,1, LocalDate.now(),  0, new BigDecimal("1111.2"));
		autoService.addOrder("Олег Васильев", "Toyota Camry", "Замена масла", 1,1, LocalDate.now().plusDays(80),  0, new BigDecimal("1111.2"));
		autoService.addOrder("Марина Иванова", "Honda Civic", "Ремонт кузова", 2, 2,LocalDate.now(),  20, new BigDecimal("18600.21"));
		autoService.addOrder("Ксения Вито", "K5", "Замена катушек", 4, 3 , LocalDate.now(),  0, new BigDecimal("1500.21"));
        autoService.addOrder("Дмитрий Смирнов", "BMW X5", "Диагностика электрики", 3, 3 , LocalDate.now().plusDays(2),  1, new BigDecimal("18600.21"));
        autoService.addOrder("Инван Петров", "Granta", "Настройка зазора клапанов", 1, 3 , LocalDate.now().plusDays(2),  1, new BigDecimal("1600.10"));
        autoService.addOrder("Инван Петров", "Granta", "Настройка зазора клапанов", 2, 4 , LocalDate.now().plusDays(2),  1, new BigDecimal("18600.91"));
        autoService.addOrder("Инван Петров", "Granta", "Настройка зазора клапанов", 1, 4 , LocalDate.now().plusDays(2),  1, new BigDecimal("1450.8"));
        autoService.addOrder("Инван Иваныч", "Волга", "Сбор двигателя", 1, 4 , LocalDate.now().plusDays(3),  1, new BigDecimal("1450.8"));
        autoService.addOrder("Анатолий Кузьмин", "Nissan note", "Диагностика стартера",3,4 , LocalDate.now(),  1, new BigDecimal("18880.88"));
		System.out.println();
		
		autoService.cancelOrder(7);;
		
        autoService.printListOfMasters();
        autoService.printListOfGarageSpots();
        autoService.printListOfOrders();
		System.out.println();
        
        autoService.completeOrder(1);
        autoService.cancelOrder(2);
        autoService.shiftOrderTime(autoService.findOrderById(3), 1, true);
		System.out.println();
		
        autoService.removeMaster(2);
        autoService.removeGarageSpot(2);
        autoService.removeOrder(autoService.findOrderById(3));
		System.out.println();
		
        autoService.printListOfMasters();
        autoService.printListOfGarageSpots();
        autoService.printListOfOrders();
		System.out.println();
		
		autoService.showListOfAvailableGarageSpots();
		System.out.println();
		
		autoService.showOrdersPerformedByMaster(autoService.findMasterById(3));
		System.out.println();
		
		autoService.showMasterWorkedOnOrder(autoService.findOrderById(4));
		System.out.println();
		
		LocalDate futureDate = LocalDate.now();
		System.out.println("Число свободных мест на " + futureDate + " = " + autoService.getCountOfAvaliablePlacesOnDate(futureDate) );
		futureDate = futureDate.plusDays(2);
		System.out.println("Число свободных мест на " + futureDate + " = " + autoService.getCountOfAvaliablePlacesOnDate(futureDate) );
		System.out.println();
		
		System.out.println("Ближайшая свободная дата " + autoService.getNearestAvailableDate());
		System.out.println();
		
		autoService.printOrdersSortedBySubmissionDate();
		System.out.println();
		autoService.printOrdersSortedByEstimatedCompletion();
		System.out.println();
		autoService.printOrdersSortedByPlannedStart();
		System.out.println();
		autoService.printOrdersSortedByPrice();
		System.out.println();

		autoService.printMastersSortedByName();
		System.out.println();
		autoService.printMastersSortedByWorkload();
		System.out.println();

		autoService.printCurrentOrdersSortedBySubmissionDate();
		System.out.println();
		autoService.printCurrentOrdersSortedByCompletionDate();
		System.out.println();
		autoService.printCurrentOrdersSortedByPrice();
		System.out.println();

		LocalDate weekAgo = LocalDate.now().minusDays(7);
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		
		autoService.printCompletedOrdersByDateRange(weekAgo, tomorrow);
		System.out.println();
		autoService.printCancelledOrdersByDateRange(weekAgo, tomorrow);
		System.out.println();
		autoService.printDeletedOrdersByDateRange(weekAgo, tomorrow);
		
		
	}
}

