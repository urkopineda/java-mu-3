package main;

public class Cliente extends Thread {

	int tipo;
	Cuenta cuenta;
	int ciclos;
	
	public Cliente(int tipo, Cuenta cuenta, int ciclos) {
		this.tipo = tipo;
		this.cuenta = cuenta;
		this.ciclos = ciclos;
	}
	
	public void run() {
		if (tipo == 0) for (int i = 0; i < ciclos; i++) cuenta.meterDinero(1);
		else if (tipo == 1) for (int i = 0; i < ciclos; i++) cuenta.sacarDinero(1);
	}
	
}
