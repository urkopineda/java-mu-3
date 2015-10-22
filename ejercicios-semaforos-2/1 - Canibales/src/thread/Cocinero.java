package thread;

import buffer.Olla;
import data.Misionero;

public class Cocinero extends Thread {
	
	boolean hasChanged;
	Olla olla;
	
	public Cocinero(Olla olla) {
		hasChanged = false;
		this.olla = olla;
	}
	
	@Override
	public void run() {
		while (!hasChanged) {
			try {
				olla.addMisionero(new Misionero());
			} catch (InterruptedException e) {}
		}
	}
	
	public void kill() {
		hasChanged = true;
		this.interrupt();
	}

}
