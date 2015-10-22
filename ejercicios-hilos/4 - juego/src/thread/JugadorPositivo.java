package thread;

import java.util.Random;

import buffer.Jugador;

public class JugadorPositivo extends Thread {
	
	int id;
	Jugador jugador;
	Random rand;
	
	static Object o;
	
	public JugadorPositivo(int id, Jugador jugador) {
		this.id = id;
		this.jugador = jugador;
		rand = new Random();
		o = new Object();
	}
	
	@Override
	public void run() {
		while (jugador.getNumVidas() != 0 && jugador.getNumVidas() != 10) {
			if (rand.nextInt(10) == 3) {
				jugador.darVida();
			}
		}
	}
	
}
