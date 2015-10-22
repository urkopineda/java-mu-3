package buffer;

import java.util.concurrent.Semaphore;

public class Buffer {

	Object [] objetos;
	int posicion;
	int capacidad;
	
	Semaphore sem1, sem2;
	Semaphore candado;
		
	public Buffer(int capacidad) {
		objetos = new Object[capacidad];
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
		objetos[posicion] = o;
		sem1.release();
		sem2.acquire();
		candado.release();
	}
	
	public void getObject(int id) throws InterruptedException {
		sem1.acquire();
		System.out.println("Hilo CONSUMIDOR Nº"+id+" consume objeto, cantidad actual "+(posicion));
		objetos[posicion] = null;
		sem2.release();
	}
	
	public Object [] getObjetos() {
		return objetos;
	}
	
}
