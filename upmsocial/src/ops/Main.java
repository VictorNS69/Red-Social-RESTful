package ops;
import java.io.File;
import java.sql.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.*;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.*;

import db.Conexion;
public class Main {

	@Path("/")
	public static void main(String[] args) throws SQLException, LifecycleException {
		Conexion con = new Conexion();
		con.crearConexion();
		String query = "SELECT * FROM upmsocial.Usuarios;";
		Statement st = con.getCon().createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getString("Nombre"));
		}
		con.cerrarConexion();
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.start();
		System.out.println(tomcat.getHost());
		tomcat.getServer().await();
	}
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello Jersey";
	  }
}
