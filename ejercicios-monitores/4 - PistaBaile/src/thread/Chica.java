package thread;

import buffer.PistaBaile;

public class Chica extends Thread {
	
	int id;
	PistaBaile pista;
		
	public Chica(int id, PistaBaile pista) {
		this.id = id;
		this.pista = pista;
	}

	@Override
	public void run() {
		try {
			pista.bailar(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void kill() {
		this.interrupt();
	}
	
	public int getThreadId() {
		return id;
	}
	
}
