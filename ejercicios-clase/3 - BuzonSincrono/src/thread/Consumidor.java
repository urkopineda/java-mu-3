package thread;

import buffer.Buffer;

public class Consumidor extends Thread {
	
	int id;
	Buffer<Object> buffer;
	
	boolean endThread;
	
	public Consumidor(int id, Buffer<Object> buffer) {
		this.id = id;
		this.buffer = buffer;
		endThread = false;
	}

	@Override
	public void run() {
		while (!endThread) {
			@SuppressWarnings("unused")
			Object o = null;
			try {
				o = buffer.get();
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
