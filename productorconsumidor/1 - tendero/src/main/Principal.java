package main;

import java.util.Scanner;

import buffer.Tienda;
import thread.Cliente;
import thread.Tendero;

public class Principal {
	
	Scanner teclado;
	
	Tendero [] cons;
	Cliente [] pros;
	
	Tienda tienda;
	
	static int MAX_CONS = 1;
	static int MAX_PROS = 10;
	static int MAX_Tienda = 5;
	
	public Principal() {
		tienda = new Tienda(MAX_Tienda);
		teclado = new Scanner(System.in);
	}
	
	private void crearHilos() {
		cons = new Tendero[MAX_CONS];
		pros = new Cliente[MAX_PROS];
		for (int i = 0; i < MAX_CONS; i++) cons[i] = new Tendero(i, tienda);
		for (int i = 0; i < MAX_PROS; i++) pros[i] = new Cliente(i, tienda);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_CONS; i++) cons[i].start();
		for (int i = 0; i < MAX_PROS; i++) pros[i].start();
	}
	
	private void matarHilos() {
		for (int i = 0; i < MAX_CONS; i++) cons[i].kill();
		for (int i = 0; i < MAX_PROS; i++) pros[i].kill();
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
