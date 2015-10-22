package main;

import java.util.concurrent.CyclicBarrier;

public class Principal {

	static int numContadores = 3;
	static int maxContador = 10;
	
	CyclicBarrier bar;
	Contador contadores[];
	
	public Principal() {
		
	}
	
	private void crearHilos() {
		bar = new CyclicBarrier(numContadores);
		contadores = new Contador[numContadores];
		for (int i = 0; i < numContadores; i++) contadores[i] = new Contador(i, maxContador, bar);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < numContadores; i++) contadores[i].start();
	}	
	
	private void iniciar() {
		crearHilos();
		iniciarHilos();
	}
	
	public static void main(String args[]) {
		Principal newPrincipal = new Principal();
		newPrincipal.iniciar();
	}
	
}
