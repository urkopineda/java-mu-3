package thread;

import buffer.Cesta;

public class Ensamblador extends Thread {
	
	boolean endThread;
	Cesta cestaCuerpo;
	Cesta cestaManga;
	
	int id = 2;
	
	public Ensamblador(Cesta cestaManga, Cesta cestaCuerpo) {
		endThread = false;
		this.cestaCuerpo = cestaCuerpo;
		this.cestaManga = cestaManga;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				cestaManga.getObject(id, 2);
				cestaCuerpo.getObject(id, 1);
				System.out.println("Hilo ENSAMBLADOR Nº"+id+" crea un objeto.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		endThread = true;
	}

}
