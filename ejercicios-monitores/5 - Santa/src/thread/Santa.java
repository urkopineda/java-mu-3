package thread;

import buffer.PoloNorte;

public class Santa extends Thread {
	
	int id;
	PoloNorte pn;
		
	boolean endThread;
	
	public Santa(int id, PoloNorte pn) {
		this.id = id;
		this.pn = pn;
		endThread = false;
	}

	@Override
	public void run() {
		while (!endThread) {
			pn.irADormir();
			try {
				pn.atender();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		endThread = true;
		this.interrupt();
	}
	
	public int getThreadId() {
		return id;
	}
	
}
