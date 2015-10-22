package main;

import buffer.GarageITV;
import thread.Coche;
import thread.Revisor;

public class Principal {
	
	Coche [] c;
	Revisor [] r;
	GarageITV itv;
	
	static int MAX_PARKING = 1;
	static int MAX_C = 3;
	static int MAX_R = 3;
	
	public Principal() {
		itv = new GarageITV(MAX_PARKING, MAX_R);
	}
	
	private void generarHilos() {
		c = new Coche[MAX_C];
		r = new Revisor[MAX_R];
		for (int i = 0; i < MAX_C; i++) c[i] = new Coche(i, itv);
		for (int i = 0; i < MAX_R; i++) r[i] = new Revisor(i, itv);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_C; i++) c[i].start();
		for (int i = 0; i < MAX_R; i++) r[i].start();
	}
	
	private void syncHilos() throws InterruptedException {
		for (int i = 0; i < MAX_C; i++) c[i].join();
		for (int i = 0; i < MAX_R; i++) r[i].join();
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
