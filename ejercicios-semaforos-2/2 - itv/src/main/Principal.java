package main;

import java.util.Scanner;

import buffer.Centro;
import thread.Coche;
import thread.Operario;

public class Principal {

	Operario [] operarios;
	Coche [] coches;
	Centro centro;
	
	Scanner teclado;
	
	static int CAPACIDAD_CENTRO = 3;
	static int CANTIDAD_OPERARIOS = 3;
	static int MAX_COCHES = 10;
	
	public Principal() {
		centro = new Centro(CAPACIDAD_CENTRO, CANTIDAD_OPERARIOS); // Teniendo en cuenta que por cada operario hay una cochera.
		teclado = new Scanner(System.in);
	}
	
	private void generarHilos() {
		coches = new Coche[MAX_COCHES];
		operarios = new Operario[CANTIDAD_OPERARIOS];
		for (int i = 0; i < MAX_COCHES; i++) coches[i] = new Coche((i + 1), centro);
		for (int i = 0; i < CANTIDAD_OPERARIOS; i++) operarios[i] = new Operario((i + 1), centro);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_COCHES; i++) coches[i].start();
		for (int i = 0; i < CANTIDAD_OPERARIOS; i++) operarios[i].start();
	}
	
	private void syncHilos() {
		for (int i = 0; i < MAX_COCHES; i++) {
			try {
				coches[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < CANTIDAD_OPERARIOS; i++) {
			try {
				operarios[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private void matarHilos() {
		for (int i = 0; i < MAX_COCHES; i++) coches[i].kill();
		for (int i = 0; i < CANTIDAD_OPERARIOS; i++) operarios[i].kill();
	}
	
	private void start() {
		generarHilos();
		iniciarHilos();
		syncHilos();
		teclado.nextLine();
		matarHilos();
	}
	
	public static void main(String [] args) {
		Principal prin = new Principal();
		prin.start();
	}
	
}
