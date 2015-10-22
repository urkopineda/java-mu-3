package thread;

import data.Buffer;

public class Consumidor extends Thread {

	Buffer buffer;
	
	volatile boolean endThread;
	
	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			if (buffer.getHasChanged())	buffer.get();
		}
	}
	
	public void kill() {
		endThread = true;
	}
	
}
