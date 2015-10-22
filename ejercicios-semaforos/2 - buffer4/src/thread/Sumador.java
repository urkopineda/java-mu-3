package thread;

import buffer.Buffer;

public class Sumador extends Thread {

	int id;
	Buffer buffer;
	boolean endThread;
	
	public Sumador(int id, Buffer buffer) {
		this.id = id;
		this.buffer = buffer;
		endThread = false;
	}
	
	@Override
	public void run() {
		while(!endThread) {
			buffer.añadirValor(id);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void end() {
		endThread = true;
	}
	
}
