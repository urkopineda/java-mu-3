package main;

import java.util.Scanner;

import buffer.Olla;
import thread.Canibal;
import thread.Cocinero;

public class Principal {

	Scanner teclado;
	
	Olla olla;
	
	Cocinero cocinero;
	Canibal [] canibales;
	
	static int CAPACIDAD_M = 4;
	static int CANTIDAD_CANIBALES = 10;
	
	public Principal() {
		olla = new Olla(CAPACIDAD_M);
		teclado = new Scanner(System.in);
	}
	
	private void generarHilos() {
		cocinero = new Cocinero(olla);
		canibales = new Canibal[CANTIDAD_CANIBALES];
		for (int i = 0; i < CANTIDAD_CANIBALES; i++) canibales[i] = new Canibal(olla);
	}
	
	private void iniciarHilos() {
		cocinero.start();
		for (int i = 0; i < CANTIDAD_CANIBALES; i++) canibales[i].start();
	}
	
	private void matarHilos() {
		cocinero.kill();
		for (int i = 0; i < CANTIDAD_CANIBALES; i++) canibales[i].kill();
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
