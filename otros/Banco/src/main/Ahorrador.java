package main;

public class Ahorrador extends Thread {
	
	Cuenta cuenta;
	int ciclos;
	
	public Ahorrador(Cuenta cuenta, int ciclos) {
		this.cuenta = cuenta;
		this.ciclos = ciclos;
	}
	
	public void run() {
		for (int i = 0; i < ciclos; i++) cuenta.meterDinero(1);
	}
	
}
