package task_9_3;

import java.util.LinkedList;
import java.util.Queue;

public class MyBuffer {
	private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 1;
    
    public MyBuffer(int capacity) {
		this.capacity = capacity;
	}
    
    public synchronized int take() {
    	
    	while (queue.isEmpty()) {
            System.out.println("Буфер пуст ...");
            try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    	
        int value = queue.poll();
        notifyAll();
        
        return value;
    } 
    
    public synchronized void put(int value) {
    	
    	while(queue.size()==capacity) {
    		System.out.println("Буфер полон ...");
            try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
    	}
    	
    	queue.add(value);
        notifyAll();

    }
}