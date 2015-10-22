package main;

import buffer.Ba�o;
import thread.Hombre;
import thread.Mujer;

public class Principal {

	Ba�o ba�o;
	Hombre [] homs;
	Mujer [] mujs;
	
	static int MAX_BA�O = 3;
	static int MAX_HOMS = 10;
	static int MAX_MUJS = 10;
	
	public Principal() {
		ba�o = new Ba�o(MAX_BA�O);
	}
	
	private void crearHilos() {
		homs = new Hombre[MAX_HOMS];
		mujs = new Mujer[MAX_MUJS];
		for (int i = 0; i < MAX_HOMS; i++) homs[i] = new Hombre(i, ba�o);
		for (int i = 0; i < MAX_MUJS; i++) mujs[i] = new Mujer(i, ba�o);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_HOMS; i++) homs[i].start();
		for (int i = 0; i < MAX_MUJS; i++) mujs[i].start();
	}
	
	private void syncHilos() {
		try {
			for (int i = 0; i < MAX_HOMS; i++) homs[i].join();
			for (int i = 0; i < MAX_MUJS; i++) mujs[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void matarHilos() {
		for (int i = 0; i < MAX_HOMS; i++) homs[i].kill();
		for (int i = 0; i < MAX_MUJS; i++) mujs[i].kill();
	}
	
	private void start() {
		crearHilos();
		iniciarHilos();
		syncHilos();
		matarHilos();
	}
	
	public static void main(String [] args) {
		Principal programa = new Principal();
		programa.start();
	}
	
}
