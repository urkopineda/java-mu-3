package thread;

import buffer.Centro;

public class Operario extends Thread {
	
	int id;
	boolean hasChanged;
	Centro centro;
	
	public Operario(int id, Centro centro) {
		this.id = id;
		hasChanged = false;
		this.centro = centro;
	}
	
	@Override
	public void run() {
		while (!hasChanged) {
			try {
				int coche = centro.pasarCochera(id);
				centro.pasarRevision(id, coche);
				centro.acabarRevision(id, coche);
				centro.salirDeRevision(coche);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		hasChanged = true;
		System.out.println("Operario "+id+" se va");
		this.interrupt();
	}

}
