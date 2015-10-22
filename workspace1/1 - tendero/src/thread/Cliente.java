package thread;

import buffer.Tienda;

public class Cliente extends Thread {
		
	int id;
	Tienda tienda;
	
	boolean hasChanged;
	
	public Cliente(int id, Tienda tienda) {
		this.id = id;
		this.tienda = tienda;
		hasChanged = false;
	}
	
	@Override
	public void run() {
		try {
			tienda.entrar(id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tienda.pedir();
		tienda.pagar();
	}
	
	public void kill() {
		hasChanged = true;
		this.interrupt();
	}

}
