package buffer;

import thread.Hombre;
import thread.Mujer;

public class Discoteca {
	
	Object [] pers;
	int capacidad;
	int posicion;
	int p;
	int count;
	
	public Discoteca(int capacidad, int p) {
		pers = new Object[capacidad];
		this.capacidad = capacidad;
		posicion = 0;
		this.p = p;
		count = 0;
	}
	
	public void entrar(Object persona) throws InterruptedException {
		while (posicion == capacidad) {
			synchronized (pers) {
				pers.wait();
			}
		}
		if (persona instanceof Hombre) {
			if (count < p) {
				synchronized (pers) {
					pers.wait();
				}
			}
		}
		synchronized (this) {
			if (persona instanceof Hombre) {
				Hombre tempHom = (Hombre) persona;
				System.out.println("Hombre con ID "+tempHom.getId()+" entra a la Discoteca. CANTIDAD: "+(posicion + 1));
				pers[posicion++] = tempHom;
			} else if (persona instanceof Mujer) {
				Mujer tempMuj = (Mujer) persona;
				System.out.println("Mujer con ID "+tempMuj.getId()+" entra a la Discoteca. CANTIDAD: "+(posicion + 1));
				pers[posicion++] = tempMuj;
				count++;
			}
		}
		
	}
	
	public synchronized void salir(Object persona) {
		synchronized (this) {
			if (persona instanceof Hombre) {
				Hombre tempHom = (Hombre) persona;
				System.out.println("Hombre con ID "+tempHom.getId()+" sale de la Discoteca. CANTIDAD: "+(posicion - 1));
			} else if (persona instanceof Mujer) {
				Mujer tempMuj = (Mujer) persona;
				System.out.println("Mujer con ID "+tempMuj.getId()+" sale de la Discoteca. CANTIDAD: "+(posicion - 1));
			}
			pers[--posicion] = null;
		}
		synchronized (pers) {
			pers.notifyAll();
		}
	}

}
