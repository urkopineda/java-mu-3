package semContador3;

import java.util.concurrent.Semaphore;

public class Contador extends Thread {
	
	static int ultimo;
	
	int idContador;
	int maxContador;
	int maxHilos;
	Semaphore barreraContador;
	Semaphore barreraHilos;
		
	public Contador(int idContador, int maxContador, Semaphore barreraContador, Semaphore barreraHilos, int maxHilos) {
		this.idContador = idContador;
		this.maxContador = maxContador;
		this.barreraContador = barreraContador;
		this.barreraHilos = barreraHilos;
		this.maxHilos = maxHilos;
		ultimo = 0;
	}
	
	public void run() {
		for (int i = 0; i < maxContador; i++) {
			System.out.println("Hilo Nº"+idContador+": "+(i + 1)+"...");
			ultimo++;
			if (ultimo == maxHilos) {
				System.out.println("------------------------------------------------");
				barreraContador.release(maxHilos);
			}
			try {
				barreraContador.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ultimo--;
			if (ultimo == 0) {
				barreraHilos.release(maxHilos);
			}
			try {
				barreraHilos.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
