package semContador3;

import java.util.concurrent.Semaphore;

public class Principal {

	static int numContadores = 100000000;
	static int maxContador = 100;
	
	Contador contadores[];
	Semaphore barreraContador;
	Semaphore barreraHilos;
	
	public Principal() {
		
	}
	
	private void crearHilos() {
		barreraContador = new Semaphore(0);
		barreraHilos = new Semaphore(0);
		contadores = new Contador[numContadores];
		for (int i = 0; i < numContadores; i++) contadores[i] = new Contador(i, maxContador, barreraContador, barreraHilos, numContadores);
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
