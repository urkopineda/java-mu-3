package main;

public class Principal {
	
	Ahorrador ahorradores[];
	Gastador gastadores[];
	
	Cuenta nuevaCuenta;
	
	public static int saldo = 0;
	public static int hilos = 10000;
	public static int ciclos = 100;
	
	public Principal() {
		
	}
	
	private void crearAhorradores() {
		ahorradores = new Ahorrador[hilos];
		for (int i = 0; i < hilos; i++) {
			ahorradores[i] = new Ahorrador(nuevaCuenta, ciclos);
		}
	}
	
	private void iniciarAhorradores() {
		for (int i = 0; i < hilos; i++) {
			ahorradores[i].start();
		}
	}
	
	private void crearGastadores() {
		gastadores = new Gastador[hilos];
		for (int i = 0; i < hilos; i++) {
			gastadores[i] = new Gastador(nuevaCuenta, ciclos);
		}
	}
	
	private void iniciarGastadores() {
		for (int i = 0; i < hilos; i++) {
			gastadores[i].start(); 
		}
	}
	
	private void syncClientes() {
		for (int i = 0; i < hilos; i++) {
			try {
				ahorradores[i].join();
				gastadores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void iniciar() {
		nuevaCuenta = new Cuenta(0);
		crearAhorradores();
		iniciarAhorradores();
		crearGastadores();
		iniciarGastadores();
		syncClientes();
		System.out.println("SALDO: "+nuevaCuenta.getSaldo());
	}
	
	public static void main(String args[]) {
		Principal principal = new Principal();
		principal.iniciar();
	}

}
