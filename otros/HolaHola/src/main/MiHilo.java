package main;

public class MiHilo extends Thread {
	
	volatile boolean enter;
	
	public MiHilo() {
		enter = false;
	}

	public void run() {
		while (!enter) {
			System.out.println("Hola holita, vecinito!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	
	@Override
	public void interrupt() {
		enter = true;
		super.interrupt();
	}
	
}
