package thread;

import buffer.Barberia;

public class Cliente extends Thread {
	
	int id;	
	Barberia bar;
	boolean endThread;
	
	public Cliente(int id, Barberia bar) {
		this.id = id;
		this.bar = bar;
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				bar.entrar(this);
				bar.salir(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

