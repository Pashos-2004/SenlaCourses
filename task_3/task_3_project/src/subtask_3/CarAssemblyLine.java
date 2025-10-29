package subtask_3;

public class CarAssemblyLine implements AssemblyLine {
	 private LineStep firstStep;
	 private LineStep secondStep;
	 private LineStep thirdStep;

	 public CarAssemblyLine(LineStep firstStep, LineStep secondStep, LineStep thirdStep) {
	     this.firstStep = firstStep;
	     this.secondStep = secondStep;
	     this.thirdStep = thirdStep;
	     System.out.println("Сборочная линия автомобилей создана");
	 }

	 @Override
	 public Product assembleProduct(Product product) {
	     System.out.println("\n=== НАЧАЛО СБОРКИ АВТОМОБИЛЯ ===");
	     
	     System.out.println("Этап 1: Сборка кузова...");
	     ProductPart firstPart = firstStep.buildProductPart();
	     product.installFirstPart(firstPart);
	     
	     System.out.println("Этап 2: Сборка шасси...");
	     ProductPart secondPart = secondStep.buildProductPart();
	     product.installSecondPart(secondPart);

	     System.out.println("Этап 3: Сборка двигателя...");
	     ProductPart thirdPart = thirdStep.buildProductPart();
	     product.installThirdPart(thirdPart);
	     System.out.println();
	     return product;
	     }
	}
