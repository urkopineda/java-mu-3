package thread;

import buffer.Ba�o;

public class Hombre extends Thread {
	
	int id;	
	Ba�o ba�o;
	
	public Hombre(int id, Ba�o ba�o) {
		this.id = id;
		this.ba�o = ba�o;
	}
	
	@Override
	public void run() {
		try {
			ba�o.entrar(this);
			ba�o.salir(this);
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
