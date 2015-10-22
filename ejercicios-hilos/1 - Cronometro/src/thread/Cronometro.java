package thread;

public class Cronometro extends Thread {
	
	int segundos;
	boolean endThread;
	
	public Cronometro() {
		segundos = 0;
		endThread = false;
	}
	
	@Override
	public void run() {
		while(!endThread) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println(++segundos);
		}
	}
	
	public void end() {
		endThread = true;
	}
	
}
