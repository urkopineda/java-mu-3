import java.util.concurrent.Semaphore;

public class Cuenta {
	
	Semaphore sem;
	int saldo;
	
	public Cuenta(int saldo, int hilos) {
		this.saldo = saldo;
		sem = new Semaphore(hilos);
	}
	
	public void meter(int dinero) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		saldo += dinero;
		sem.release();
	}
	
	public void sacar(int dinero) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		saldo -= dinero;
		sem.release();
	}

}
