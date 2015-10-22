package main;

import java.util.Scanner;

import buffer.Discoteca;
import thread.Hombre;
import thread.Mujer;

public class Principal {

	Scanner teclado;
	
	Discoteca disco;
	Hombre [] homs;
	Mujer [] mujs;
	
	static int MAX_BAÑO = 3;
	static int MAX_HOMS = 10;
	static int MAX_MUJS = 10;
	static int P = 5;
	
	public Principal() {
		teclado = new Scanner(System.in);
		disco = new Discoteca(MAX_BAÑO, P);
	}
	
	private void crearHilos() {
		homs = new Hombre[MAX_HOMS];
		mujs = new Mujer[MAX_MUJS];
		for (int i = 0; i < MAX_HOMS; i++) homs[i] = new Hombre(i, disco);
		for (int i = 0; i < MAX_MUJS; i++) mujs[i] = new Mujer(i, disco);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_HOMS; i++) homs[i].start();
		for (int i = 0; i < MAX_MUJS; i++) mujs[i].start();
	}
	
	private void matarHilos() {
		for (int i = 0; i < MAX_HOMS; i++) homs[i].kill();
		for (int i = 0; i < MAX_MUJS; i++) mujs[i].kill();
	}
	
	private void start() {
		crearHilos();
		iniciarHilos();
		teclado.nextLine();
		matarHilos();
	}
	
	public static void main(String [] args) {
		Principal programa = new Principal();
		programa.start();
	}
	
}
