public class Principal {
	
	Cuenta nuevaCuenta;
	Cliente [] clientes;
	
	public static int saldo = 0;
	public static int hilos = 5000;
	public static int maxHilos = 1;
	public static int ciclos = 100;
	
	public Principal() {
		nuevaCuenta = new Cuenta(saldo, maxHilos);
	}
	
	public void crearClientes() {
		clientes = new Cliente[hilos];
		int i = 0;
		for (i = 0; i < hilos; i++) {
			clientes[i] = new Cliente(0, ciclos, nuevaCuenta);
		}
		while (i < (hilos * 2)) {
			clientes[i] = new Cliente(1, ciclos, nuevaCuenta);
			 i++;
		}
	}
	
	public void iniciarClientes() {
		for (int i = 0; i < (hilos * 2); i++) clientes[i].start();
	}
	
	public void syncClientes() {
		for (int i = 0; i < (hilos * 2); i++) {
			try {
				clientes[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void iniciar() {
		crearClientes();
		iniciarClientes();
		syncClientes();
		System.out.println(nuevaCuenta.saldo);
	}
	
	public static void main(String args[]) {
		Principal principal = new Principal();
		principal.iniciar();
	}

}
