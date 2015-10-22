package thread;

import buffer.Puente;

public class Coche extends Thread {
	
	Puente puente;
	int id;
	int sentido;
	
	public Coche(int id, int sentido, Puente puente) {
		this.id = id;
		this.sentido = sentido;
		this.puente = puente;
	}
	
	@Override
	public void run() {
		try {
			puente.llegarPuente(id, sentido);
			puente.cruzarPuente(id, sentido);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		puente.salirPuente(id, sentido);
	}
	
	public void kill() {
		this.interrupt();
	}

}
