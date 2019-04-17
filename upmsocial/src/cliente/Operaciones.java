package cliente;

import java.util.Scanner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import datos.*;

public class Operaciones implements InterfazOperacionesC{

	private static String GET = "Metodo: GET";
	private static String POST = "Metodo: POST";
	private static String PUT = "Metodo: PUT";
	private static String DELETE = "Metodo: DELETE";
	
	private WebTarget target = null;
	
	public Operaciones(WebTarget target) {
		this.target = target;
	}
	
	@Override
	public Response listaUsuarios() {
		System.out.println("Elige un filtro para el nombre. \n(Si quieres omitir pulsa <enter>)");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String filter = in.nextLine();
		System.out.println(target.path("usuarios"));
		System.out.println(GET);
		return target.path("usuarios").queryParam("filterBy", filter)
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

	@Override
	public Response amigosUsuario(int id) {
		System.out.println("Elige un filtro para el nombre. \n(Si quieres omitir pulsa <enter>)");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String filter = in.nextLine();
		System.out.println("Elige inicio. \n(Si quieres omitir pulsa <enter>)");
		String start = in.nextLine();
		System.out.println("Elige final. \n(Si quieres omitir pulsa <enter>)");
		String end = in.nextLine();
		try {
			if (!start.isEmpty())
				Integer.valueOf(start);
			if (!end.isEmpty())
				Integer.valueOf(end);
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		}
		System.out.println(target.path("usuarios/" + id + "/amigos"));
		System.out.println(GET);
		return target.path("usuarios/" + id + "/amigos").queryParam("filterBy", filter)
				.queryParam("start", start).queryParam("end", end)
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

	@Override
	public Response borrarAmigoUsuario(int idU, int idA) {
		System.out.println(target.path("usuarios/" + idU + "/amigos/" + idA));
		System.out.println(DELETE);
		return target.path("usuarios/" + idU + "/amigos/" + idA)
				.request().accept(MediaType.APPLICATION_JSON)
				.delete(Response.class);
	}

	@Override
	public Response mensajesMuroUsuario(int id) {
		System.out.println("Elige un filtro para el cuerpo del mensaje. \n(Si quieres omitir pulsa <enter>)");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String filter = in.nextLine();
		System.out.println(target.path("usuarios/" + id + "/muro_personal"));
		System.out.println(GET);
		return target.path("usuarios/" + id + "/muro_personal")
				.queryParam("filterBy", filter)
				.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	}

	@Override
	public Response mensajeMuroUsuario(int idU, int idM) {
		System.out.println(target.path("usuarios/" + idU + "/muro_personal/" + idM));
		System.out.println(GET);
		return target.path("usuarios/" + idU + "/muro_personal/" + idM)
				.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	}

	@Override
	public Response publicarMensajeMuro(int id, String msg) {
		System.out.println(target.path("usuarios/" + id + "/amigos"));
		System.out.println(POST);
		String format = "{\"cuerpo\":  \""+ msg + "\" }";
		return target.path("usuarios/" + id + "/muro_personal")
				.request().accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(format));
	}
}
