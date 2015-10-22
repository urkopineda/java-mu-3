package main;

import buffer.Buffer;
import thread.Consumidor;
import thread.Productor;

public class Principal {
	
	Buffer buffer;
	Consumidor [] consumidores;
	Productor [] productores;
	
	static int MAX_BUFFER = 5;
	static int MAX_CON = 3;
	static int MAX_PRO = 1;
	
	public Principal() {
		buffer = new Buffer(MAX_BUFFER);
	}
	
	private void crearHilos() {
		consumidores = new Consumidor[MAX_CON];
		productores = new Productor[MAX_PRO];
		for (int i = 0; i < MAX_CON; i++) consumidores[i] = new Consumidor(i, buffer);
		for (int i = 0; i < MAX_PRO; i++) productores[i] = new Productor(i, buffer);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_CON; i++) consumidores[i].start();
		for (int i = 0; i < MAX_PRO; i++) productores[i].start();
	}
	
	private void syncHilos() {
		try {
			for (int i = 0; i < MAX_CON; i++) consumidores[i].join();
			for (int i = 0; i < MAX_PRO; i++) productores[i].join();
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
