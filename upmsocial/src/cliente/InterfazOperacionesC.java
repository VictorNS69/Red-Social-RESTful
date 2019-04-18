package cliente;

import javax.ws.rs.core.*;

import datos.*;

public interface InterfazOperacionesC {
	/** Lista los usuarios en el sistema
	 * @return Response
	 */
	public Response listaUsuarios();
	
	/** Añade un nuevo usuario
	 * 
	 * @param usuario: el nuevo usuario
	 * @return Response
	 */
	public Response anadirUsuario(Usuario usuario);
	
	/** Devuelve la información de un usuario
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response infoUsuario(int id);
	
	/** Modifica la información de un usuario
	 * @param id: id del usuario
	 * @param usuario: el usuario modificado
	 * @return Response
	 */
	public Response modificaUsuario(int id, Usuario usuario);
	
	/** Elimina un usuario del sistema
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response eliminaUsuario(int id);
	
	/** Lista los amigos de un usuario
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response amigosUsuario(int id);
	
	/** Añade un nuevo amigo a un usuario concreto
	 * @param idU: id del usuario
	 * @param idA: id del amigo
	 * @return Response
	 */
	public Response anadirAmigoUsuario(int idU, int idA);
	
	/** Borra a un amigo de un usuario
	 * @param idU: id del usuario
	 * @param idA: id del amigo
	 * @return Response
	 */
	public Response borrarAmigoUsuario(int idU, int idA);
	
	/** Lista los mensajes en el muro de un usuario
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response mensajesMuroUsuario(int id);

	/** Obtiene un mensaje de muro concreto
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @return Response
	 */
	public Response mensajeMuroUsuario(int idU, int idM);
	
	/** Publica un nuevo mensaje en el muro
	 * @param id: id del usuario
	 * @param msg: mensaje a publicar
	 * @return Response
	 */
	public Response publicarMensajeMuro(int id, String msg);
	
	/** Edita un mensaje de muro
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @param msg: cuerpo del mensaje editado
	 * @return Response
	 */
	public Response editarMensajeMuro(int idU, int idM, String msg);
	
	/** Borra un mensaje de muro
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @return Response
	 */
	public Response borrarMensajeMuro(int idU, int idM);
	
	/** Lista los mensajes privados de un usuario
	 * @param id
	 * @return
	 */
	public Response mensajesPrivadosUsuario(int id);
	
	/** Envia un mensaje privado
	 * @param idO: id del usuario origen
	 * @param idD: id del usuario destino
	 * @param msg: cuerpo del mensaje
	 * @return
	 */
	public Response enviarMensaje(int idO, int idD, String msg);
	
	/** Obtiene un mensaje privado concreto
	 * @param idU: id del usuario
	 * @param idM: id del mensaje
	 * @return Response
	 */
	public Response mensajePrivado(int idU, int idM);
	
	/** Lista los mensajes en el muro de los amigos de
	 * un usuario
	 * @param id: id del usuario
	 * @return Response
	 */
	public Response muroAmigos(int id);
}
