package main;

import java.util.Scanner;

import thread.Cronometro;

public class Principal {
	
	Cronometro crono;
	Scanner teclado;
	
	public Principal() {
		crono = new Cronometro();
		teclado = new Scanner(System.in);
	}
	
	private void start() {
		teclado.nextLine();
		crono.start();
		teclado.nextLine();
		crono.end();
	}

	public static void main(String [] args) {
		Principal principal = new Principal();
		principal.start();
	}
	
}
