package thread;

import buffer.Buffer;
import data.Cubo;

public class RobotB extends Thread {
	
	Buffer bufferCubos;
	boolean endThread;
	
	int id = 1;
	
	public RobotB(Buffer bufferCubos) {
		this.bufferCubos = bufferCubos;
		endThread = false;		
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				bufferCubos.addObject(id, new Cubo()); // ponerCubo();
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
