package thread;

import buffer.Buffer;

public class RobotC extends Thread {
	
	Buffer bufferCilindros;
	Buffer bufferCubos;
	boolean endThread;
	
	int id = 2;
	
	public RobotC(Buffer bufferCilindros, Buffer bufferCubos) {
		this.bufferCilindros = bufferCilindros;
		this.bufferCubos = bufferCubos;
		endThread = false;		
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				bufferCilindros.getObject(id); // retirarCilindros();
				bufferCubos.getObject(id); // retirarCubos();
				System.out.println("Hilo ENSAMBLADOR Nº"+id+" crea un objeto.");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
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
