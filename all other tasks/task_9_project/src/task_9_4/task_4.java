package task_9_4;

public class task_4 {

	public static void main(String[] args) {
		
		TimePrinter timeThread = new TimePrinter(5);
        timeThread.start();
        
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
        
        System.out.println("Основная программа завершена");
	}

}
