package thread;

import java.util.concurrent.Semaphore;

public class ABC extends Thread {
	
	int id;
	
	static Semaphore semA = new Semaphore(1);
	static Semaphore semB = new Semaphore(0);
	static Semaphore semC = new Semaphore(0);
	
	public ABC(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		for (int i = 0; i != 6; i++) {
			if (id == 0) {
				try {
					semA.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print("A");
				semB.release();
			}
			if (id == 1) {
				try {
					semB.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print("B");
				semC.release();
			}
			if (id == 2) {
				try {
					semC.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print("C");
				semA.release();
			}
		}
	}

}
