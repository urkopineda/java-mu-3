package buffer;

import java.util.concurrent.atomic.AtomicInteger;

public class Jugador extends Thread {
	
	int id;
	AtomicInteger numVidas;
	
	public Jugador(int id) {
		this.id = id;
		numVidas = new AtomicInteger(5);
	}
	
	public int getNumVidas() {
		return numVidas.get();
	}
	
	public void quitarVida() {
		numVidas.decrementAndGet();
	}
	
	public void darVida() {
		numVidas.incrementAndGet();
	}
	
}
