package main;

import java.util.concurrent.atomic.AtomicInteger;

public class Cuenta {
	
	private AtomicInteger saldo;
		
	public Cuenta(int saldo) {
		this.saldo = new AtomicInteger(saldo);
	}
	
	public int getSaldo() {
		return saldo.get();
	}
	
	public void meterDinero(int cantidad) {
		saldo.getAndAdd(cantidad);
	}
	
	public void sacarDinero(int cantidad) {
		saldo.getAndAdd(-cantidad);
	}

}
