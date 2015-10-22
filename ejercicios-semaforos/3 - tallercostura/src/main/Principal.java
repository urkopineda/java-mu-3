package main;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import buffer.Cesta;
import thread.CreadorCuerpo;
import thread.CreadorManga;
import thread.Ensamblador;

public class Principal {
	
	Cesta cestaCuerpo;
	Cesta cestaManga;
	
	CreadorManga creadorM;
	CreadorCuerpo creadorC;
	Ensamblador ensamblador;
	
	Semaphore semaforoManga;
	Semaphore semaforoCuerpo;
	
	Scanner teclado;
	
	static int MAX_CUERPOS = 50;
	static int MAX_MANGAS = 100;
	
	public Principal() {
		semaforoManga = new Semaphore(1);
		semaforoCuerpo = new Semaphore(1);
		cestaCuerpo = new Cesta(MAX_CUERPOS);
		cestaManga = new Cesta(MAX_MANGAS);
		teclado = new Scanner(System.in);
	}
	
	private void crearHilos() {
		creadorM = new CreadorManga(cestaManga);
		creadorC = new CreadorCuerpo(cestaCuerpo);
		ensamblador = new Ensamblador(cestaManga, cestaCuerpo);
	}
	
	private void iniciarHilos() {
		creadorM.start();
		creadorC.start();
		ensamblador.start();
	}
	
	private void matarHilos() {
		creadorM.kill();
		creadorC.kill();
		ensamblador.kill();
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
