package thread;

import java.util.Random;

import buffer.Buffer;

public class Productor extends Thread {
	
	Random rand;
	
	int id;
	Buffer buffer;
	
	boolean endThread;
	
	public Productor(int id, Buffer buffer) {
		rand = new Random();
		this.id = id;
		this.buffer = buffer;
		endThread = false;
	}

	@Override
	public void run() {
		while (!endThread) {
			try {
				buffer.put(id, rand.nextInt(100));
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
