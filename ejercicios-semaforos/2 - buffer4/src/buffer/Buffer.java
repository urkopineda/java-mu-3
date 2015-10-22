package buffer;

public class Buffer {

	static int MAX_ARRAY = 4;
	
	int count;
	int [] numeros;
	
	public Buffer() {
		count = 0;
		numeros = new int[MAX_ARRAY];
	}
	
	public void añadirValor(int num) {
		synchronized (this) {
			numeros[count] = num;
			count++;
			if (count == MAX_ARRAY) {
				System.out.print("[");
				for (int i = 0; i < MAX_ARRAY; i++) System.out.print(numeros[i]+" ");
				System.out.println("]");
				count = 0;
				numeros = new int[MAX_ARRAY];
			}
		}
	}
	
}
