package main;

import buffer.PistaBaile;
import thread.Chica;
import thread.Chico;

public class Principal {
	
	PistaBaile pista;
	Chico [] chicos;
	Chica [] chicas;
	
	static int MAX_PISTA = 2;
	static int MAX_COS = 3;
	static int MAX_CAS = 3;
	
	public Principal() {
		pista = new PistaBaile(MAX_PISTA);
	}
	
	private void crearHilos() {
		chicos = new Chico[MAX_COS];
		chicas = new Chica[MAX_CAS];
		for (int i = 0; i < MAX_COS; i++) chicos[i] = new Chico(i, pista);
		for (int i = 0; i < MAX_CAS; i++) chicas[i] = new Chica(i, pista);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_COS; i++) chicos[i].start();
		for (int i = 0; i < MAX_CAS; i++) chicas[i].start();
	}
	
	private void syncHilos() {
		try {
			for (int i = 0; i < MAX_COS; i++) chicos[i].join();
			for (int i = 0; i < MAX_CAS; i++) chicas[i].join();
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
