package operacionesAPI;


import java.sql.SQLException;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;
import interfaces.OperacionesAPI;
import operacionesBackend.OperacionesB;

@Path("/")
public class Operaciones implements OperacionesAPI{
	
	@Context
	private UriInfo uriInfo;
	
    public Operaciones() {}
    
    @Path("/usuarios")
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response responseGetUsuarios() {
		String json = null;
		List <Usuario> lista;
		OperacionesB ops = new OperacionesB();
		try {
			lista = ops.getUsuarios();
		} catch (Exception e) {
			json = new Gson().toJson(e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("500 Internal Server Error").build();
		}
		if (lista.isEmpty()) 
			return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();
		else {
			json = new Gson().toJson(lista);
			return Response.status(Response.Status.OK).entity(json).build();
		}
	}


	@Override
	public void publicarMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MensajeMuro getMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editarMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MensajePrivado> getMensajesPrivados(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enviarMensajePrivado(Usuario origen, Usuario destino, MensajePrivado msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MensajePrivado getMensajePrivado(Usuario usuario, MensajePrivado msj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarMensajePrivado(Usuario usuario, MensajePrivado msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Response responseCrearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@GET
	@Path("/usuarios/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseInfoUsuario(@PathParam("id") String id) {
		String json = null;
		Usuario thisUsuario;
		OperacionesB ops = new OperacionesB();
		int thisId = Integer.parseInt(id);
		try {
			thisUsuario = ops.infoUsuario(thisId);
		} catch (SQLException e) {
			json = new Gson().toJson(e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("500 Internal Server Error").build();
		}
		if (thisUsuario == null)
			return Response.status(Response.Status.NOT_FOUND).entity("404 Not Found").build();
		else
			json = new Gson().toJson(thisUsuario);
			return Response.status(Response.Status.OK).entity(json).build();
	}

	@Override
	public Response borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAmigos(List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response nuevoAmigo(Usuario usuario, Usuario amigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response borrarAmigo(Usuario usuario, Usuario amigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getMensajesMuro(List<MensajeMuro> mensajes) {
		// TODO Auto-generated method stub
		return null;
	}

}
