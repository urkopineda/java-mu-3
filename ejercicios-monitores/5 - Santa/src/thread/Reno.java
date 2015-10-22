package thread;

import buffer.PoloNorte;

public class Reno extends Thread {
	
	int id;
	PoloNorte pn;
		
	public Reno(int id, PoloNorte pn) {
		this.id = id;
		this.pn = pn;
	}

	@Override
	public void run() {
		try {
			pn.llegar(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} System.out.println("Reno Nº"+id+" se va en trineo con Santa! :D");
	}
	
	public void kill() {
		this.interrupt();
	}
	
	public int getThreadId() {
		return id;
	}
	
}
