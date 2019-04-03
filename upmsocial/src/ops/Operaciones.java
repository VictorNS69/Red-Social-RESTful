package ops;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import datos.*;

public class Operaciones {
	
	@Path("/usuarios")
	public class UserOps implements Interfaz {

		@Override
		@GET
		@Produces(MediaType.TEXT_XML)
		public List<Usuario> getUsuarios() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void createUsuario(int id, String nombre, String apellido1, String apellido2, long telefono,
				String email, String pais) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void infoUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Usuario> getAmigos(Usuario usuario) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void newAmigo(Usuario usuario, Usuario amigo) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAmigo(Usuario usuario, Usuario amigo) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<MensajeMuro> getMessages(Usuario usuario) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void publish(Usuario usuario, MensajeMuro msj) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public MensajeMuro showMessage(Usuario usuario, MensajeMuro msj) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void editMessage(Usuario usuario, MensajeMuro msj) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteMessage(Usuario usuario, MensajeMuro msj) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<MensajePrivado> getPrivates(Usuario usuario) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void sendPrivate(Usuario origen, Usuario destino, MensajePrivado msj) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public MensajePrivado getPrivate(Usuario usuario, MensajePrivado msj) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void deletePrivate(Usuario usuario, MensajePrivado msj) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
}
