package buffer;

import java.util.ArrayList;

public class aBuzon<A> {
	
	int capacidad;
	int count;
	ArrayList<A> list;
	
	public aBuzon(int capacidad) {
		this.capacidad = capacidad;
		count = 0;
		list = new ArrayList<>();
	}
	
	public synchronized void send(A msg) throws InterruptedException {
		while (count >= capacidad) {
			this.wait();
		}
		list.add(msg);
		count++;
		this.notifyAll();
	}
	
	public synchronized void send() throws InterruptedException {
		while (count >= capacidad) {
			this.wait();
		}
		list.add(null);
		count++;
		this.notifyAll();
	}
	
	public synchronized A receive() throws InterruptedException {
		while (count <= 0) {
			this.wait();
		}
		--count;
		A temp = list.remove(0);
		this.notifyAll();
		return temp;
	}

}
