package lectoresescritores3;

public class Principal {
	final int MAXLECTORES = 10;
	final int MAXESCRITORES = 10;
	Buffer buffer;
	Lector lectores[];
	Escritor escritores [];
	
	public Principal(){
		buffer = new Buffer();
		lectores = new Lector [MAXLECTORES];
		escritores = new Escritor [MAXESCRITORES];
	}
	public void crearHilos(){
		for (int i= 0; i<MAXLECTORES; i++){
			lectores [i] = new Lector(i,buffer);
		}
		for (int i= 0; i<MAXESCRITORES; i++){
			escritores [i] = new Escritor(i,buffer);
		}
	}
	public void inciarHilos(){
		
		for (int i= 0; i<MAXLECTORES; i++){
			lectores [i].start(); 
			escritores [i].start(); 
		}
		
	}
	public void esperarHilos() throws InterruptedException{
		for (int i= 0; i<MAXLECTORES; i++){
			lectores [i].join(); 
		}
		for (int i= 0; i<MAXESCRITORES; i++){
			escritores [i].join();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Principal ejercicio = new Principal();
		ejercicio.crearHilos();
		ejercicio.inciarHilos();
		ejercicio.esperarHilos();
	}

}
