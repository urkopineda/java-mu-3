package lectoresescritores;

public class Escritor extends Thread {
	int id;
	private Buffer buffer;
	
	public Escritor (int id, Buffer buffer){
		this.id = id;
		this.buffer = buffer;
	}
	@Override
	public void run() {
		
		buffer.añadir(id);
	}
}
