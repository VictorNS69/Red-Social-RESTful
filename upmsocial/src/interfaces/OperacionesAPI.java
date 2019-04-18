package interfaces;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
	public Response responseBorrarUsuario(String id);
	
	/** Edita a un usuario
	 * @param id: id del usuario
	 * @param usuario: usuario modificado	
	 * @return Response
	 */
	public Response responseEditarUsuario(String id, Usuario usuario);
	
	/** Lista los amigos de un usuario
	 * @param id: id del usuario
	 * @param filterBy: filtro opcional para filtrar por nombre
	 * @param start: filtro opcional para filtar desde el
	 * amigo 'start'
	 * @param end: filtro opcional para filtrar hasta el amigo
	 * 'end'
	 * @return Response
	 */
	public Response responseGetAmigos(String id, String filterBy, 
			String start, String end);
	
	/** Añade un nuevo amigo a un usuario concreto
	 * @param idU: id del usuario
	 * @param idA: id del amigo
	 * @return Response
	 */
	public Response responseNuevoAmigo(String idU, String idA);
	
	/** Borra a un amigo de un usuario
	 * @param idU: id del usuario
	 * @param idA: id del amigo
	 * @return Response
	 */
	public Response responseBorrarAmigo(String idU, String idA);
	
	/** Lista los mensajes en el muro de un usuario
	 * @param filterBy: filtro opcional para filtrar por
	 * el cuerpo del mensaje
	 * @param id: id del usuario
	 * @return
	 */
	public Response responseGetMensajesMuro(String filterBy, String id);
	
	
	public Response responsePublicarMensajeMuro(String id, MensajeMuro msj);
	
	/** Devuelve un mensaje de muro concreto
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @return Response
	 */
	public Response responseGetMensajeMuro(String idU, String idM);
	
	/** Edita un mensaje de muro existente
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @param cuerpo: cuerpo del mensaje
	 * @return
	 */
	public Response responseEditarMensajeMuro(String idU, String idM, String cuerpo);
	
	public Response responseBorrarMensajeMuro(String idM, String idU);
	
	public Response responseGetMensajesPrivados(String filterBy, String id);
	
	public Response responseEnviarMensajePrivado(String origen, MensajePrivado msj);
	
	public MensajePrivado responseGetMensajePrivado(Usuario usuario, 
			MensajePrivado msj);
	
	/** Devuelve la información de un usuario
	 * @param idU: id del usuario
	 * @return Response
	 */
	public Response responseInfoUsuario(String idU);
	
	/** Obtiene una lista de mensajes de los amigos de un usuario
	 * @param id: id del usuario
	 * @param filterBy: filtro opcional para filtrar el cuerpo del
	 * mensjae
	 * @return Response
	 */
	public Response responseGetMensajesMuroAmigos(String id, String filterBy);

}
