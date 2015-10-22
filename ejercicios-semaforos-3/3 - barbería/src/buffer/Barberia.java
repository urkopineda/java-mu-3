package buffer;

import thread.Barbero;
import thread.Cliente;

public class Barberia {
	
	Barbero barbero;
	Cliente [] cli;
	int capacidad;
	int posicion;
	
	boolean ocupado = false;
	
	public Barberia(int capacidad, Barbero barbero) {
		cli = new Cliente[capacidad];
		this.barbero = barbero;
		this.capacidad = capacidad;
		posicion = 0;
	}
	
	public synchronized void entrar(Cliente persona) throws InterruptedException {
		if (posicion != capacidad) {
			cli[posicion++] = persona;
			System.out.println("Cliente Nº"+persona.getId()+" entra y se sienta.");
			barbero.notifyAll();
		} else System.out.println("Cliente Nº"+persona.getId()+" se va por que no hay sitio.");
	}
	
	public synchronized void salir(Cliente persona) {
		System.out.println("Cliente Nº"+persona.getId()+" se va.");
	}
	
	public synchronized void atender(Barbero barbero) throws InterruptedException {
		while (posicion == 0) {
			System.out.println("El barbero se va a dormir.");
			synchronized (barbero) {
				ocupado = false;
				barbero.wait();
			}
		}
		ocupado = true;
		System.out.println("El barbero atiende a cliente Nº"+cli[--posicion].getId()+".");
		salir(cli[posicion]);
		ocupado = false;
	}

}
