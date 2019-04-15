package cliente;

import javax.ws.rs.core.*;

import datos.*;

public interface OperacionesC {
	public Response listaUsuarios();
	public Response anadirUsuario(Usuario usuario);
	public Response infoUsuario(int id);
	public Response modificaUsuario(int id, Usuario usuario);
	public Response eliminaUsuario(int id);
	public Response amigosUsuario(int id);
	public Response anadirAmigoUsuario(int idU, int idA);
}
