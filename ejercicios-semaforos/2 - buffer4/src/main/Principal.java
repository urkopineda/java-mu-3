package main;

import java.util.Scanner;

import buffer.Buffer;
import thread.Sumador;

public class Principal {
	
	Scanner teclado;
	
	Sumador [] sumadores;
	Buffer buffer;
	
	static int MAX_HILOS = 9;
	
	public Principal() {
		buffer = new Buffer();
		teclado = new Scanner(System.in);
	}
	
	private void generarHilos() {
		sumadores = new Sumador[MAX_HILOS];
		for (int i = 0; i < MAX_HILOS; i++) sumadores[i] = new Sumador(i, buffer);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_HILOS; i++) sumadores[i].start();
	}
	
	private void matarHilos() {
		for (int i = 0; i < MAX_HILOS; i++) sumadores[i].end();
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		teclado.nextLine();
		matarHilos();
	}
	
	public static void main(String [] args) {
		Principal programa = new Principal();
		programa.start();
	}
	
}
