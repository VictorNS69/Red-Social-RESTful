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
}
