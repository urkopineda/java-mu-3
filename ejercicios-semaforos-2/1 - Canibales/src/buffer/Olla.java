package buffer;

import java.util.concurrent.Semaphore;

import data.Misionero;

public class Olla {
	
	int capacidad;
	int posicion;
	Misionero [] misioneros;
	
	Semaphore candado1;
	Semaphore candado2;
	Semaphore sem1;
	Semaphore sem2;
	
	public Olla(int capacidad) {
		this.capacidad = capacidad;
		posicion = -1;
		misioneros = new Misionero[capacidad];
		candado1 = new Semaphore(1);
		candado2 = new Semaphore(1);
		sem1 = new Semaphore(0);
		sem2 = new Semaphore(0);
	}
	
	public Misionero getMisionero() throws InterruptedException {
		Misionero m = null;
		candado1.acquire();
		if (posicion == -1) {
			sem2.release(capacidad);
			sem1.acquire();
		} else {
			m = misioneros[posicion];
			System.out.println("Canibal se sirve Misionero. CANTIDAD ACTUAL: "+posicion);
			misioneros[posicion--] = null;
		}
		candado1.release();
		return m;
	}
	
	public void addMisionero(Misionero m) throws InterruptedException {
		sem2.acquire();
		candado2.acquire();
		misioneros[++posicion] = m;
		System.out.println("Cocinero añade Misionero a la olla. CANTIDAD ACTUAL: "+(posicion + 1));
		if (capacidad == (posicion + 1)) {
			sem1.release();
		}
		candado2.release();
	}

}
