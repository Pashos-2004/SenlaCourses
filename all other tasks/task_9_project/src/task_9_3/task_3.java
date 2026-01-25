package task_9_3;

public class task_3 {
	public static void main(String[] args) throws Exception {
	    
		MyBuffer buffer = new MyBuffer(5);

        Thread producer = new Thread(new Producer(buffer), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");

        producer.start();
        consumer.start();
        
    }
}
