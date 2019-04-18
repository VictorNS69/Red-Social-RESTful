package datos;

import java.sql.Date;

public class MensajePrivado {
	private int id;
	private int idOrigen;
	private int idDestino;
	private String cuerpo;
	private Date fecha;
	
	public MensajePrivado() {}
	
	public MensajePrivado(int destino, String cuerpo) {
		this.idDestino = destino;
		this.cuerpo = cuerpo;
	}
	
	public MensajePrivado(int id, int idDestino, 
			int idOrigen, String cuerpo,
			Date fecha) {
		this.id = id;
		this.idDestino = idDestino;
		this.idOrigen = idOrigen;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}
	
	public MensajePrivado(int id, int idDestino, 
			int idOrigen, String cuerpo) {
		this.id = id;
		this.idDestino = idDestino;
		this.idOrigen = idOrigen;
		this.cuerpo = cuerpo;
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
	
	public void setId(int id) {
		this.id = id;
	}

	public void setIdOrigen(int id) {
		this.idOrigen = id;
	}

	public void setIdDestino(int id) {
		this.idDestino = id;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
