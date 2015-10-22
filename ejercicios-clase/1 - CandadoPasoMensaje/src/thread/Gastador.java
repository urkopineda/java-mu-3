package thread;

import buffer.aBuzon;

public class Gastador extends Thread {
	
	int id;
	aBuzon<Integer> buzon;
	
	boolean endThread;
	
	public Gastador(int id, aBuzon<Integer> buzon) {
		this.id = id;
		this.buzon = buzon;
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				System.out.println("Gastador recive "+buzon.receive());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		endThread = true;
		this.interrupt();
	}

}
