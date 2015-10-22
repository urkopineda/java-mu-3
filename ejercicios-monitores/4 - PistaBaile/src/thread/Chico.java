package thread;

import buffer.PistaBaile;

public class Chico extends Thread {
	
	int id;
	PistaBaile pista;
		
	public Chico(int id, PistaBaile pista) {
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
