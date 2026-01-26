package task_9_3;

import java.util.Random;

public class Consumer implements Runnable{
	private final MyBuffer buffer;
    private final Random random = new Random();

    public Consumer(MyBuffer buffer) {
        this.buffer = buffer;
    }
    
	@Override
	public void run() {
		
		try {
			while (true) {
				int value = buffer.take();
				System.out.println("Потребили из буффера : " + value);
				Thread.sleep(50+random.nextInt(450));
			}
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            
        }
		
	}
}