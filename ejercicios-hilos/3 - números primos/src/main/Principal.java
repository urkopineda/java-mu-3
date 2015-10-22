package main;

import java.util.ArrayList;

import thread.Calculador;

public class Principal {
	
	Calculador calculadores[];
	
	ArrayList<Integer> numeros;
	
	public static int MAX_NUM = 1000000000;
	public static int MAX_HILOS = 100;
	
	public Principal() {
		
	}
	
	private void unirNumeros() {
		numeros = new ArrayList<>();
		for (int i = 0; i < MAX_HILOS; i++) {
			numeros.addAll(calculadores[i].getNumerosHilos());
		}
	}
	
	private void mostrarNumeros() {
		for (int i = 0; i < numeros.size(); i++) System.out.println(numeros.get(i));
	}
	
	private void generarHilos() {
		calculadores = new Calculador[MAX_HILOS];
		for (int i = 0; i < MAX_HILOS; i++) calculadores[i] = new Calculador(i);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_HILOS; i++) calculadores[i].start();
	}
	
	private void syncHilos() {
		for (int i = 0; i < MAX_HILOS; i++) {
			try {
				calculadores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		syncHilos();
		unirNumeros();
		mostrarNumeros();
	}

	public static void main(String [] args) {
		Principal principal = new Principal();
		principal.start();
	}
	
}
