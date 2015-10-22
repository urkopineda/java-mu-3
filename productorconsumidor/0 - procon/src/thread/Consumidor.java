package thread;

import buffer.Buffer;

public class Consumidor extends Thread {
	
	int id;
	Buffer buffer;
	
	boolean hasChanged;
	
	public Consumidor(int id, Buffer buffer) {
		this.id = id;
		this.buffer = buffer;
		hasChanged = false;
	}
	
	@Override
	public void run() {
		while (!hasChanged) {
			int temp = buffer.get();
			System.out.println("CONSUMIDOR: GET "+temp);
		}
	}
	
	public void kill() {
		hasChanged = true;
		this.interrupt();
	}

}
