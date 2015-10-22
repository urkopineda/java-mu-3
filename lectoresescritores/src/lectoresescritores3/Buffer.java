package lectoresescritores3;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.Semaphore;

public class Buffer {
	final int NUMINICIAL= 5;
	
	Semaphore candado;
	Semaphore mutexLectores, mutexEscritores;
	Semaphore cola;
	ArrayList<Integer> valores;
	volatile int numLectores;
	volatile int numEscritores;
	
	public Buffer (){
		numLectores = 0;
		numEscritores = 0;
		valores = new ArrayList<>();
		for (int i = 0; i<NUMINICIAL; i++){
			valores.add(i);
		}
		candado = new Semaphore (1);
		mutexLectores = new Semaphore(1);
		mutexEscritores = new Semaphore(1);
		cola = new Semaphore (1);
	}
	public void añadir(int id) throws InterruptedException{
		mutexEscritores.acquire();
		numEscritores++;
		if (numEscritores == 1){
			cola.acquire();
		}
		mutexEscritores.release();
		
		candado.acquire();
		int num = valores.size();
		valores.add(num);
		System.out.println("Escritor "+id+ " escribe "+num);
		Thread.sleep(10);
		candado.release();
		
		mutexEscritores.acquire();
		numEscritores --;
		if (numEscritores == 0){
			cola.release();
		}
		mutexEscritores.release();
		
	}
	public void mostrar(int id) throws InterruptedException{
		cola.acquire();
		
		mutexLectores.acquire();
		numLectores ++;
		if (numLectores == 1){
			candado.acquire();
		}
		mutexLectores.release();
		
		cola.release();
		ListIterator<Integer> it = valores.listIterator();
		while (it.hasNext()){
			System.out.println("lector "+ id+" lee: "+it.next());
			Thread.sleep(100);
		}
		mutexLectores.acquire();
		numLectores --;
		if (numLectores==0){
			candado.release();
		}
		mutexLectores.release();
	}
}
