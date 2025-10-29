package subtask_3;

public class ChassisStep implements LineStep {
	 @Override
	 public ProductPart buildProductPart() {
	     System.out.println("Создано шасси автомобиля");
	     return new Chassis();
	     }
}
