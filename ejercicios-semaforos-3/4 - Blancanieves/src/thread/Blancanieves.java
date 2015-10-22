package thread;

import buffer.Comedor;
import main.Principal;

public class Blancanieves extends Thread {

	Comedor comedor;
	int numSilla;
	
	public Blancanieves (Comedor comedor){
		this.comedor = comedor;
	}
	
	public void run (){
		int contador =0;
		while (contador<Principal.NUMENANOS){
			comedor.darUnaVuelta();
			numSilla= comedor.servirComida();
			comedor.indicarPuedeComer(numSilla);
			contador++;
		}
		
	}
}