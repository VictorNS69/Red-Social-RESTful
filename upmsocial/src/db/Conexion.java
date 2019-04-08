package db;

import java.sql.*;

public class Conexion {
	
	private Connection con;
	private String url;
	private String usuario;
	private String pass;
	
	/** Constructor sin argumentos, se usa por defecto nuestros argumentos
	 */
	public Conexion() {
		this.url = "jdbc:mysql://localhost:3306/upmsocial";
		this.usuario = "root";
		this.pass = "root1234";
	}
	
	/** Constructor con argumentos, asigna los parametros de la llamada
	 * @param url: url del servidor
	 * @param usuario: usuario de la base de datos
	 * @param pass: contraseña del usuario
	 */
	public Conexion(String url, String usuario, String pass) {
		this.url = url;
		this.usuario = usuario;
		this.pass = pass;
	}
	
	public void crearConexion() {
		try {
			this.con = DriverManager.getConnection(url, usuario, pass);
			System.out.println("Conexion establecida");
		} 
		catch (SQLException e) {
			System.err.println("No se ha podido establecer la conexion");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void cerrarConexion() throws SQLException {
		if (!con.isClosed()) {
			try {
				this.con.close();
				System.out.println("Conexion cerrada con exito");
			} 
			catch (SQLException e) {
				System.err.println("No se ha podido cerrar la conexion");
				e.printStackTrace();
				System.exit(1);
			}
		} else System.out.println("No hay ninguna conexion abierta");
	}
			
	public Connection getCon() {
		return this.con;
	}
}
