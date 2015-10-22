package thread;

import java.util.Random;

import buffer.Buffer;

public class RobotD extends Thread {

	Buffer bufferCilindros;
	Buffer bufferCubos;
	Buffer bufferRueda;
	boolean endThread;
	
	Random rand;
	
	int id = 3;
	
	int cantidadRuedas = 3;
	int cantidadCubos = 1;
	int cantidadCilindros = 1;
	
	public RobotD(Buffer bufferCilindros, Buffer bufferCubos, Buffer bufferRueda) {
		this.bufferCilindros = bufferCilindros;
		this.bufferCubos = bufferCubos;
		this.bufferRueda = bufferRueda;
		rand = new Random();
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				int i = 0, j = 0, k = 0;
				while ((i != cantidadRuedas) || (j != cantidadCubos) || (k != cantidadCilindros)) {
					switch (rand.nextInt(3)) {
					case 0:
						if (i != cantidadRuedas) {
							bufferCilindros.getObject(1);
							i++;
						}
						break;
					case 1:
						if (j != cantidadCubos) {
							bufferCubos.getObject(1);
							j++;
						}
						break;
					case 2:
						if (k != cantidadCilindros) {
							bufferCilindros.getObject(1);
							k++;
						}
						break;
					}
				}
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
