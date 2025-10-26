package subtask_4;

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
		System.out.println();
		
		autoService.addOrder("Олег Васильев", "Toyota Camry", "Замена масла", 1, 1, 2, 0);
		autoService.addOrder("Марина Иванова", "Honda Civic", "Ремонт кузова", 2, 2, 0, 20);
        autoService.addOrder("Дмитрий Смирнов", "BMW X5", "Диагностика электрики", 3, 3, 1, 1);
		System.out.println();
		
        autoService.printListOfMasters();
        autoService.printListOfGarageSpots();
        autoService.printListOfOrders();
		System.out.println();
        
        autoService.completeOrder(1);
        autoService.cancelOrder(2);
        autoService.shiftOrderTime(3, java.time.Duration.ofHours(1));
		System.out.println();
		
        autoService.removeMaster(2);
        autoService.removeGarageSpot(2);
		System.out.println();
		
        autoService.printListOfMasters();
        autoService.printListOfGarageSpots();
        autoService.printListOfOrders();
		System.out.println();
	}
}

