package datos;

import java.util.List;

public class Usuario {
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String pais;
	private String email;
	
	public Usuario(int id, String nombre, String apellido1, String apellido2, 
			int telefono, String email, String pais) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.pais = pais;
		this.telefono = telefono;
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
	
	public int getTelefono(){
		return this.telefono;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPais(){
		return this.pais;
	}
		
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido1(String apellido) {
		this.apellido1 = apellido;
	}
	
	public void setApellido2(String apellido) {
		this.apellido2 = apellido;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String toString() {
		return this.id + " "+ this.nombre + " " + this.apellido1;
	}
}












