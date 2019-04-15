package cliente;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import datos.*;

public class Operaciones implements OperacionesC{

	private static String GET = "Metodo: GET";
	private static String POST = "Metodo: POST";
	private static String PUT = "Metodo: PUT";
	private static String DELETE = "Metodo: DELETE";
	
	private WebTarget target = null;
	
	public Operaciones(WebTarget target) {
		this.target = target;
	}
	
	// TODO: hacer el filtro
	@Override
	public Response listaUsuarios() {
		System.out.println(target.path("usuarios"));
		System.out.println(GET);
		return target.path("usuarios")
				.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	}

	@Override
	public Response anadirUsuario(Usuario usuario) {
		System.out.println(target.path("usuarios"));
		System.out.println(POST);
		String json = new Gson().toJson(usuario);
		return target.path("usuarios")
		        .request()
		        .post(Entity.json(json));
	}

	@Override
	public Response infoUsuario(int id) {
		System.out.println(target.path("usuarios/" + id));
		System.out.println(GET);
		return target.path("usuarios/" + id)
				.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	}

	@Override
	public Response modificaUsuario(int id, Usuario usuario) {
		System.out.println(target.path("usuarios/" + id));
		System.out.println(PUT);
		String json = new Gson().toJson(usuario);
		return target.path("usuarios/" + id)
				.request().put(Entity.json(json));
	}

	@Override
	public Response eliminaUsuario(int id) {
		System.out.println(target.path("usuarios/" + id));
		System.out.println(DELETE);
		return target.path("usuarios/" + id)
				.request().accept(MediaType.APPLICATION_JSON)
				.delete(Response.class);
	}

	// TODO: Añadir los posibles filtros
	@Override
	public Response amigosUsuario(int id) {
		System.out.println(target.path("usuarios/" + id + "/amigos"));
		System.out.println(GET);
		return target.path("usuarios/" + id + "/amigos")
				.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	}

	@Override
	public Response anadirAmigoUsuario(int idU, int idA) {
		System.out.println(target.path("usuarios/" + idU + "/amigos"));
		System.out.println(POST);
		String json = new Gson().toJson(idA);
		return target.path("usuarios/" + idU + "/amigos")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(json));
	}

	// TODO: testear bien. Que se pueda borrar 1-2 y 2-1
	@Override
	public Response borrarAmigoUsuario(int idU, int idA) {
		System.out.println(target.path("usuarios/" + idU + "/amigos/" + idA));
		System.out.println(DELETE);
		return target.path("usuarios/" + idU + "/amigos/" + idA)
				.request().accept(MediaType.APPLICATION_JSON)
				.delete(Response.class);
	}

	// TODO: Añadir los posibles filtros
	@Override
	public Response mensajesMuroUsuario(int id) {
		System.out.println(target.path("usuarios/" + id + "/muro_personal"));
		System.out.println(GET);
		return target.path("usuarios/" + id + "/muro_personal")
				.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	}

}
