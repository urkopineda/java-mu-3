package lectoresescritores;

import java.util.ArrayList;
import java.util.ListIterator;

public class Buffer {
	final int NUMINICIAL= 5;
	
	ArrayList<Integer> valores;
	
	public Buffer (){
		valores = new ArrayList<>();
		for (int i = 0; i<NUMINICIAL; i++){
			valores.add(i);
		}
	}
	public void añadir(int id){
		int num = valores.size();
		valores.add(num);
		System.out.println("Escritor "+id+ " escribe "+num);
	}
	public void mostrar(int id){
		ListIterator<Integer> it = valores.listIterator();
		while (it.hasNext()){
			System.out.println("lector "+ id+" lee: "+it.next());
		}
	}
}
