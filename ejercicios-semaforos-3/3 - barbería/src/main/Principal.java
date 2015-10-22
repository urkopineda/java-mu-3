package main;

import java.util.Scanner;

import buffer.Barberia;
import thread.Barbero;
import thread.Cliente;

public class Principal {

	Scanner teclado;
	
	Barberia bar;
	Cliente [] cli;
	Barbero barbero;
	
	static int MAX_BAÑO = 3;
	static int MAX_HOMS = 10;
	
	public Principal() {
		teclado = new Scanner(System.in);
		bar = new Barberia(MAX_BAÑO, barbero);
	}
	
	private void crearHilos() {
		cli = new Cliente[MAX_HOMS];
		barbero = new Barbero(0, bar);
		for (int i = 0; i < MAX_HOMS; i++) cli[i] = new Cliente(i, bar);
	}
	
	private void iniciarHilos() {
		barbero.start();
		for (int i = 0; i < MAX_HOMS; i++) cli[i].start();
	}
	
	private void matarHilos() {
		barbero.kill();
		for (int i = 0; i < MAX_HOMS; i++) cli[i].kill();
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
