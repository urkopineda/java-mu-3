package thread;

import buffer.Buffer;

public class Consumidor extends Thread {
	
	int id;
	Buffer buffer;
	
	boolean endThread;
	
	public Consumidor(int id, Buffer buffer) {
		this.id = id;
		this.buffer = buffer;
		endThread = false;
	}

	@Override
	public void run() {
		while (!endThread) {
			try {
				buffer.get(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		endThread = true;
		this.interrupt();
	}
	
}
