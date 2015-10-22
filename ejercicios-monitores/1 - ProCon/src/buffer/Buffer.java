package buffer;

public class Buffer {
	
	int capacidad;
	int [] buffer;
	int count;
		
	public Buffer(int capacidad) {
		this.capacidad = capacidad;
		buffer = new int[capacidad];
		count = 0;
	}
	
	public synchronized void get(int id) throws InterruptedException {
		while (count < 1) {
			this.wait();
		}
		System.out.println("Consumidor "+id+" consume "+buffer[--count]);
		buffer[count] = 0;
		this.notifyAll();
	}
	
	public synchronized void put(int id, int valor) throws InterruptedException {
		while (count >= capacidad) {
			this.wait();
		}
		System.out.println("Productor "+id+" produce "+valor);
		buffer[count++] = valor;
		this.notifyAll();
	}

}
