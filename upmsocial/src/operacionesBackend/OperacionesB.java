package operacionesBackend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;
import interfaces.OperacionesUsuario;
import bd.Conexion;
import datos.Usuario;

public class OperacionesB implements OperacionesUsuario{

	@Override
	public List<Usuario> getUsuarios() throws SQLException {
		Conexion conn = new Conexion();
		conn.crearConexion();
		String query = "SELECT * FROM Usuarios;";
		Statement st = conn.getCon().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <Usuario> lista = new ArrayList <Usuario>();
		while(rs.next()) {
			Usuario usuario = new Usuario(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO1"), rs.getString("APELLIDO2"),
					rs.getInt("TELEFONO"), rs.getString("EMAIL"), rs.getString("PAIS"));
			lista.add(usuario);
		}
		for (int i=0; i< lista.size();i++) {
			System.out.print(lista.get(i).toString() + "\n");
		}
		conn.cerrarConexion();
		return lista;
	}

	@Override
	public void crearUsuario(String nombre, String apellido1, String apellido2, long telefono, String email,
			String pais) throws SQLException {
		Conexion conn = new Conexion();
		conn.crearConexion();
		
		String query = " INSERT INTO Usuarios (NOMBRE, APELLIDO1, APELLIDO2, EMAIL, PAIS, TELEFONO)"
		        + " VALUES (?, ?, ?, ?, ?, ?)";
		      PreparedStatement preparedStmt;
			  preparedStmt = conn.getCon().prepareStatement(query);
		      preparedStmt.setString (1, nombre);
		      preparedStmt.setString (2, apellido1);
		      preparedStmt.setString (3, apellido2);
		      preparedStmt.setString (4, email);
		      preparedStmt.setString (5, pais);
		      preparedStmt.setLong (6, telefono);
	}

	@Override
	public ResultSet infoUsuario(int id) throws SQLException {
		Conexion conn = new Conexion();
		conn.crearConexion();
		String query = "SELECT NOMBRE, APELLIDO1, APELLIDO2, EMAIL, PAIS, TELEFONO FROM Usuarios "
					 + "WHERE ID='" + id + "';"; 
		Statement st = conn.getCon().createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	@Override
	public void borrarUsuario(int id) throws SQLException {
		Conexion conn = new Conexion();
		conn.crearConexion();
		String query = "DELETE FROM Usuarios"
					 + "WHERE ID='" + id + "';";
		Statement st = conn.getCon().createStatement();
		st.executeQuery(query);
	}

	@Override
	public List<Usuario> getAmigos(int id) throws SQLException {
		Conexion conn = new Conexion();
		conn.crearConexion();
		String query = "SELECT ID_AMIGO2 FROM Relaciones_amistad"
					 + "WHERE ID_AMIGO1='" + id + "';";
		Statement st = conn.getCon().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <Usuario> amigos = new ArrayList <Usuario>();
		while(rs.next()) {
			Usuario usuario = new Usuario(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO1"), rs.getString("APELLIDO2"),
					rs.getInt("TELEFONO"), rs.getString("EMAIL"), rs.getString("PAIS"));
			amigos.add(usuario);
		}
		for (int i=0; i< amigos.size();i++) {
			System.out.print(amigos.get(i).toString() + "\n");
		}
		return amigos;
	}

	@Override
	public void nuevoAmigo(int idU, int idA) throws SQLException {
		Conexion conn = new Conexion();
		conn.crearConexion();
		String idUsuario = Integer.toString(idU);
		String idAmigo = Integer.toString(idA);
		String query = "INSERT INTO Relaciones_amistad (ID_AMIGO1, ID_AMIGO2)"
					 + "VALUES (?, ?)";
		PreparedStatement preparedStmt;
		  preparedStmt = conn.getCon().prepareStatement(query);
	      preparedStmt.setString (1, idUsuario);
	      preparedStmt.setString (2, idAmigo);
	}

	@Override
	public void borrarAmigo(Usuario usuario, Usuario amigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MensajeMuro> getMensajesMuro(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void publicarMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MensajeMuro getMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editarMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarMensajeMuro(Usuario usuario, MensajeMuro msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MensajePrivado> getMensajesPrivados(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enviarMensajePrivado(Usuario origen, Usuario destino, MensajePrivado msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MensajePrivado getMensajePrivado(Usuario usuario, MensajePrivado msj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarMensajePrivado(Usuario usuario, MensajePrivado msj) {
		// TODO Auto-generated method stub
		
	}

}
