package main;

public class Gastador extends Thread {
	
	Cuenta cuenta;
	int ciclos;
	
	public Gastador(Cuenta cuenta, int ciclos) {
		this.cuenta = cuenta;
		this.ciclos = ciclos;
	}
	
	public void run() {
		for (int i = 0; i < ciclos; i++) cuenta.sacarDinero(1);
	}

}
