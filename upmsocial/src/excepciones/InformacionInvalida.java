package excepciones;

@SuppressWarnings("serial")
public class InformacionInvalida extends Exception{
	public InformacionInvalida() {
		super("Invalid Data");
	}
	
	public InformacionInvalida(String msg) {
		super("Invalid Data: " + msg);
	}
}
