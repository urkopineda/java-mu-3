package buffer;

public class Buffer {
	
	int capacidad;
	int consumidores;
	
	int [] buffer;
	int [] count;
	int proCount;
	
	boolean filling;
	
	static Object o = new Object();
			
	public Buffer(int capacidad, int consumidores) {
		this.capacidad = capacidad;
		this.consumidores = consumidores;
		buffer = new int[capacidad];
		count = new int[consumidores];
		proCount = 0;
		filling = true;
	}
	
	public void get(int id) throws InterruptedException {
		while (count[id] < 1) {
			synchronized (this) {
				this.notify();
			}
			synchronized (o) {
				o.wait();
			}
		}
		System.out.println("Consumidor "+id+" consume "+buffer[--count[id]]);
	}
	
	public void put(int id, int valor) throws InterruptedException {
		if (!filling) {
			while (MIN_checkConsumers()) {
				synchronized (o) {
					o.notifyAll();
				}
				synchronized (this) {
					this.wait();
				}
			}
		}
		filling = true;
		System.out.println("Productor "+id+" produce "+valor);
		buffer[count[0]++] = valor;
		for (int i = 1; i < consumidores; i++) count[i] = count[0];
		if (MAX_checkConsumers()) {
			filling = false;
			synchronized (o) {
				o.notifyAll();
			}
		}
	}
	
	private boolean MIN_checkConsumers() {
		for (int i = 0; i < consumidores; i++) {
			if (count[i] == 0) return false;
		}
		return true;
	}
	
	private boolean MAX_checkConsumers() {
		for (int i = 0; i < consumidores; i++) {
			if (count[i] != capacidad) return false;
		}
		return true;
	}

}
