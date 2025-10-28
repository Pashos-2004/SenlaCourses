package subtask_3;

public class BodyStep implements LineStep {
	 @Override
	 public ProductPart buildProductPart() {
	     System.out.println("Создан кузов автомобиля");
	     return new CarBody();
	     }
}
