package interfaces;

import java.sql.SQLException;
import java.util.List;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;

public interface OperacionesUsuario {
	public List<Usuario> getUsuarios() throws SQLException;
	public void crearUsuario(int id, String nombre, String apellido1, String apellido2, 
			long telefono, String email, String pais);
	public void infoUsuario(Usuario usuario); 
	public void borrarUsuario(Usuario usuario);
	public List<Usuario> getAmigos(Usuario usuario);
	public void nuevoAmigo(Usuario usuario, Usuario amigo);
	public void borrarAmigo(Usuario usuario,Usuario amigo);
	public List<MensajeMuro> getMensajesMuro(Usuario usuario);
	public void publicarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public MensajeMuro getMensajeMuro(Usuario usuario, MensajeMuro msj);
	public void editarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public void borrarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public List<MensajePrivado> getMensajesPrivados(Usuario usuario);
	public void enviarMensajePrivado(Usuario origen, Usuario destino, MensajePrivado msj);
	public MensajePrivado getMensajePrivado(Usuario usuario, MensajePrivado msj);
	public void borrarMensajePrivado(Usuario usuario, MensajePrivado msj);
}
