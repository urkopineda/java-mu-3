package thread;

import java.util.Random;

import buffer.Jugador;

public class JugadorNegativo extends Thread {
	
	int id;
	Jugador jugador;
	Random rand;
	
	public JugadorNegativo(int id, Jugador jugador) {
		this.id = id;
		this.jugador = jugador;
		rand = new Random();
	}
	
	@Override
	public void run() {
		while (jugador.getNumVidas() != 0 && jugador.getNumVidas() != 10) {
			if (rand.nextInt(10) == 6) {
				jugador.quitarVida();
			}
		}
	}
	
}