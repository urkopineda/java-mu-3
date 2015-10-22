package main;

import thread.ABC;

public class Principal {
	
	ABC hilos[];
	
	static int MAX_HILOS = 3;
	
	public Principal() {
		
	}
	
	private void generarHilos() {
		hilos = new ABC[MAX_HILOS];
		for (int i = 0; i != MAX_HILOS; i++) hilos[i] = new ABC(i);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i != MAX_HILOS; i++) hilos[i].start();
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
	}
	
	public static void main(String [] args) {
		Principal prin = new Principal();
		prin.start();
	}
	
}
