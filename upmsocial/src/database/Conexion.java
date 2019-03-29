package database;

import java.sql.*;

public class Conexion {
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/upmsocial";
	private String usuario = "root";
	private String pass = "root1234";
	
	public Conexion() {}
	
	public void crearConexion() {
		try {
			this.con = DriverManager.getConnection(url, usuario, pass);
			System.out.println("Conexion establecida!");
		} catch (SQLException e) {
			System.err.println("No se ha podido establecer la conexion");
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() throws SQLException {
		if (!con.isClosed()) {
			try {
				this.con.close();
				System.out.println("Conexion cerrada con exito");
			} catch (SQLException e) {
				System.err.println("No se ha podido cerrar la conexion");
				e.printStackTrace();
			}
		} else System.out.println("No hay ninguna conexion abierta");
	}
			
	public Connection getCon() {
		return this.con;
	}
}
