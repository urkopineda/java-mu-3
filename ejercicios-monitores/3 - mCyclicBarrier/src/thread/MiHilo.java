package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import main.Principal;

public class MiHilo extends Thread {
	
	int id;
	static int count = 0;
	
	static Lock mutex = new ReentrantLock();
	static Condition cola = mutex.newCondition();
	
	public MiHilo(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hilo Nº"+id+" escribe "+(i + 1)+"...");
			mutex.lock();
			count++;
			if (count == Principal.MAX) {
				cola.signalAll();
				count = 0;
			} else {
				try {
					cola.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			mutex.unlock();
		}
		System.out.println("Hilo Nº"+id+" dice agur!");
	}
	
	public void kill() {
		this.interrupt();
	}
	
}
