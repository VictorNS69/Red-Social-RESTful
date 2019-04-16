package interfaces;

import java.util.List;

import javax.ws.rs.core.*;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;


public interface OperacionesAPI {
	/** Obtiene los usuarios del sistema
	 * @param filtro: filtro opcional para filtrar por 
	 * nombre
	 * @return Response
	 */
	public Response responseGetUsuarios(String filtro);
	
	/** Añade un usuario al sistema
	 * @param usuario: usuario a añadir
	 * @return Response
	 */
	public Response responseCrearUsuario(Usuario usuario);
	
	/** Borra a un usuario del sistema
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response borrarUsuario(String id);
	
	/** Edita a un usuario
	 * @param id: id del usuario
	 * @param usuario: usuario modificado
	 * @return Response
	 */
	public Response editarUsuario(String id, Usuario usuario);
	
	/** Lista los amigos de un usuario
	 * @param id: id del usuario
	 * @param filterBy: filtro opcional para filtrar por nombre
	 * @param start: filtro opcional para filtar desde el
	 * amigo 'start'
	 * @param end: filtro opcional para filtrar hasta el amigo
	 * 'end'
	 * @return Response
	 */
	public Response getAmigos(String id, String filterBy, 
			String start, String end);
	
	/** Añade un nuevo amigo a un usuario concreto
	 * @param idU: id del usuario
	 * @param idA: id del amigo
	 * @return Response
	 */
	public Response nuevoAmigo(String idU, String idA);
	
	/** Borra a un amigo de un usuario
	 * @param idU: id del usuario
	 * @param idA: id del amigo
	 * @return Response
	 */
	public Response borrarAmigo(String idU, String idA);
	
	/** Lista los mensajes en el muro de un usuario
	 * @param filterBy: filtro opcional para filtrar por
	 * el cuerpo del mensaje
	 * @param id: id del usuario
	 * @return
	 */
	public Response getMensajesMuro(String filterBy, String id);
	
	
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
	
	/** Devuelve la información de un usuario
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response responseInfoUsuario(String id);
	
	public Response getMensajesMuroAmigos(String filterBy, String id);

}
