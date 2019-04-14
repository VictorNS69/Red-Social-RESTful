package interfaces;

import java.util.List;

import javax.ws.rs.core.*;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;


public interface OperacionesAPI {
	public Response responseGetUsuarios(String filtro);
	public Response responseCrearUsuario(Usuario usuario);
	public Response borrarUsuario(String id);
	public Response getAmigos(String id, 
			String filterBy, String start, String end);
	public Response nuevoAmigo(Usuario usuario, Usuario amigo);
	public Response borrarAmigo(Usuario usuario,Usuario amigo);
	public Response getMensajesMuro(List<MensajeMuro>  mensajes);
	public void publicarMensajeMuro(Usuario usuario, MensajeMuro msj);
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
	public Response responseInfoUsuario(String id);
}
