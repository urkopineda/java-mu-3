package buffer;

import java.util.concurrent.Semaphore;

public class Puente {
	
	int capacidad;
	int sentido;
	
	Semaphore candado;
	Semaphore semPuente;
	Semaphore semSentido;
	
	int count;
	
	public Puente(int capacidad) {
		sentido = -1;
		count = 0;
		this.capacidad = capacidad;
		candado = new Semaphore(1);
		semPuente = new Semaphore(capacidad);
		semSentido = new Semaphore(0);
	}
	
	public void llegarPuente(int id, int sentido) throws InterruptedException {
		candado.acquire();
		String str;
		if (sentido == 0) str = "izquierda";
		else str = "derecha";
		System.out.println("Coche "+id+" llega por la "+str);
		candado.release();
	}
	
	public void cruzarPuente(int id, int sent) throws InterruptedException {
		if (sentido == -1) {
			sentido = sent;
		} else if (sentido != sent) {
			semSentido.acquire();
			sentido = sent;
		}
		semPuente.acquire();
		count++;
		String str;
		if (sent == 0) str = "izquierda";
		else str = "derecha";
		System.out.println("Coche "+id+" pasa por la "+str);
	}
	
	public void salirPuente(int id, int sentido) {
		String str;
		if (sentido == 1) str = "izquierda";
		else str = "derecha";
		System.out.println("Coche "+id+" sale por la "+str);
		semPuente.release();
		if (count == capacidad) semSentido.release();
	}

}
