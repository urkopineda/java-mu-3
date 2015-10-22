package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneradorNumeros extends Thread {
	int id;
	
	Random rand;
	static int numAGenerar = 20;	
	static public ArrayList<Integer> listaNumeros;
	static AtomicInteger numerosGenerados = new AtomicInteger(0);

	public GeneradorNumeros(int id) {
		this.id = id;
		rand = new Random();
		listaNumeros = new ArrayList<>();
	}
	
	public void run() {
		while (numerosGenerados.get() < numAGenerar) {
			synchronized (this) {
				System.out.println("Hilo Nº"+id+" añade valor...");
				listaNumeros.add(rand.nextInt(1000));
				numerosGenerados.addAndGet(1);
			}
		}
	}
	
	public void printList() {
		for (int i = 0; i < listaNumeros.size(); i++) System.out.println(listaNumeros.get(i));
	}
	
}
