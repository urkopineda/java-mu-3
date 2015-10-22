package thread;

import main.Principal;

public class HiloContador extends Thread {

	int id;
	
	static Object o = new Object();
	static int contador = 0;
	
	public HiloContador(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("H"+id+":"+(i + 1));
		}
		synchronized(o) {
			contador++;
			while (contador != Principal.MAX_HILOS) {
				try {
					o.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			o.notifyAll();
		}
		System.out.println("Agur!");
	}
	
}
