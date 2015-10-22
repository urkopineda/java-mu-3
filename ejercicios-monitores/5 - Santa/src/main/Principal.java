package main;

import buffer.PoloNorte;
import thread.Duende;
import thread.Reno;
import thread.Santa;

public class Principal {
	
	PoloNorte pn;
	Reno [] renos;
	Duende [] duendes;
	Santa santa;
	
	static int MAX_DUENDES = 20;
	static int MAX_DUENDES_DESP = 3;
	static int MAX_RENOS = 9;
	
	public Principal() {
		pn = new PoloNorte(MAX_DUENDES_DESP, MAX_RENOS);
	}
	
	private void crearHilos() {
		renos = new Reno[MAX_RENOS];
		duendes = new Duende[MAX_DUENDES];
		for (int i = 0; i < MAX_RENOS; i++) renos[i] = new Reno(i, pn);
		for (int i = 0; i < MAX_DUENDES; i++) duendes[i] = new Duende(i, pn);
		santa = new Santa(0, pn);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_RENOS; i++) renos[i].start();
		for (int i = 0; i < MAX_DUENDES; i++) duendes[i].start();
		santa.start();
	}
	
	private void syncHilos() {
		try {
			for (int i = 0; i < MAX_RENOS; i++) renos[i].join();
			for (int i = 0; i < MAX_DUENDES; i++) duendes[i].join();
			santa.join();
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
