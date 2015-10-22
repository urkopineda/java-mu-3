package thread;

import buffer.PoloNorte;

public class Duende extends Thread {
	
	int id;
	PoloNorte pn;
		
	public Duende(int id, PoloNorte pn) {
		this.id = id;
		this.pn = pn;
	}

	@Override
	public void run() {
		try {
			pn.llegar(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} System.out.println("Duda de duende Nº"+id+" resuelta!");
	}
	
	public void kill() {
		this.interrupt();
	}
	
	public int getThreadId() {
		return id;
	}
	
}
