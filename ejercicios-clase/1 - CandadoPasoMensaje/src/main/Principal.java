package main;

import buffer.aBuzon;
import thread.Ahorrador;
import thread.Gastador;

public class Principal {
	
	Ahorrador [] a;
	Gastador [] g;
	aBuzon<Integer> buzon;
	
	static int MAX_BUZON = 1;
	static int MAX_A = 3;
	static int MAX_G = 3;
	
	public Principal() {
		buzon = new aBuzon<>(MAX_BUZON);
	}
	
	private void generarHilos() {
		a = new Ahorrador[MAX_A];
		g = new Gastador[MAX_G];
		for (int i = 0; i < MAX_A; i++) a[i] = new Ahorrador(i, buzon);
		for (int i = 0; i < MAX_G; i++) g[i] = new Gastador(i, buzon);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_A; i++) a[i].start();
		for (int i = 0; i < MAX_G; i++) g[i].start();
	}
	
	private void syncHilos() throws InterruptedException {
		for (int i = 0; i < MAX_A; i++) a[i].join();
		for (int i = 0; i < MAX_G; i++) g[i].join();
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		try {
			syncHilos();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		Principal exe = new Principal();
		exe.start();
	}

}
