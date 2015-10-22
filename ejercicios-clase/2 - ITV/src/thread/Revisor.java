package thread;

import buffer.GarageITV;

public class Revisor extends Thread {
	
	int id;
	GarageITV itv;
		
	public Revisor(int id, GarageITV itv) {
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
