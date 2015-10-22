package main;

import java.util.Scanner;

import buffer.Buffer;
import thread.Consumidor;
import thread.Productor;

public class Principal {
	
	Scanner teclado;
	
	Consumidor [] cons;
	Productor [] pros;
	
	Buffer buffer;
	
	static int MAX_CONS = 10;
	static int MAX_PROS = 10;
	static int MAX_BUFFER = 100;
	
	public Principal() {
		buffer = new Buffer(MAX_BUFFER);
		teclado = new Scanner(System.in);
	}
	
	private void crearHilos() {
		cons = new Consumidor[MAX_CONS];
		pros = new Productor[MAX_PROS];
		for (int i = 0; i < MAX_CONS; i++) cons[i] = new Consumidor(i, buffer);
		for (int i = 0; i < MAX_PROS; i++) pros[i] = new Productor(i, buffer);
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
