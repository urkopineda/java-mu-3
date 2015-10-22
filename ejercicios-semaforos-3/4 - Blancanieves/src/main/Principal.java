package main;

import buffer.Comedor;
import thread.Blancanieves;
import thread.Enano;

public class Principal {

	public static final int NUMENANOS = 7;
	Comedor comedor;
	Enano enanos[];
	Blancanieves blancanieves;
	
	public Principal(){
		comedor = new Comedor();
		blancanieves = new Blancanieves(comedor);
		enanos = new Enano[NUMENANOS];
		for ( int i = 0; i<NUMENANOS;i++){
			enanos[i]= new Enano(i+1,comedor);
		}
	}
	public void iniciar(){
		blancanieves.start();
		for ( int i = 0; i<NUMENANOS;i++){
			enanos[i].start();
		}
	}
	public static void main(String[] args) {
		Principal ejercicio = new Principal();
		ejercicio.iniciar();
	}

}