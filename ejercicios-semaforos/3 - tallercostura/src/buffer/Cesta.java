package buffer;

import java.util.concurrent.Semaphore;

public class Cesta {

	Object [] cesta;
	int posicion;
	int capacidad;
	
	Semaphore sem1, sem2;
	Semaphore candado;
		
	public Cesta(int capacidad) {
		cesta = new Object[capacidad];
		this.capacidad = capacidad;
		posicion = 0;
		sem1 = new Semaphore(0);
		sem2 = new Semaphore(0);
		candado = new Semaphore(1);
	}
	
	public int getSize() {
		return posicion + 1;
	}
	
	public void addObject(int id, Object o) throws InterruptedException {
		candado.acquire();
		System.out.println("Hilo CREADOR Nº"+id+" añade objeto, cantidad actual "+(posicion + 1));
		cesta[posicion++] = o;
		if (posicion >= capacidad) {
			sem1.release();
			sem2.acquire();
		}
		candado.release();
	}
	
	public void getObject(int id, int cantidad) throws InterruptedException {
		sem1.acquire();
		if (cantidad < posicion) {
			posicion--;
			for (int i = 0; i < cantidad; i++) {
				System.out.println("Hilo CONSUMIDOR Nº"+id+" consume objeto, cantidad actual"+(posicion));
				cesta[posicion--] = null;
			}
		}
		sem2.release();
	}
	
	public Object [] getCesta() {
		return cesta;
	}
}
