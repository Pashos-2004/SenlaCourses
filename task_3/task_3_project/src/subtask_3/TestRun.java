package subtask_3;

public class TestRun {

	public static void main(String args[]) {
		System.out.println("=== ТЕСТИРОВАНИЕ СБОРОЧНОЙ ЛИНИИ АВТОМОБИЛЕЙ ===");
        
        LineStep bodyStep = new BodyStep();
        LineStep chassisStep = new ChassisStep();
        LineStep engineStep = new EngineStep();
        
        AssemblyLine carAssemblyLine = new CarAssemblyLine(bodyStep, chassisStep, engineStep);
        Product car = new Car();
        Product assembledCar = carAssemblyLine.assembleProduct(car);
        assembledCar.displayInfo();
        
        System.out.println("=== ТЕСТИРОВАНИЕ ЗАВЕРШЕНО ===");
	}
}
