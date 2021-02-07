package exception;


//Aqui iran todas las excepciones que puedan haber
public class BlogException extends RuntimeException {
	private static final long serialVersionUID = -2950205365998448571L; //id autogenerado

	public BlogException() {  //esto es llamado por el constructor
		super();
	}
	
	public BlogException(String message) { //una exception con parametro
		super(message);
	}


	public BlogException(Throwable e) {
		super(e);
	}
}
