package interfaces;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.*;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;


public interface OperacionesAPI {
	public Response responseGetUsuarios() throws SQLException, NamingException;
	public Response responseCrearUsuario(Usuario usuario);
	public Response responseInfoUsuario(Usuario usuario); 
	public Response borrarUsuario(Usuario usuario);
	public Response getAmigos(List<Usuario> usuarios);
	public Response nuevoAmigo(Usuario usuario, Usuario amigo);
	public Response borrarAmigo(Usuario usuario,Usuario amigo);
	public Response getMensajesMuro(List<MensajeMuro>  mensajes);
	//TODO
	public void publicarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public MensajeMuro getMensajeMuro(Usuario usuario, MensajeMuro msj);
	public void editarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public void borrarMensajeMuro(Usuario usuario, MensajeMuro msj);
	public List<MensajePrivado> getMensajesPrivados(Usuario usuario);
	public void enviarMensajePrivado(Usuario origen, Usuario destino, MensajePrivado msj);
	public MensajePrivado getMensajePrivado(Usuario usuario, MensajePrivado msj);
	public void borrarMensajePrivado(Usuario usuario, MensajePrivado msj);
}
