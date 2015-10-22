package thread;

import buffer.Tienda;

public class Tendero extends Thread {
	
	int id;
	Tienda tienda;
	
	boolean hasChanged;
	
	public Tendero(int id, Tienda tienda) {
		this.id = id;
		this.tienda = tienda;
		hasChanged = false;
	}
	
	@Override
	public void run() {
		while (!hasChanged) {
			tienda.atender();
		}
	}
	
	public void kill() {
		hasChanged = true;
		this.interrupt();
	}

}
