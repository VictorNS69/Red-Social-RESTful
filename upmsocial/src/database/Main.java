package database;
import java.sql.*;
public class Main {

	public static void main(String[] args) throws SQLException {
		
		Conexion con = new Conexion();
		con.crearConexion();
		String query = "SELECT * FROM upmsocial.Usuarios;";
		Statement st = con.getCon().createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getString("Nombre"));
		}
		con.cerrarConexion();
	}

}
