package thread;

import buffer.Cesta;
import data.Cuerpo;

public class CreadorCuerpo extends Thread {
	
	Cesta cestaCuerpo;
	boolean endThread;
	
	int id = 1;
	
	public CreadorCuerpo(Cesta cestaCuerpo) {
		this.cestaCuerpo = cestaCuerpo;
		endThread = false;		
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				cestaCuerpo.addObject(id, new Cuerpo());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public void kill() {
		endThread = true;
	}
	
}
