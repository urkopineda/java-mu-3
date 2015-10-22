package thread;

import java.util.Random;

import data.Buffer;

public class Productor extends Thread {
	
	Random rand;
	Buffer buffer;
	
	volatile boolean endThread;
	
	public Productor(Buffer buffer) {
		this.buffer = buffer;
		rand = new Random();
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			if (!buffer.getHasChanged()) {
				buffer.put(rand.nextInt(10));
			}
		}
	}
	
	public void kill() {
		endThread = true;
	}

}
