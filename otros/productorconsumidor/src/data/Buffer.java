package data;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Buffer {
	
	volatile AtomicInteger valor = new AtomicInteger(0);
	Semaphore sem;
	
	boolean hasChanged = false;

	public Buffer() {
		sem = new Semaphore(1);		
	}
	
	public void put(int newValor) {
		try {
			sem.acquire();
			valor.set(newValor);
			hasChanged = true;
			System.out.println("PRODUCTOR: "+newValor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sem.release();
	}
	
	public int get(){
		int temp = 0;
		try {
			sem.acquire();
			temp = valor.get();
			hasChanged = false;
			System.out.println("CONSUMIDOR: "+temp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sem.release();
		return temp;
	}
	
	public boolean getHasChanged() {
		return hasChanged;
	}

	public void setHasChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;
	}
	
}
