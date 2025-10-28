package subtask_3;

public class EngineStep implements LineStep {
	 @Override
	 public ProductPart buildProductPart() {
	     System.out.println("Создан двигатель автомобиля");
	     return new Engine();
	     }
}

