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
  @Produces(MediaType.APPLICATION_JSON)
  public Response saludoPlainText() {
    String respuesta = "{\n" + 
    		"    \"tutorials\": {\n" + 
    		"        \"id\": \"Crunchify\",\n" + 
    		"        \"topic\": \"REST Service\",\n" + 
    		"        \"description\": \"This is REST Service Example by Crunchify.\"\n" + 
    		"    }\n" + 
    		"}";
    return Response.status(Response.Status.ACCEPTED).entity(respuesta).header("Location", 
        uriInfo.getAbsolutePath().toString()+"/otra").build();
  }
}
