package subtask_3;

class CarBody implements ProductPart {
 @Override
 public String getName() {
     return "Кузов автомобиля";
     }
}

class Chassis implements ProductPart {
 @Override
 public String getName() {
     return "Шасси автомобиля";
     }
}

class Engine implements ProductPart {
 @Override
 public String getName() {
     return "Двигатель автомобиля";
     }
}

class BodyStep implements LineStep {
 @Override
 public ProductPart buildProductPart() {
     System.out.println("Создан кузов автомобиля");
     return new CarBody();
     }
}

class ChassisStep implements LineStep {
 @Override
 public ProductPart buildProductPart() {
     System.out.println("Создано шасси автомобиля");
     return new Chassis();
     }
}

class EngineStep implements LineStep {
 @Override
 public ProductPart buildProductPart() {
     System.out.println("Создан двигатель автомобиля");
     return new Engine();
     }
}

class Car implements Product {
 private ProductPart body;
 private ProductPart chassis;
 private ProductPart engine;

 @Override
 public void installFirstPart(ProductPart part) {
     this.body = part;
     System.out.println("Установлен кузов: " + part.getName());
     }

 @Override
 public void installSecondPart(ProductPart part) {
     this.chassis = part;
     System.out.println("Установлено шасси: " + part.getName());
     }

 @Override
 public void installThirdPart(ProductPart part) {
     this.engine = part;
     System.out.println("Установлен двигатель: " + part.getName());
     }

 @Override
 public void displayInfo() {
     System.out.println("=== СБОРКА АВТОМОБИЛЯ ЗАВЕРШЕНА ===");
     System.out.println("Кузов: " + (body != null ? body.getName() : "Отсутствует"));
     System.out.println("Шасси: " + (chassis != null ? chassis.getName() : "Отсутствует"));
     System.out.println("Двигатель: " + (engine != null ? engine.getName() : "Отсутствует"));
     System.out.println("===================================");
     }
}

class CarAssemblyLine implements AssemblyLine {
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
