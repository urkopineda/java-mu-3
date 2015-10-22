package thread;

import buffer.Discoteca;

public class Hombre extends Thread {
	
	int id;	
	Discoteca disco;
	boolean endThread;
	
	public Hombre(int id, Discoteca disco) {
		this.id = id;
		this.disco = disco;
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				disco.entrar(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			disco.salir(this);
		}
	}
	
	public void kill() {
		endThread = true;
		this.interrupt();
	}
	
	public long getId() {
		return id;
	}

}
