package thread;

import buffer.Buffer;

public class Productor extends Thread {
	
	int id;
	Buffer<Object> buffer;
	
	boolean endThread;
	
	public Productor(int id, Buffer<Object> buffer) {
		this.id = id;
		this.buffer = buffer;
		endThread = false;
	}

	@Override
	public void run() {
		while (!endThread) {
			Object o = new Object();
			try {
				buffer.put(o);
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
