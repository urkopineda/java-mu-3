package main;

import java.util.Random;

import exception.MatrixErrorException;
import thread.Calculador;

/**
 * En la clase Principal, definimos las matrices a calcular (en este caso, aleatorias).
 * Finalmente, se las pasamos a la clase 'Calcular', que iniciará un hilo para calcular
 * la multiplicación de las dos matrices, generando una tercera matriz.
 * 
 * @author urko
 *
 */
public class Principal {
	
	Calculador calculadores[];
	
	Random rand;
	
	int [][] a;
	int [][] b;
	int [][] c;
	
	public static int ROW_A = 100;
	public static int COLUMN_A = 100;
	public static int ROW_B = 100;
	public static int COLUMN_B = 100;
	public static int MAX_HILOS = 10;
	
	static int MAX_RANDOM = 100;
	
	/**
	 * Inicializamos las dos matrices, junto con el objeto para generar números aleatorios.
	 */
	public Principal() {
		a = new int [ROW_A][COLUMN_A];
		b = new int [ROW_B][COLUMN_B];
		rand = new Random();
	}
	
	/**
	 * Genera 2 matrices con números aleatorios.
	 */
	private void generarMatrices() {
		for (int i = 0; i < Principal.ROW_A; i++) {
			for (int j = 0; j < Principal.COLUMN_A; j++) {
				a[i][j] = rand.nextInt(MAX_RANDOM);
			}
		}
		for (int i = 0; i < Principal.ROW_B; i++) {
			for (int j = 0; j < Principal.COLUMN_B; j++) {
				b[i][j] = rand.nextInt(MAX_RANDOM);
			}
		}
	}
	
	private void generarHilos() {
		calculadores = new Calculador[MAX_HILOS];
		for (int i = 0; i < MAX_HILOS; i++) calculadores[i] = new Calculador(i, a, b);
	}
	
	private void iniciarHilos() {
		for (int i = 0; i < MAX_HILOS; i++) calculadores[i].start();
	}
	
	private void syncHilos() {
		for (int i = 0; i < MAX_HILOS; i++) {
			try {
				calculadores[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Unimos todas las matrices en una gran matriz. 
	 */
	private void crearResultado() {
		c = new int [ROW_A][COLUMN_B];
		for (int i = 0; i < MAX_HILOS; i++) {
			int h = 0;
			for (int k = (calculadores[i].getIdHilo() * (ROW_A / MAX_HILOS)); k < calculadores[i].getC().length; k++) {
				for (int j = 0; j < calculadores[i].getC()[0].length; j++) {
					c[k][j] = calculadores[i].getC()[h][j];
				}
				h++;
			}
		}
	}
	
	/**
	 * Con este método printamos una matriz.
	 * 
	 * @param matriz
	 * 
	 */
	private void printarMatriz(int [][] matriz) {
		for (int i = 0; i != matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	/**
	 * Hilo principal del programa.
	 * @throws MatrixErrorException 
	 */
	private void start() throws MatrixErrorException {
		generarMatrices();
		if (a[0].length != b.length) {
			throw new MatrixErrorException("ERROR: Las matrices propuestas no son válidas para multiplicar.");
		}
		generarHilos();
		iniciarHilos();
		syncHilos();
		crearResultado();
		printarMatriz(c);
	}

	public static void main(String [] args) {
		Principal principal = new Principal();
		try {
			principal.start();
		} catch (MatrixErrorException e) {
			e.printStackTrace();
		}
	}
	
}
