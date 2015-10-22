package lectoresescritores;

public class Lector extends Thread {
	int id;
	private Buffer buffer;
	
	public Lector(int id, Buffer buffer){
		this.id = id;
		this.buffer = buffer;
	}
	@Override
	public void run() {
		
		buffer.mostrar(id);
	}

}
