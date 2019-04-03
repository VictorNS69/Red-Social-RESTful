package ops;

import datos.*;
import java.util.List;

public interface Interfaz {
	public List<Usuario> getUsuarios();
	public void createUsuario(int id, String nombre, String apellido1, String apellido2, 
			long telefono, String email, String pais);
	public void infoUsuario(Usuario usuario); //no se muy bien como va este
	public void deleteUsuario(Usuario usuario);
	public List<Usuario> getAmigos(Usuario usuario);
	public void newAmigo(Usuario usuario, Usuario amigo);
	public void deleteAmigo(Usuario usuario,Usuario amigo);
	public List<MensajeMuro> getMessages(Usuario usuario);
	public void publish(Usuario usuario, MensajeMuro msj);
	public MensajeMuro showMessage(Usuario usuario, MensajeMuro msj);
	public void editMessage(Usuario usuario, MensajeMuro msj);
	public void deleteMessage(Usuario usuario, MensajeMuro msj);
	public List<MensajePrivado> getPrivates(Usuario usuario);
	public void sendPrivate(Usuario origen, Usuario destino, MensajePrivado msj);
	public MensajePrivado getPrivate(Usuario usuario, MensajePrivado msj);
	public void deletePrivate(Usuario usuario, MensajePrivado msj);
	
}
