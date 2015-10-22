package thread;

import buffer.Buffer;
import data.Rueda;

public class RobotC extends Thread {
	
	Buffer bufferRueda;
	boolean endThread;
	
	int id = 2;
	
	public RobotC(Buffer bufferRueda) {
		this.bufferRueda = bufferRueda;
		endThread = false;		
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				bufferRueda.addObject(id, new Rueda()); // ponerRueda();
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
