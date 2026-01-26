package task_9_1;

public class task_1 {

    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(() -> {
            System.out.println("Внутри run(), состояние: " + Thread.currentThread().getState());
            blockedDemo();      
            waitingDemo();     
            timedWaitingDemo(); 
        }, "Worker");

        System.out.println("После создания: " + worker.getState());

        worker.start();
        Thread.sleep(100); 
       

        worker.join();
        System.out.println("После завершения: " + worker.getState());
    }

    private static void blockedDemo() {
        Thread locker = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        });

        locker.start();

        try {
            Thread.sleep(50); 
        } catch (InterruptedException ignored) {}

        Thread blockedThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("blockedThread вошёл в synchronized");
            }
        }, "Blocked-Thread");

        blockedThread.start();

        try {
            Thread.sleep(50); 
        } catch (InterruptedException ignored) {}

        System.out.println("Состояние blockedThread (ожидаемо BLOCKED): " + blockedThread.getState());

        try {
            locker.join();
            blockedThread.join();
        } catch (InterruptedException ignored) {}
    }


    private static void waitingDemo() {
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                	lock.wait();
                } catch (InterruptedException ignored) {}
            }
        }, "Waiting-Thread");

        waitingThread.start();

        try {
            Thread.sleep(50); 
        } catch (InterruptedException ignored) {}

        System.out.println("Состояние waitingThread (ожидаемо WAITING): " + waitingThread.getState());

        synchronized (lock) {
        	lock.notify();
        }

        try {
            waitingThread.join();
        } catch (InterruptedException ignored) {}
    }

    private static void timedWaitingDemo() {
        Thread timedThread = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {}
        }, "Timed-Thread");

        timedThread.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException ignored) {}

        System.out.println("Состояние timedThread (ожидаемо TIMED_WAITING): " + timedThread.getState());

        try {
            timedThread.join();
        } catch (InterruptedException ignored) {}
    }
}
