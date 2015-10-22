package buffer;

import java.util.concurrent.Semaphore;

public class Comedor {
	final int NUMSILLAS = 4;
	
	Semaphore candado;  			//utilizado para proteger el acceso a las variables de esta clase
	Semaphore sillasLibres;			//evita el acceso al comedor si está lleno
	
	Semaphore comidaServida[]; 		//evita que se empiece a comer si no se ha servido el plato
	Semaphore avisoBlancanieves; 	//hace que Blancanieves espere si no hay nadie sentado
	
	int sillas []; 					// sirve para identificar el enano sentado en la silla
	boolean sillasPendientes[]; 	//sirve para identificar si alguien sentado no tiene comida
	
	public Comedor(){
		candado = new Semaphore (1);  //permite el acceso a un unico hilo
		sillas = new int [NUMSILLAS]; 
		sillasPendientes = new boolean [NUMSILLAS];
		comidaServida = new Semaphore [NUMSILLAS]; 
		sillasLibres = new Semaphore(NUMSILLAS); //inicialemente todas las sillas libres
		for (int i=0;i<NUMSILLAS;i++){
			comidaServida[i]=new Semaphore(0); //inicialmente no hay comida en ningúna silla
			sillas [i]= 0; 						//inicialmente no hay ningún enano en la silla 
			sillasPendientes [i]= false; 		//inicialmente no hay sillas pendientes de servir
		}
		avisoBlancanieves = new Semaphore (0); //inicialmente Blancanieves no tiene trabajo pendiente
	}
	
	public int sentarseSilla(int id) {
		try {
			sillasLibres.acquire();   		//Si no hay sillas libres esperea
		} catch (InterruptedException e) {e.printStackTrace();	}
		
		
		
		try {
			candado.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		
			int numSilla = buscarSillaLibre(); 
			sillas[numSilla] = id;    //ocupa la silla
			
			System.out.println ("Enano "+id + " sentado en silla "+ numSilla);
		candado.release();
		return numSilla;
	}

	public void pedirComida(int id,int numSilla) {
		try {
			candado.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		sillasPendientes[numSilla]= true;             //indica que espera comida
		avisoBlancanieves.release();                  //avisa a Blancanieves
		System.out.println ("Enano "+id+ " pide comida a Blancanieves");
		candado.release();
	}

	public void comer(int id, int numSilla) {
		try {
			comidaServida[numSilla].acquire();       //espera a que se le sirva la comida
		} catch (InterruptedException e) {}
		System.out.println ("Enano "+id+" esta comiendo");
	}
	public void dejarSilla(int id, int numSilla) {
		try {
			candado.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		sillas [numSilla]=0;                //libera la silla
		sillasLibres.release();             //avisa que queda libre
		candado.release();
	}


	public void darUnaVuelta() {
		System.out.println ("\t\t Blancanieves da una vuelta");
		try {
			avisoBlancanieves.acquire();   //espera a que alguien le avise
		} catch (InterruptedException e) {}
	}

	public int servirComida() {
		int numSilla;
		
		try {
			candado.acquire(); 
		} catch (InterruptedException e) {e.printStackTrace();}
		
		numSilla = buscarSillaPendiente() ;//busca a quien tiene que atender
		
		System.out.println("\t\tBlancanieves sirve comida a silla "+numSilla);
		sillasPendientes[numSilla]= false; //lo marca como servido
		
		candado.release();
		return numSilla;
	}
	public void indicarPuedeComer(int numSilla) {
		try {
			candado.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println("\t\tBlancanieves avisa a enano "+sillas[numSilla]+ " sentado en silla "+numSilla+ "que puede comer" );
		comidaServida[numSilla].release();         //avisa que puede empezar a comer
		
		candado.release();
	}

	private int buscarSillaPendiente() {
		boolean encontrada = false;
		int libre= -1;
		
		for (int i = 0; (i<NUMSILLAS)&&(!encontrada); i++){
			if (sillasPendientes[i]){
				encontrada = true;
				libre = i;
			}
		}
		
		return libre;
	}

	private int buscarSillaLibre(){
		boolean encontrada = false;
		int libre= -1;
	
		for (int i = 0; (i<NUMSILLAS)&&(!encontrada); i++){
			if (sillas[i]==0){
				encontrada = true;
				libre = i;
			}
		}
	
		return libre;
	}
	
}