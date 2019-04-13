package operacionesBackend;

import java.sql.Date;
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
		String query = "SELECT * FROM Usuarios;";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <Usuario> lista = new ArrayList <Usuario>();
		while(rs.next()) {
			Usuario usuario = new Usuario(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO1"), rs.getString("APELLIDO2"),
					rs.getInt("TELEFONO"), rs.getString("EMAIL"), rs.getString("PAIS"));
			lista.add(usuario);
		}
		return lista;
	}

	@Override
	public void crearUsuario(String nombre, String apellido1, String apellido2, long telefono, String email,
			String pais) throws SQLException {
		Conexion conn = new Conexion();
		String query = " INSERT INTO Usuarios (NOMBRE, APELLIDO1, APELLIDO2, EMAIL, PAIS, TELEFONO)"
		        + " VALUES (?, ?, ?, ?, ?, ?)";
		      PreparedStatement preparedStmt;
			  preparedStmt = conn.getConn().prepareStatement(query);
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
		String query = "SELECT NOMBRE, APELLIDO1, APELLIDO2, EMAIL, PAIS, TELEFONO FROM Usuarios "
					 + "WHERE ID='" + id + "';"; 
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	@Override
	public void borrarUsuario(int id) throws SQLException {
		Conexion conn = new Conexion();
		String query = "DELETE FROM Usuarios"
					 + "WHERE ID='" + id + "';";
		Statement st = conn.getConn().createStatement();
		st.executeQuery(query);
	}

	@Override
	public List<Usuario> getAmigos(int id) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT ID_AMIGO2 FROM Relaciones_amistad"
					 + "WHERE ID_AMIGO1='" + id + "';";
		Statement st = conn.getConn().createStatement();
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
		String idUsuario = Integer.toString(idU);
		String idAmigo = Integer.toString(idA);
		String query = "INSERT INTO Relaciones_amistad (ID_AMIGO1, ID_AMIGO2)"
					 + "VALUES (?, ?)";
		PreparedStatement preparedStmt;
		  preparedStmt = conn.getConn().prepareStatement(query);
	      preparedStmt.setString (1, idUsuario);
	      preparedStmt.setString (2, idAmigo);
	}

	@Override
	public void borrarAmigo(int idU, int idA) throws SQLException {
		Conexion conn = new Conexion();		
		String idUsuario = Integer.toString(idU);
		String idAmigo = Integer.toString(idA);
		String query = "DELETE FROM Relaciones_amistad"
					 + "WHERE ID_AMIGO1 ='" + idUsuario
					 + "AND ID_AMIGO2 ='" + idAmigo + "';";
		Statement st = conn.getConn().createStatement();
		st.executeQuery(query);
	}

	@Override
	public List<MensajeMuro> getMensajesMuro(int id) throws SQLException {
		Conexion conn = new Conexion();	
		String query = "SELECT * FROM Mensajes_muro WHERE ID_USUARIO='" + id + "';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <MensajeMuro> mensajes = new ArrayList <MensajeMuro>();
		while(rs.next()) {
			MensajeMuro mensaje = new MensajeMuro(rs.getInt("ID"), rs.getInt("ID_USUARIO"), rs.getString("CUERPO"), rs.getDate("FECHA"));
			mensajes.add(mensaje);
		}
		return mensajes;
	}

	@Override
	public void publicarMensajeMuro(int idMsj, int idU, String cuerpo, Date fecha) throws SQLException {
		Conexion conn = new Conexion();
		String query = "INSERT INTO Mensajes_muro (ID, ID_USUARIO, CUERPO, FECHA)"
					 + "VALUES (?, ?, ?, ?)";	
		PreparedStatement preparedStmt;
		  preparedStmt = conn.getConn().prepareStatement(query);
	      preparedStmt.setInt (1, idMsj);
	      preparedStmt.setInt (2, idU);
	      preparedStmt.setString (3, cuerpo);
	      preparedStmt.setDate (4, fecha);
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
