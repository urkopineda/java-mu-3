package main;

public class Principal {
	
	static int cantidadHilos = 100;
	GeneradorNumeros[] hilos;
	
	public Principal() {
		
	}
	
	private void crearHilos() {
		hilos = new GeneradorNumeros[cantidadHilos];
		for (int i = 0; i < cantidadHilos; i++) hilos[i] = new GeneradorNumeros(i);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < cantidadHilos; i++) hilos[i].start();
	}
	
	private void syncHilos() {
		for (int i = 0; i < cantidadHilos; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printList() {
		System.out.println("\nPrintando valores...");
		for (int i = 0; i < GeneradorNumeros.listaNumeros.size(); i++) System.out.println((i + 1)+"-. "+GeneradorNumeros.listaNumeros.get(i));
	}
	
	private void iniciar() {
		crearHilos();
		iniciarHilos();
		syncHilos();
		printList();
	}
	
	public static void main(String args[]) {
		Principal principal = new Principal();
		principal.iniciar();
	}

}
