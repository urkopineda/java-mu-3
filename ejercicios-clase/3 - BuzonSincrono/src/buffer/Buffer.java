package buffer;

import java.util.concurrent.Semaphore;

public class Buffer <A> {

	A buffer;
	
	Semaphore sCon;
	Semaphore sPro;
	Semaphore mutex;
	
	public Buffer() {
		buffer = null;
		sCon = new Semaphore(0);
		sPro = new Semaphore(1);
		mutex = new Semaphore(1);
	}
	
	public A get() throws InterruptedException {
		A temp;
		mutex.acquire();
		sCon.acquire();
		temp = buffer;
		System.out.println("Consumidor consume "+temp.getClass());
		sPro.release();
		mutex.release();
		return temp;
	}
	
	public void put(A a) throws InterruptedException {
		mutex.acquire();
		sPro.acquire();
		buffer = a;
		System.out.println("Productor produce "+a.getClass());
		sCon.release();
		mutex.release();
	}

}
