package datos;

import java.util.List;

public class InfoMovil {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String pais;
	private String email;
	private int numAmigos;
	private List<MensajeMuro> ultimosMsj;
	
	public InfoMovil(String nombre, String apellido1, String apellido2, int telefono, String email,
			String pais, int numAmigos, List<MensajeMuro> ultimosMsj) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.pais = pais;
		this.numAmigos = numAmigos;
		this.ultimosMsj = ultimosMsj;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getPais() {
		return pais;
	}

	public String getEmail() {
		return email;
	}

	public int getNumAmigos() {
		return numAmigos;
	}

	public List<MensajeMuro> getUltimosMsj() {
		return ultimosMsj;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumAmigos(int numAmigos) {
		this.numAmigos = numAmigos;
	}

	public void setUltimosMsj(List<MensajeMuro> ultimosMsj) {
		this.ultimosMsj = ultimosMsj;
	}
	
}
