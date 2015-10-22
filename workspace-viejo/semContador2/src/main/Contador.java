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
			try {
				System.out.println("Hilo Nº"+idContador+": "+(i + 1)+"...");
				bar.await();
				if (bar.getNumberWaiting() == 0) System.out.println("--------");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
