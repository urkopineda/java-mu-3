package buffer;

import thread.Chica;
import thread.Chico;

public class PistaBaile {
	
	int capacidad;
	Object [] pista;
	int count;
		
	public PistaBaile(int capacidad) {
		this.capacidad = capacidad;
		pista = new Object[capacidad];
		count = 0;
	}
	
	public synchronized void bailar(Object persona) throws InterruptedException {
		if (count == 0) {
			pista[count++] = persona;
			if (persona instanceof Chico) {
				Chico temp = (Chico) persona;
				System.out.println("Chico "+temp.getThreadId()+" bailando.");
			} else if (persona instanceof Chica) {
				Chica temp = (Chica) persona;
				System.out.println("Chica "+temp.getThreadId()+" bailando.");
			} else System.out.println("ERROR");
		} else if (count == 1) {
			if (!persona.getClass().equals(pista[0].getClass())) {
				pista[count++] = persona;
				if (persona instanceof Chico) {
					Chico temp = (Chico) persona;
					System.out.println("Chico "+temp.getThreadId()+" bailando.");
					System.out.println("------------------------------------------");
					count = 0;
					this.notifyAll();
				} else if (persona instanceof Chica) {
					Chica temp = (Chica) persona;
					System.out.println("Chica "+temp.getThreadId()+" bailando.");
					System.out.println("------------------------------------------");
					count = 0;
					this.notifyAll();
				} else System.out.println("ERROR");
			} else {
				this.wait();
			}
		} else {
			this.wait();
		}
	}

}
