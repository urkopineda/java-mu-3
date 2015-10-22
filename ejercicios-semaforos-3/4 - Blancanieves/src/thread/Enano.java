package thread;

import buffer.Comedor;

public class Enano extends Thread {

	Comedor comedor;
	int id;
	int numSilla;
	public Enano (int id, Comedor comedor){
		this.id = id;
		this.comedor = comedor;
	}
	
	public void run(){
		llegarCasa();
		numSilla = comedor.sentarseSilla(id);
		comedor.pedirComida(id,numSilla);
		comedor.comer(id,numSilla);
		comedor.dejarSilla(id,numSilla);
		irTrabajar(id);
	}

	private void irTrabajar(int id2) {
		
		System.out.println ("El enano "+id+" se va a trabajar");
		
	}

	private void llegarCasa() {
		
		System.out.println ("El enano "+id+" llega a casa");
	}
}