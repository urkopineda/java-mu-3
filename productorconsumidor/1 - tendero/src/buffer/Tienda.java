package buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tienda {
	
	Lock candado;
	
	Condition conCli;
	Condition conTen;
	
	int [] buffer;
	int contador;
	int tamaño;
	
	boolean servido;
	boolean ocupado;

	public Tienda(int tamaño) {
		candado = new ReentrantLock();
		conCli = candado.newCondition();
		conTen = candado.newCondition();
		buffer = new int[tamaño];
		this.tamaño = tamaño;
		contador = 0;
	}
	
	public void entrar(int id) throws InterruptedException {
		candado.lock();
		while (contador == tamaño) {
			conCli.await();
		}
		buffer[contador++] = id;
		candado.unlock();
	}
	
	public void pedir() {
		candado.lock();
		
		candado.unlock();
	}
	
	public void pagar() {
		candado.lock();
		buffer[contador--] = 0;
		conCli.signal();
		candado.unlock();
	}
	
	public void atender() {
		candado.lock();
		
		candado.unlock();
	}
	
}
