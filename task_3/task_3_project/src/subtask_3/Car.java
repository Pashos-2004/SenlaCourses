package subtask_3;

public class Car implements Product {
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

