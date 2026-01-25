package task_9_3;

import java.util.Random;

public class Producer implements Runnable{
	private final MyBuffer buffer;
    private final Random random = new Random();

    public Producer(MyBuffer buffer) {
        this.buffer = buffer;
    }
    
	@Override
	public void run() {
		
		try {
            while (true) {
                int value = random.nextInt(100);
                System.out.println("Добавляем в буффер : " + value);
                buffer.put(value);
                Thread.sleep(250+random.nextInt(50));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
		
	}
}
