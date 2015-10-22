package thread;

import buffer.Baño;

public class Mujer extends Thread {
	
	int id;	
	Baño baño;
	
	public Mujer(int id, Baño baño) {
		this.id = id;
		this.baño = baño;
	}
	
	@Override
	public void run() {
		try {
			baño.entrar(this);
			baño.salir(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void kill() {
		this.interrupt();
	}
	
	public int getThreadId() {
		return id;
	}

}
