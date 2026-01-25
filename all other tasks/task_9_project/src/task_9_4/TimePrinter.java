package task_9_4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePrinter extends Thread{
	
	private final int intervalSeconds;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TimePrinter(int intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
        setDaemon(true);
    }
    
    @Override
    public void run() {
        while (true) {
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Текущее время: " + now.format(formatter));
            try {
                Thread.sleep(intervalSeconds * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
	
}
