package main;

public class HiloContador extends Thread {
	
	public HiloContador() {
		
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) System.out.println(i + 1);
	}
	
}
