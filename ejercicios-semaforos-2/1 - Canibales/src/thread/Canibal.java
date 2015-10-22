package thread;

import buffer.Olla;

public class Canibal extends Thread {
	
	boolean hasChanged;
	Olla olla;
	
	public Canibal(Olla olla) {
		hasChanged = false;
		this.olla = olla;
	}
	
	@Override
	public void run() {
		while (!hasChanged) {
			try {
				olla.getMisionero();
			} catch (InterruptedException e) {}
		}
	}
	
	public void kill() {
		hasChanged = true;
		this.interrupt();
	}

}
