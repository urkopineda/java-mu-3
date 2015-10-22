package buffer;

public class Buffer {
	
	Object oPro;
	Object oCon;
	
	int [] buffer;
	int contador;
	int tamaño;

	public Buffer(int tamaño) {
		oPro = new Object();
		oCon = new Object();
		buffer = new int[tamaño];
		this.tamaño = tamaño;
		contador = 0;
	}
	
	public void put(int valor) throws InterruptedException {
		synchronized (oPro) {
			while (contador == tamaño){
				oPro.wait();
			}
			synchronized (this) {
				buffer[contador++] = valor;
			}
		}
		synchronized (oCon) {
			oCon.notify();
		}
	}
	
	public int get() {
		int valor = 0;
		synchronized (oCon) {
			while (contador == 0) {
				
			}
			synchronized (this) {
				valor = buffer[contador--];
			}
		}
		synchronized (oPro) {
			oPro.notify();
		}
		return valor;
	}
	
}
