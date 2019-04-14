package interfaces;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;
import excepciones.InformacionInvalida;

public interface OperacionesUsuario {
	public List<Usuario> getUsuarios() throws SQLException;
	public Usuario crearUsuario(String nombre, String apellido1, 
			String apellido2, 
			int telefono, String email, String pais) throws 
	SQLException, InformacionInvalida;
	public Usuario infoUsuario(int id) throws SQLException; 
	public boolean borrarUsuario(String id) throws SQLException;
	public List<Usuario> getAmigos(String id, String filterBy, 
			String start, String end) throws SQLException;
	public void nuevoAmigo(int idU, int idA) throws SQLException;
	public void borrarAmigo(int idU, int idA) throws SQLException;
	public List<MensajeMuro> getMensajesMuro(int id) throws SQLException;
	public void publicarMensajeMuro(int idU, int idMsj, String cuerpo, 
			Date fecha) throws SQLException;
	public MensajeMuro getMensajeMuro(Usuario usuario, MensajeMuro msj);
	public void editarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public void borrarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public List<MensajePrivado> getMensajesPrivados(Usuario usuario);
	public void enviarMensajePrivado(Usuario origen, Usuario destino, 
			MensajePrivado msj);
	public MensajePrivado getMensajePrivado(Usuario usuario, 
			MensajePrivado msj);
	public void borrarMensajePrivado(Usuario usuario, 
			MensajePrivado msj);
}
