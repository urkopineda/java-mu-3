package buffer;

import java.util.concurrent.Semaphore;

public class Centro {
	
	int [] parking;
	
	int posicion;
	int cantidadParking;
	int cantidadCocheras;
	
	Semaphore candado;
	Semaphore sem;
	
	public Centro(int cantidadParking, int cantidadCocheras) {
		parking = new int[cantidadParking];
		posicion = 0;
		this.cantidadParking = cantidadParking;
		this.cantidadCocheras = cantidadCocheras;
		candado = new Semaphore(1);
		sem = new Semaphore(0);
	}
	
	public void entrarAlParking(int id) throws InterruptedException {
		candado.acquire();
		boolean found = false;
		for (int i = 0; i < cantidadParking; i++) {
			if (parking[i] == 0) {
				parking[i] = id;
				found = true;
				System.out.println("El coche "+id+" entra en aparcamiento a la posicion "+(i + 1));
				i = cantidadParking;
				sem.release();
			}
		}
		if (!found) {
			System.out.println("El coche "+id+" se va por que el parking está lleno");
		}
		candado.release();
	}
	
	public int pasarCochera(int id) throws InterruptedException {
		sem.acquire();
		int j = 0;
		for (int i = 0; i < cantidadParking; i++) {
			if (parking[i] != 0) {
				System.out.println("Coche "+parking[i]+" pasa a revisión con operario "+id);
				j = parking[i];
				parking[i] = 0;
			}
		}
		return j;
	}
	
	public void pasarRevision(int id, int coche) throws InterruptedException {
		System.out.println("Operario "+id+" revisa coche "+coche);
	}
	
	public void acabarRevision(int id, int coche) throws InterruptedException {
		System.out.println("Operario "+id+" termina de revisar coche "+coche);
	}
	
	public void salirDeRevision(int coche) throws InterruptedException {
		System.out.println("Coche "+coche+" ha terminado su revisión y sale");
	}
	
}
