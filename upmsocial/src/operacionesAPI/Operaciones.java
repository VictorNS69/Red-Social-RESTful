package operacionesAPI;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import datos.InfoMovil;
import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;
import excepciones.InformacionInvalida;
import interfaces.OperacionesAPI;
import operacionesBackend.OperacionesB;

@Path("/")
public class Operaciones implements OperacionesAPI{
	private static final String INTERNAL_SERVER_ERROR = "500 Internal Server Error";
	private static final String NOT_FOUND_ERROR = "404 Not Found";
	private static final String NOT_ACCEPTABLE_ERROR = "406 Not Acceptable";
	private static final String OK_MESSAGE = "2OO OK";

	@Context
	private UriInfo uriInfo;

	public Operaciones() {}

	@Path("/usuarios")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetUsuarios(@QueryParam("filterBy") @DefaultValue("") String filterBy) {
		List <Usuario> lista;
		OperacionesB ops = new OperacionesB();
		try {
			lista = ops.getUsuarios(filterBy);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		if (lista.isEmpty()) 
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		else {
			String aux = "";
			List <String> json = new ArrayList <String>();
			for (Usuario usuario : lista) {
				String location = uriInfo.getAbsolutePath() + "/" + usuario.getId();
				aux = (new Gson().toJson(usuario).replace("}",
						", \"location\": \"" + location + "\"}"));
				json.add(aux);
			}
			return Response.status(Response.Status.OK).
					entity(json.toString()).build();
		}
	}

	@POST
	@Path("/usuarios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseCrearUsuario(Usuario usuario) {
		OperacionesB ops = new OperacionesB();
		Usuario thisUsuario = null;
		try {
			thisUsuario = ops.crearUsuario(usuario.getNombre(), usuario.getApellido1(),
					usuario.getApellido2(), usuario.getTelefono(), usuario.getEmail(), 
					usuario.getPais());
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		} catch (InformacionInvalida e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).
					entity(NOT_ACCEPTABLE_ERROR).build();
		}
		String location = uriInfo.getAbsolutePath() + "/" + thisUsuario.getId();
		return Response.status(Response.Status.CREATED).entity(location).
				header("Location", location).
				header("Content-Location", location).build();
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
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		if (thisUsuario == null)
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		else
			json = new Gson().toJson(thisUsuario);
		return Response.status(Response.Status.OK).entity(json).build();
	}

	@DELETE
	@Path("/usuarios/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response responseBorrarUsuario(@PathParam("id") String id) {
		OperacionesB ops = new OperacionesB();
		try {
			if (!ops.borrarUsuario(id))
				return Response.status(Response.Status.NOT_FOUND).
						entity(NOT_FOUND_ERROR).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity(OK_MESSAGE).build();
	}

	@PUT
	@Path("/usuarios/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseEditarUsuario(@PathParam("id") String id, Usuario usuario) {
		OperacionesB ops = new OperacionesB();
		Usuario thisUsuario = null;
		try {
			thisUsuario = ops.editarUsuario(id, usuario);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		} catch (InformacionInvalida e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).
					entity(NOT_ACCEPTABLE_ERROR).build();
		}catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		}
		String location = uriInfo.getAbsolutePath().toString();
		thisUsuario.setId(Integer.valueOf(id));
		String json = (new Gson().toJson(thisUsuario));
		json = json.replace("}", ", \"location\": \"" + location + "\"}");
		return Response.status(Response.Status.OK).entity(json).build();
	}

	@GET
	@Path("/usuarios/{id}/amigos")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetAmigos(@PathParam("id") String id, 
			@QueryParam("filterBy") @DefaultValue("") String filterBy,
			@QueryParam("start") @DefaultValue("0")String start,
			@QueryParam("end") @DefaultValue("100") String end) {
		OperacionesB ops = new OperacionesB();
		List <Usuario> lista;
		try {
			lista = ops.getAmigos(id, filterBy, start, end);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		if (lista.isEmpty()) 
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		else {
			String aux = "";
			List <String> json = new ArrayList <String>();
			for (Usuario usuario : lista) {
				String location = uriInfo.getBaseUri() + "usuarios/" + usuario.getId();
				aux = (new Gson().toJson(usuario).replace("}",
						", \"location\": \"" + location + "\"}"));
				json.add(aux);
			}
			return Response.status(Response.Status.OK).
					entity(json.toString()).build();
		}
	}

	@POST
	@Path("/usuarios/{id}/amigos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response responseNuevoAmigo(@PathParam("id") String idU, String idA) {
		OperacionesB ops = new OperacionesB();
		idA = idA.replaceAll("\\D+","");
		try {
			ops.nuevoAmigo(idU, idA);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		} catch (InformacionInvalida e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).
					entity(NOT_ACCEPTABLE_ERROR).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		}
		String location = "http://localhost:8080/upmsocial/usuarios/" + idA;
		return Response.status(Response.Status.CREATED).entity(location).
				header("Location", location).
				header("Content-Location", location).build();

	}

	@DELETE
	@Path("/usuarios/{idU}/amigos/{idA}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response responseBorrarAmigo(@PathParam("idU") String idU, @PathParam("idA") String idA) {
		OperacionesB ops = new OperacionesB();
		try {
			if (!ops.borrarAmigo(idU, idA))
				return Response.status(Response.Status.NOT_FOUND).
						entity(NOT_FOUND_ERROR).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity(OK_MESSAGE).build();
	}

	@Path("/usuarios/{id}/muro_personal")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetMensajesMuro(@QueryParam("filterBy") @DefaultValue("") String filterBy,
			@PathParam("id") String id) {
		List <MensajeMuro> lista;
		OperacionesB ops = new OperacionesB();
		try {
			lista = ops.getMensajesMuro(id, filterBy);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		if (lista.isEmpty()) 
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		else {
			String aux = "";
			List <String> json = new ArrayList <String>();
			for (MensajeMuro msj : lista) {
				String location = uriInfo.getAbsolutePath() + "/" + msj.getId();
				aux = (new Gson().toJson(msj).replace("}",
						", \"location\": \"" + location + "\"}"));
				json.add(aux);
			}
			return Response.status(Response.Status.OK).
					entity(json.toString()).build();
		}
	}

	@POST
	@Path("/usuarios/{id}/muro_personal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responsePublicarMensajeMuro(@PathParam("id") String id, MensajeMuro msj) {
		OperacionesB ops = new OperacionesB();
		MensajeMuro thisMsj = null;
		try {
			thisMsj = ops.publicarMensajeMuro(id, msj.getCuerpo());
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		}
		String location = uriInfo.getAbsolutePath() + "/" + thisMsj.getId();
		return Response.status(Response.Status.CREATED).entity(location).
				header("Location", location).
				header("Content-Location", location).build();
	}
	
	@Path("/usuarios/{idU}/muro_personal/{idM}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetMensajeMuro(@PathParam("idU") String idU, @PathParam("idM") String idM) {
		MensajeMuro msj;
		OperacionesB ops = new OperacionesB();
		try {
			msj = ops.getMensajeMuro(idU, idM);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		}
		String json = new Gson().toJson(msj);
		return Response.status(Response.Status.OK).
				entity(json.toString()).build();
	}

	@PUT
	@Path("/usuarios/{idU}/muro_personal/{idM}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseEditarMensajeMuro(@PathParam("idU") String idU, 
			@PathParam("idM") String idM, MensajeMuro msj) {
		OperacionesB ops = new OperacionesB();
		try {
			ops.editarMensajeMuro(idU, idM, msj.getCuerpo());
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		}
		String location = uriInfo.getAbsolutePath().toString();
		msj.setId(Integer.valueOf(idM));
		msj.setIdUsuario(Integer.valueOf(idU));
		String json = (new Gson().toJson(msj));
		json = json.replace("}", ", \"location\": \"" + location + "\"}");
		return Response.status(Response.Status.OK).entity(json).build();		
	}
	
	@DELETE
	@Path("/usuarios/{id}/muro_personal/{idM}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response responseBorrarMensajeMuro(@PathParam("idM") String idM, @PathParam("id") String idU) {
		OperacionesB ops = new OperacionesB();
		try {
			if (!ops.borrarMensajeMuro(idM, idU))
				return Response.status(Response.Status.NOT_FOUND).
						entity(NOT_FOUND_ERROR).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity(OK_MESSAGE).build();

	}

	@Path("/usuarios/{id}/mensajes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetMensajesPrivados(@QueryParam("filterBy") @DefaultValue("") String filterBy,
			@PathParam("id") String id) {
		List <MensajePrivado> lista;
		OperacionesB ops = new OperacionesB();
		try {
			lista = ops.getMensajesPrivados(id, filterBy);
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		if (lista.isEmpty()) 
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		else {
			String aux = "";
			List <String> json = new ArrayList <String>();
			for (MensajePrivado msj : lista) {
				String location = uriInfo.getAbsolutePath() + "/" + msj.getId();
				aux = (new Gson().toJson(msj).replace("}",
						", \"location\": \"" + location + "\"}"));
				json.add(aux);
			}
			return Response.status(Response.Status.OK).
					entity(json.toString()).build();
		}
	}

	@POST
	@Path("/usuarios/{id}/mensajes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseEnviarMensajePrivado(@PathParam("id") String origen, MensajePrivado msj) {
		OperacionesB ops = new OperacionesB();
		MensajePrivado thisMsj = null;
		try {
			thisMsj = ops.enviarMensajePrivado(origen, String.valueOf(msj.getIdDestino()), msj.getCuerpo());
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		} catch (InformacionInvalida e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).
					entity(NOT_ACCEPTABLE_ERROR).build();
		}
		String location = uriInfo.getAbsolutePath() + "/" + thisMsj.getId();
		return Response.status(Response.Status.CREATED).entity(location).
				header("Location", location).
				header("Content-Location", location).build();
	}

	@Path("/usuarios/{idU}/mensajes/{idM}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetMensajePrivado(@PathParam("idU") String idU,@PathParam("idM") String idM) {
		MensajePrivado msj;
		OperacionesB ops = new OperacionesB();
		try {
			msj = ops.getMensajePrivado(idU, idM);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		}
		String json = new Gson().toJson(msj);
		return Response.status(Response.Status.OK).
				entity(json.toString()).build();
	}

	@Path("/usuarios/{id}/muro_amigos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseGetMensajesMuroAmigos(@PathParam("id") String id,
			@QueryParam("filterBy") @DefaultValue("") String filterBy) {
		List <MensajeMuro> lista;
		OperacionesB ops = new OperacionesB();
		try {
			lista = ops.getMensajesMuroAmigos(id, filterBy);
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		if (lista.isEmpty()) 
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		else {
			String aux = "";
			List <String> json = new ArrayList <String>();
			for (MensajeMuro msj : lista) {
				String location = uriInfo.getBaseUri() + "usuarios/"+ msj.getIdUsuario() + 
						"/muro_personal/" + msj.getId();
				aux = (new Gson().toJson(msj).replace("}",
						", \"location\": \"" + location + "\"}"));
				json.add(aux);
			}
			return Response.status(Response.Status.OK).
					entity(json.toString()).build();
		}
	}

	@GET
	@Path("usuarios/{id}/info_movil")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response responseInfoMovil(@PathParam("id") String id) {
		OperacionesB ops = new OperacionesB();
		InfoMovil info;
		try {
			info = ops.infoMovil(id);
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).
					entity(NOT_FOUND_ERROR).build();
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
					entity(INTERNAL_SERVER_ERROR).build();
		}
		String json = (new Gson().toJson(info));
		return Response.status(Response.Status.OK).entity(json).build();	
	}
}
