package thread;

import java.util.ArrayList;

import main.Principal;

public class Calculador extends Thread {
	
	int id;
	ArrayList<Integer> numerosHilos;
	
	public Calculador(int id) {
		this.id = id;
	}
	
	private boolean esPrimo(int num) {
		int div = 2;
		while (div < num) {
			if (num % div == 0) return true;
			else div++;
		}
		return false;
	}
	
	@Override
	public void run() {
		numerosHilos = new ArrayList<>();
		for (int i = primerNum(id); i < primerNum(id + 1); i++) {
			if (!esPrimo(i)) numerosHilos.add(i);
		}
	}
	
	private int primerNum(int id) {
		return id * (Principal.MAX_NUM / Principal.MAX_HILOS);
	}

	public ArrayList<Integer> getNumerosHilos() {
		return numerosHilos;
	}
	
}
