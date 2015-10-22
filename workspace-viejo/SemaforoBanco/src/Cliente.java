
public class Cliente extends Thread {
	
	int tipo;
	int ciclos;
	Cuenta cuenta;
	
	public Cliente(int tipo, int ciclos, Cuenta cuenta) {
		this.tipo = tipo;
		this.ciclos = ciclos;
		this.cuenta = cuenta;
	}
	
	public void run() {
		if (tipo == 0) {
			for (int i = 0; i < ciclos; i++) cuenta.meter(1);
		} else if (tipo == 1) {
			for (int i = 0; i < ciclos; i++) cuenta.sacar(1);
		}
	}

}
