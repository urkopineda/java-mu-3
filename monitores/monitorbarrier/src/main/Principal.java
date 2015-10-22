package main;

import thread.HiloContador;

public class Principal {

	HiloContador [] hilos;
	public static int MAX_HILOS = 10;
	
	public Principal() {

	}
	
	private void generarHilos() {
		hilos = new HiloContador[MAX_HILOS];
		for (int i = 0; i < MAX_HILOS; i++) hilos[i] = new HiloContador(i);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_HILOS; i++) hilos[i].start();
	}
	
	private void syncHilos() {
		for (int i = 0; i < MAX_HILOS; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		syncHilos();
	}
	
	public static void main(String args[]) {
		Principal newPrincipal = new Principal();
		newPrincipal.start();
	}
	
}