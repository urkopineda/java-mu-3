package main;

import buffer.Jugador;
import thread.JugadorNegativo;
import thread.JugadorPositivo;

public class Principal {
	
	Jugador jugador;
	JugadorPositivo positivo;
	JugadorNegativo negativo;
	
	public Principal() {
		jugador = new Jugador(0);
	}
	
	private void generarHilos() {
		positivo = new JugadorPositivo(0, jugador);
		negativo = new JugadorNegativo(0, jugador);
	}
	
	private void iniciarHilos() {
		positivo.start();
		negativo.start();
	}
	
	private void syncHilos() {
		try {
			positivo.join();
			negativo.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		syncHilos();
		if (jugador.getNumVidas() == 0) System.out.println("El jugador a muerto");
		else if (jugador.getNumVidas() >= 10) System.out.println("El jugador a sobrevivido");
	}

	public static void main(String [] args) {
		Principal principal = new Principal();
		principal.start();
	}
	
}
