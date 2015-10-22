package buffer;

import thread.Duende;
import thread.Reno;

public class PoloNorte {
	
	int MAX_DUENDES_DESP;
	int MAX_RENOS_DESP;
	
	int countR;
	int countD;
	
	static Object monitorDuendes = new Object();
	static Object monitorRenos = new Object();
	
	Reno [] renos;
	Duende [] duendes;
		
	public PoloNorte(int MAX_DUENDES_DESP, int MAX_RENOS_DESP) {
		this.MAX_DUENDES_DESP = MAX_DUENDES_DESP;
		this.MAX_RENOS_DESP = MAX_RENOS_DESP;
		countR = 0;
		countD = 0;
		renos = new Reno[MAX_RENOS_DESP];
		duendes = new Duende[MAX_DUENDES_DESP];
	}
	
	public void llegar(Reno reno) throws InterruptedException {
		synchronized (monitorRenos) {
			renos[countR++] = reno;
			if (countR >=  MAX_RENOS_DESP) {
				System.out.println("Último reno, reno Nº"+reno.getThreadId()+" llega al Polo Norte, despierta a Santa.");
				synchronized (this) {
					this.notify();
				}
				monitorRenos.wait();
			} else {
				System.out.println("Reno Nº"+reno.getThreadId()+" llega al Polo Norte, espera a los demás en el refugio.");
				monitorRenos.wait();
			}
		}
	}
	
	public void llegar(Duende duende) throws InterruptedException {
		synchronized (monitorDuendes) {
			while (countD >= MAX_DUENDES_DESP) {
				System.out.println("Duende Nº"+duende.getThreadId()+" pasa a espera, la cola está llena.");
				monitorDuendes.wait();
			}
			duendes[countD++] = duende;
			if (countD >=  MAX_DUENDES_DESP) {
				System.out.println("Último duende, duende Nº"+duende.getThreadId()+" tiene dificultades, despierta a Santa.");
				synchronized (this) {
					this.notify();
				}
				monitorDuendes.wait();
			} else {
				System.out.println("Duende Nº"+duende.getThreadId()+" tiene dificultades, espera a los demás.");
				monitorDuendes.wait();
			}
		}
	}
	
	private boolean isRenos() {
		synchronized (monitorRenos) {
			if (countR >=  MAX_RENOS_DESP) return true;
			else return false;
		}
	}
	
	private boolean isDuendes() {
		synchronized (monitorDuendes) {
			if (countD >=  MAX_DUENDES_DESP) return true;
			else return false;
		}
	}
	
	public synchronized void irADormir() {
		System.out.println("Santa va a dormir...");
	}
	
	public synchronized void atender() throws InterruptedException {
		this.wait();
		if (isRenos()) {
			System.out.println("Santa atiende a los renos...");
			countR = 0;
			synchronized (monitorRenos) {
				monitorRenos.notifyAll();
			}
		} else if (isDuendes()) {
			System.out.println("Santa atiende a los Duendes...");
			countD = 0;
			synchronized (monitorDuendes) {
				monitorDuendes.notifyAll();
			}
		} else System.out.println("¡¿QUIEN HA DESPERTADO A SANTA!?");
	}

}
