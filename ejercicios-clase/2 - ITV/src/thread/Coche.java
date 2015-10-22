package thread;

import buffer.GarageITV;

public class Coche extends Thread {
	
	int id;
	GarageITV itv;
	
	public Coche(int id, GarageITV itv) {
		this.id = id;
		this.itv = itv;
	}
	
	@Override
	public void run() {
		
	}
	
	public void kill() {
		this.interrupt();
	}

}

