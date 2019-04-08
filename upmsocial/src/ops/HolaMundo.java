package ops;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("/holamundo")
public class HolaMundo {

  @Context
  private UriInfo uriInfo;
  
  // Este método se invoca si se solicita TEXT_PLAIN
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response saludoPlainText() {
    String respuesta = "Hola JAX-RS";
    return Response.status(Response.Status.ACCEPTED).entity(respuesta).header("Location", 
        uriInfo.getAbsolutePath().toString()+"/otra").build();
  }
}
