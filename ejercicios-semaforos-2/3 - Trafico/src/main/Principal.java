package main;

import java.util.Scanner;

import buffer.Puente;
import thread.Coche;

public class Principal {

	Scanner teclado;
	
	Puente puente;
	
	Coche [] coches;
	
	static int CAPACIDAD_PUENTE = 3;
	static int CANTIDAD_COCHES = 10; // Por cada sentido.
	
	public Principal() {
		puente = new Puente(CAPACIDAD_PUENTE);
		teclado = new Scanner(System.in);
	}
	
	private void generarHilos() {
		coches = new Coche[CANTIDAD_COCHES * 2];
		for (int i = 0; i < CANTIDAD_COCHES; i++) coches[i] = new Coche(i, 0, puente);
		for (int i = 0; i < CANTIDAD_COCHES; i++) coches[CANTIDAD_COCHES + i] = new Coche((CANTIDAD_COCHES + i), 1, puente);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < (CANTIDAD_COCHES * 2); i++) coches[i].start();
	}
	
	private void matarHilos() {
		for (int i = 0; i < (CANTIDAD_COCHES * 2); i++) coches[i].kill();
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		teclado.nextLine();
		matarHilos();
	}
	
	public static void main(String [] args) {
		Principal prin = new Principal();
		prin.start();
	}
	
}
