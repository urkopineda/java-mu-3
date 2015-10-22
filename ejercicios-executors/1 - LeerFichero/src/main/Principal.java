package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Principal {
	
	BufferedReader br;
	
	static String FILE_NAME = "data";
	static int NUM_HILOS = 4;
	
	ExecutorService executor;
	
	public Principal() throws FileNotFoundException {
		br = new BufferedReader(new FileReader(FILE_NAME));
		//executor = Executors.newFixedThreadPool(NUM_HILOS);
		executor = Executors.newSingleThreadExecutor();
	}
	
	private void start() throws IOException, InterruptedException {
		String sCurrentLine = null;
		String [] lineArray = null;
		long inicio = System.currentTimeMillis();
		while ((sCurrentLine = br.readLine()) != null) {
			lineArray = sCurrentLine.split(" ");
			if (lineArray[0].equals("Multiplicar")) {
				executor.execute(new Calculator(0, 0, lineArray));
			} else if (lineArray[0].equals("Sumar")) {
				executor.execute(new Calculator(1, 1, lineArray));
			} else if (lineArray[0].equals("Maximo")) {
				executor.execute(new Calculator(2, 2, lineArray));
			} else {
				System.out.println("ERROR: Comando no identificado.");
			}
			executor.shutdown();
			executor.awaitTermination(30, TimeUnit.SECONDS);
		}
		long fin = System.currentTimeMillis();
		System.out.println (" ha tardado: "+(fin -inicio)+" milisegundos");
	}
	
	private class Calculator implements Runnable{
		
		int id;
		int opType;
		String [] num;
		
		int SUM = 0;
		int MULTI = 1;
		int MAX = 2;
		
		public Calculator(int id, int opType, String [] num){
			this.id = id;
			this.opType = opType;
			this.num = num;
		}
		
		@Override
		public void run() {
			int valor = Integer.parseInt(num[1]);
			for (int i = 2; i < num.length; i++) {
				valor = calcular(valor, Integer.parseInt(num[i]));
			}
			System.out.println("Runnable ID: "+id+", valor "+valor);
		}
		
		private int calcular(int valor1, int valor2) {
			if (opType == SUM) {
				return valor1 + valor2;
			} else if (opType == MULTI) {
				return valor1 * valor2;
			} else if (opType == MAX) {
				if (valor1 > valor2) return valor1;
				else return valor2;
			} else return -1;
		}
		
	}
	
	public static void main(String [] args) {
		Principal exe;
		try {
			exe = new Principal();
			exe.start();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
