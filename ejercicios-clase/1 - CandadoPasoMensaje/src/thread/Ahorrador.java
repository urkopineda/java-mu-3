package thread;

import buffer.aBuzon;

public class Ahorrador extends Thread {
	
	int id;
	aBuzon<Integer> buzon;
	
	boolean endThread;
	
	public Ahorrador(int id, aBuzon<Integer> buzon) {
		this.id = id;
		this.buzon = buzon;
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				System.out.println("Ahorrador envía "+id);
				buzon.send(id);
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
