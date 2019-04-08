package datos;

import java.sql.Date;

public class MensajeMuro {
	private int id;
	private int idUsuario;
	private String cuerpo;
	private Date fecha;
	
	public MensajeMuro(int id, int idUsuario, String cuerpo, Date fecha) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String toString() {
		return this.id + " " + this.idUsuario + " " + this.cuerpo + " " + this.fecha;
	}
}
