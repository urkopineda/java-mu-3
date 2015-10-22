package main;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Contador extends Thread {
	
	int idContador;
	int maxContador;
	CyclicBarrier bar;
	Principal prin;
	
	public Contador(int idContador, int maxContador, CyclicBarrier bar) {
		this.idContador = idContador;
		this.maxContador = maxContador;
		this.bar = bar;
	}
	
	public void run() {
		for (int i = 0; i < maxContador; i++) {
			System.out.println("Hilo Nº"+idContador+": "+(i + 1)+"...");
		}
		try {
			bar.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("Adiós");
	}

}
