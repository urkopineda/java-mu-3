package lectoresescritores1;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.Semaphore;

public class Buffer {
	final int NUMINICIAL= 5;
	
	Semaphore candado;
	Semaphore mutex;
	ArrayList<Integer> valores;
	volatile int numLectores;
	
	public Buffer (){
		numLectores = 0;
		valores = new ArrayList<>();
		for (int i = 0; i<NUMINICIAL; i++){
			valores.add(i);
		}
		candado = new Semaphore (1);
		mutex = new Semaphore(1);
	}
	public void añadir(int id) throws InterruptedException{
		candado.acquire();
		int num = valores.size();
		valores.add(num);
		System.out.println("Escritor "+id+ " escribe "+num);
		candado.release();
	}
	public void mostrar(int id) throws InterruptedException{
		mutex.acquire();
		numLectores ++;
		if (numLectores == 1){
			candado.acquire();
		}
		mutex.release();
		ListIterator<Integer> it = valores.listIterator();
		while (it.hasNext()){
			System.out.println("lector "+ id+" lee: "+it.next());
		}
		mutex.acquire();
		numLectores --;
		if (numLectores==0){
			candado.release();
		}
		mutex.release();
	}
}
