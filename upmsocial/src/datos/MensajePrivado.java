package datos;

import java.sql.Date;

public class MensajePrivado {
	private int id;
	private int idOrigen;
	private int idDestino;
	private String cuerpo;
	private Date fecha;
	
	public MensajePrivado() {
		this.id = id;
		this.idDestino = idDestino;
		this.idOrigen = idOrigen;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public int getIdOrigen() {
		return idOrigen;
	}

	public int getIdDestino() {
		return idDestino;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public Date getFecha() {
		return fecha;
	}
}
