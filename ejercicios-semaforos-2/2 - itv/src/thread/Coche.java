package thread;

import buffer.Centro;

public class Coche extends Thread {
	
	int id;
	boolean hasChanged;
	Centro centro;
	
	public Coche(int id, Centro centro) {
		this.id = id;
		hasChanged = false;
		this.centro = centro;
	}
	
	@Override
	public void run() {
		try {
			centro.entrarAlParking(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void kill() {
		this.interrupt();
	}
}
