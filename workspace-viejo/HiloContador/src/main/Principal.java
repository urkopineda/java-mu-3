package main;

public class Principal {

	public Principal() {
		HiloContador hilo = new HiloContador();
		hilo.start();
		System.out.println("Agur!");
	}
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		Principal newPrincipal = new Principal();
	}
	
}
