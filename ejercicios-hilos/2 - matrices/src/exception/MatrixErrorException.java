package exception;

/**
 * Clase para lanzar una excepción cuando se intentan multiplicar dos matrices 
 * que no son válidas
 * 
 * @author urko
 *
 */
public class MatrixErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lanza una excepción cuando se intentan multiplicar dos matrices
	 * que no son válidas para multiplicar (Columnas de A != Filas de B)
	 * 
	 * @param mensajeError
	 * 
	 */
	public MatrixErrorException(String mensajeError) {
		super(mensajeError);
	}

}
