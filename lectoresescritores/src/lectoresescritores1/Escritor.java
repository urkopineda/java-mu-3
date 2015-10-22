package lectoresescritores1;

public class Escritor extends Thread {
	int id;
	private Buffer buffer;
	
	public Escritor (int id, Buffer buffer){
		this.id = id;
		this.buffer = buffer;
	}
	@Override
	public void run() {
		
		try {
			buffer.añadir(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
