package datos;

public class Usuario {
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private long telefono;
	private String pais;
	private String email;

	public Usuario(int id, String nombre, String apellido1, String apellido2, long telefono, String email, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.pais = pais;
	}
	
	public int getId(){
		return this.id;
	}
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido1(){
		return this.apellido1;
	}
	
	public String getApellido2(){
		return this.apellido2;
	}
	
	public long getTelefono(){
		return this.telefono;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPais(){
		return this.pais;
	}
	
}











