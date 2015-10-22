package buffer;

import java.util.concurrent.Semaphore;

import thread.Hombre;
import thread.Mujer;

public class Ba�o {
	
	Object [] personas;
	int capacidad;
	int posicion;
		
	Semaphore candado;
	Semaphore semPersonas;
	Semaphore semS;
	
	int count;
	
	public Ba�o(int capacidad) {
		personas = new Object[capacidad];
		this.capacidad = capacidad;
		posicion = 0;
		candado = new Semaphore(1);
		semPersonas = new Semaphore(capacidad);
		semS = new Semaphore(0);
		count = 0;
	}
	
	public void entrar(Object persona) throws InterruptedException {
		if (posicion != 0) {
			if (persona.getClass() != personas[0]) {
				count++;
				semS.acquire();
			}
		}
		semPersonas.acquire();
		candado.acquire();
		if (persona instanceof Mujer) {
			Mujer tempMujer = (Mujer) persona;
			personas[posicion++] = tempMujer;
			System.out.println("Mujer Nº"+tempMujer.getThreadId()+" entra al baño, "+posicion);
		} else if (persona instanceof Hombre) {
			Hombre tempHombre = (Hombre) persona;
			personas[posicion++] = tempHombre;
			System.out.println("Hombre Nº"+tempHombre.getThreadId()+" entra al baño, "+posicion);
		}
		candado.release();
	}
	
	public void salir(Object persona) throws InterruptedException {
		candado.acquire();
		if (persona instanceof Mujer) {
			Mujer tempMujer = (Mujer) persona;
			System.out.println("Mujer Nº"+tempMujer.getThreadId()+" sale del baño, "+(posicion - 1));
		} else if (persona instanceof Hombre) {
			Hombre tempHombre = (Hombre) persona;
			System.out.println("Hombre Nº"+tempHombre.getThreadId()+" sale del baño, "+(posicion - 1));
		}
		personas[--posicion] = null;
		candado.release();
		if (count == 0) {
			semS.release(count);
			count = 0;
		}
		semPersonas.release();
	}

}
