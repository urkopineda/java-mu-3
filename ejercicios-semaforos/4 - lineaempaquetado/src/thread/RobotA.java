package thread;

import buffer.Buffer;
import data.Cilindro;

public class RobotA extends Thread {
	
	Buffer bufferCilindros;
	boolean endThread;
	
	int id = 0;
	
	public RobotA(Buffer bufferCilindros) {
		this.bufferCilindros = bufferCilindros;
		endThread = false;		
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				bufferCilindros.addObject(id, new Cilindro()); // ponerCilindro();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	public void kill() {
		endThread = true;
	}

}
