package thread;

import main.Principal;

public class Calculador extends Thread {
	
	int id;
		
	int [][] a;
	int [][] b;
	int [][] c;
	
	public Calculador(int id, int [][] a, int [][] b) {
		this.id = id;
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Hilo que calcula el resultado de la multiplicación de las dos matrices.
	 */
	@Override
	public void run() {
		c = new int [primeraLinea(id + 1)][Principal.COLUMN_B];
		for (int i = 0; i < (primeraLinea(id + 1) - primeraLinea(id)); i++) {			
			for (int j = 0; j < Principal.COLUMN_B; j++) {
				for (int k = 0; k < Principal.COLUMN_A; k++) {
					c[i][j] += (a[i][k] * b[k][j]);
				}
			}
		}
	}
	
	/**
	 * Método para calcular las lineas correspondientes a cada hilo.
	 * 
	 * @param id
	 * @return
	 */
	private int primeraLinea(int id) {
		return id * (Principal.ROW_A / Principal.MAX_HILOS);
	}

	public int getIdHilo() {
		return id;
	}

	public int[][] getC() {
		return c;
	}
	
}
