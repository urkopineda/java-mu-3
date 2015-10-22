package thread;

import java.util.Random;

import buffer.Buffer;

public class Productor extends Thread {
	
	Random rand;
	
	int id;
	Buffer buffer;
	
	boolean hasChanged;
	
	public Productor(int id, Buffer buffer) {
		rand = new Random();
		this.id = id;
		this.buffer = buffer;
		hasChanged = false;
	}
	
	@Override
	public void run() {
		while (!hasChanged) {
			int temp = rand.nextInt(10);
			try {
				buffer.put(temp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("PRODUCTOR: PUT "+temp);
		}
	}
	
	public void kill() {
		hasChanged = true;
		this.interrupt();
	}

}
