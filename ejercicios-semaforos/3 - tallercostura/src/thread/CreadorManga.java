package thread;

import buffer.Cesta;
import data.Manga;

public class CreadorManga extends Thread {
	
	Cesta cestaManga;
	boolean endThread;
	
	int id = 0;
	
	public CreadorManga(Cesta cestaManga) {
		this.cestaManga = cestaManga;
		endThread = false;
	}
	
	@Override
	public void run() {
		while (!endThread) {
			try {
				cestaManga.addObject(id, new Manga());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void kill() {
		endThread = true;
	}

}
