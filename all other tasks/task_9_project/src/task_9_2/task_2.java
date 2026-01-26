package task_9_2;

public class task_2 {
	
	private static final Object lock = new Object();
	private static boolean firstTurn = true;
	
	public static void main(String[] args) throws Exception {
       
        Thread t1 = new Thread(() -> printName(true), "Thread-1");
        Thread t2 = new Thread(() -> printName(false), "Thread-2");

        t1.start();
        t2.start();
    }

    private static void printName(boolean isFirst) {
        while(true) {
            synchronized (lock) {
                while (firstTurn != isFirst) {
                    try {
                    	lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println(Thread.currentThread().getName());
                firstTurn = !firstTurn;
                lock.notifyAll();
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        }
    }
}
