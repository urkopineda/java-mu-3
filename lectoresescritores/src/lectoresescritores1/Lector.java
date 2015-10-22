package lectoresescritores1;

public class Lector extends Thread {
	int id;
	private Buffer buffer;
	
	public Lector(int id, Buffer buffer){
		this.id = id;
		this.buffer = buffer;
	}
	@Override
	public void run() {
		
		try {
			buffer.mostrar(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
