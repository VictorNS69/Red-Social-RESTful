package interfaces;

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
	
	/** Publica un nuevo mensaje en el muro
	 * @param id: id del usuario
	 * @param msj: mensaje a publicar
	 * @return
	 */
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
	 * @return Response
	 */
	public Response responseEditarMensajeMuro(String idU, String idM, MensajeMuro msj);
	
	/** Borra un mensaje en el muro
	 * @param idM: id del mensaje
	 * @param idU: id del usuario
	 * @return Response
	 */
	public Response responseBorrarMensajeMuro(String idM, String idU);
	
	/** Lista los mensajes privados
	 * @param filterBy: filtro opcional para filtrar el cuerpo
	 * del mensaje
	 * @param id: id del mensaje
	 * @return Response
	 */
	public Response responseGetMensajesPrivados(String filterBy, String id);
	
	/** Envia un mensaje privado
	 * @param origen: id origen
	 * @param msj: mensaje a enviar
	 * @return Response
	 */
	public Response responseEnviarMensajePrivado(String origen, MensajePrivado msj);
	
	/** Obtiene un mensaje privado concreto
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @return Response
	 */
	public Response responseGetMensajePrivado(String idU, String idM);
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

	/** Lista la información de un usuario para un dispositivo
	 * movil
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response responseInfoMovil(String id);
}
