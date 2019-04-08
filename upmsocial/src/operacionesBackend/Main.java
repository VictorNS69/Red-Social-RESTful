package operacionesBackend;
import java.io.File;
import java.sql.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bd.Conexion;
import operacionesBackend.*;

import org.apache.*;
public class Main {

	@Path("/")
	public static void main(String[] args) throws SQLException {
		OperacionesB op = new OperacionesB();
		op.getUsuarios();
		/*
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
	  }*/
	}
}
