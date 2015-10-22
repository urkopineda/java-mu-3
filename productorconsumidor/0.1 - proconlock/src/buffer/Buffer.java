package buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
	
	Lock candado;
	
	Condition conPro;
	Condition conCon;
	
	
	int [] buffer;
	int contador;
	int tamaño;

	public Buffer(int tamaño) {
		candado = new ReentrantLock();
		conPro = candado.newCondition();
		conCon = candado.newCondition();
		buffer = new int[tamaño];
		this.tamaño = tamaño;
		contador = 0;
	}
	
	public void put(int valor) throws InterruptedException {
		candado.lock();
		while (contador == tamaño) {
			conPro.wait();
		}
		buffer[contador++] = valor;
		conCon.signal();
		candado.unlock();
	}
	
	public int get() throws InterruptedException {
		int valor = 0;
		candado.lock();
		while (contador == 0) {
			conCon.wait();
		}
		valor = buffer[contador--];
		conPro.signal();
		candado.unlock();
		return valor;
	}
	
}
