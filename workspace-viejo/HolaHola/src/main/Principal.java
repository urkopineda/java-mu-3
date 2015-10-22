package main;

import java.util.Scanner;

public class Principal {
		
	Scanner teclado = new Scanner(System.in);
	
	public Principal() {
		MiHilo hilito = new MiHilo();
		hilito.start();
		teclado.nextLine();
		hilito.interrupt();
		teclado.close();
	}
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		Principal principal = new Principal();
	}
	
}
