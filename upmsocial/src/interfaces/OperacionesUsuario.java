package interfaces;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;
import excepciones.InformacionInvalida;

public interface OperacionesUsuario {
	/** Obtiene una lista de los usuarios en la base de 
	 * datos
	 * @param filter: filtro opcional para buscar por nombre
	 * @return List: lista de los usuarios
	 * @throws SQLException
	 */
	public List<Usuario> getUsuarios(String filter) throws SQLException;

	/** Crea un nuevo usuario y lo añade al sistema
	 * @param nombre: nombre del usuario
	 * @param apellido1: primer apellido del usuario
	 * @param apellido2: segundo apellido del usuario
	 * @param telefono: telefono del usuario
	 * @param email: email del usuario
	 * @param pais: pais del usuario
	 * @return Usuario: el nuevo usuario
	 * @throws SQLException
	 * @throws InformacionInvalida
	 */
	public Usuario crearUsuario(String nombre, String apellido1, 
			String apellido2, int telefono, String email, 
			String pais) throws	SQLException, InformacionInvalida;
	
	/** Muestra la información de un usuario concreto
	 * @param id: id del usuario
	 * @return Usuario: el objeto usuario concreto
	 * @throws SQLException
	 */
	public Usuario infoUsuario(int id) throws SQLException; 
	
	/** Borra a un usuario del sistema
	 * @param id: id del usuario
	 * @return boolean: devuelve si ha habido éxito o no
	 * @throws SQLException
	 */
	public boolean borrarUsuario(String id) throws SQLException;
	
	/** Edita la información de un usuario
	 * @param id: id del usuario
	 * @param usuario: el usuario modificado
	 * @return Usuario: devuelve el usuario ya modificado
	 * @throws SQLException
	 * @throws InformacionInvalida
	 */
	public Usuario editarUsuario(String id, Usuario usuario) 
			throws SQLException, InformacionInvalida;
	
	/** Lista los amigos de un usuario
	 * @param id: id del usuario 
	 * @param filterBy: filtro opcional para filtrar por
	 * nombre
	 * @param start: filtro opcional para iniciar por el
	 * amigo 'start'
	 * @param end: filtro opcional para terminar a los
	 * 'end' amigos
	 * @return List: lista de los usuarios que son amigos
	 * @throws SQLException
	 */
	public List<Usuario> getAmigos(String id, String filterBy, 
			String start, String end) throws SQLException;
	
	/** Crea una relación de amistad
	 * @param idU: id del usuario concreto
	 * @param idA: id del amigo que se quiere "hacer amigo"
	 * @throws SQLException
	 * @throws InformacionInvalida
	 */
	public void nuevoAmigo(String idU, String idA) throws 
		SQLException, InformacionInvalida;
	
	/** Borra a un amigo de un usuaro
	 * @param idU: id del usuario 
	 * @param idA: id del amigo
	 * @return boolean: dice si ha habido éxito en la operación
	 * @throws SQLException
	 */
	public boolean borrarAmigo(String idU, String idA) throws SQLException;
	
	/** Muestra los mensajes en el muro de un usuario
	 * @param id: id del usuario
	 * @param filter: filtro opcional para el cuerpo del mensaje
	 * @return List: lista de los mensajes en el muro
	 * @throws SQLException
	 */
	public List<MensajeMuro> getMensajesMuro(String id, String filter) 
			throws SQLException;
	
	public void publicarMensajeMuro(int idU, int idMsj, String cuerpo, 
			Date fecha) throws SQLException, InformacionInvalida;
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
