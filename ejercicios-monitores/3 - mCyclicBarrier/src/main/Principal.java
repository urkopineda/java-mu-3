package main;

import thread.MiHilo;

public class Principal {
	
	MiHilo [] hilos;
	
	public static int MAX = 3;
	
	public Principal() {

	}
	
	private void crearHilos() {
		hilos = new MiHilo[MAX];
		for (int i = 0; i < MAX; i++) hilos[i] = new MiHilo(i);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX; i++) hilos[i].start();
	}
	
	private void syncHilos() {
		try {
			for (int i = 0; i < MAX; i++) hilos[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		crearHilos();
		iniciarHilos();
		syncHilos();
	}

	public static void main(String [] args) {
		Principal exe = new Principal();
		exe.start();
	}
	
}
