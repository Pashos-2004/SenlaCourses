package autoService;

import java.math.BigDecimal;
import java.time.LocalDate;

import autoService.config.StateManager;
import autoService.controller.AutoServiceController;
import autoService.model.AutoService;
import autoService.model.OrderStatus;
import autoService.view.AutoServiceConsoleUI;

public class TestRun {

	public static void main(String args[]) {
		System.out.print(OrderStatus.valueOf("PENDING"));
		
		System.out.println("== Тест работы автосервиса ==");
		//initializeBasicDataForTest();
		AutoServiceConsoleUI ui = AutoServiceConsoleUI.getInstance();
        ui.run();
        StateManager.getInstance().saveState(AutoServiceController.getInstance().getAutoService());
	}
	
	public static void initializeBasicDataForTest() {
        
		AutoServiceController autoServiceController = AutoServiceController.getInstance();
        
		autoServiceController.addMaster("Булгаков Даниил Никитич", "Моторист");
		autoServiceController.addMaster("Евсеев Александр Максимович", "Кузов");
		autoServiceController.addMaster("Соколов Егор Иванович ", "Электрик");
		autoServiceController.addMaster("Негодин Витор Олегович ", "шиномонтажник");
		
		autoServiceController.addGarageSpot("A1");
		autoServiceController.addGarageSpot("A2");
		autoServiceController.addGarageSpot("B1");
		autoServiceController.addGarageSpot("B2");
		
		autoServiceController.addOrder("Олег Васильев", "Toyota Camry", "Замена масла", 1,1, LocalDate.now(),  0, BigDecimal.valueOf(1111.2));
		autoServiceController.addOrder("Олег Васильев", "Toyota Camry", "Замена масла", 1,1, LocalDate.now().plusDays(80),  0, BigDecimal.valueOf(1111.2));
		autoServiceController.addOrder("Марина Иванова", "Honda Civic", "Ремонт кузова", 2, 2,LocalDate.now(),  20, BigDecimal.valueOf(18600.21));
		autoServiceController.addOrder("Ксения Вито", "K5", "Замена катушек", 4, 3 , LocalDate.now(),  0, BigDecimal.valueOf(1500.21));
		autoServiceController.addOrder("Дмитрий Смирнов", "BMW X5", "Диагностика электрики", 3, 3 , LocalDate.now().plusDays(2),  1, BigDecimal.valueOf(18600.21));
		autoServiceController.addOrder("Инван Петров", "Granta", "Настройка зазора клапанов", 1, 3 , LocalDate.now().plusDays(2),  1, BigDecimal.valueOf(1600.10));
		autoServiceController.addOrder("Инван Петров", "Granta", "Настройка зазора клапанов", 2, 4 , LocalDate.now().plusDays(2),  1, BigDecimal.valueOf(18600.91));
		autoServiceController.addOrder("Инван Петров", "Granta", "Настройка зазора клапанов", 1, 4 , LocalDate.now().plusDays(2),  1, BigDecimal.valueOf(1450.8));
		autoServiceController.addOrder("Инван Иваныч", "Волга", "Сбор двигателя", 1, 4 , LocalDate.now().plusDays(3),  1, BigDecimal.valueOf(1450.8));
		autoServiceController.addOrder("Анатолий Кузьмин", "Nissan note", "Диагностика стартера",3,4 , LocalDate.now(),  1, BigDecimal.valueOf(18880.88));
		
		autoServiceController.cancelOrder(7);;
		autoServiceController.completeOrder(1);
		autoServiceController.cancelOrder(2);
        autoServiceController.shiftOrderTime(autoServiceController.findOrderById(3), 1, true);
		
		autoServiceController.removeMaster(2);
		autoServiceController.removeGarageSpot(2);
		autoServiceController.removeOrder(autoServiceController.findOrderById(3));
	}
	
}

