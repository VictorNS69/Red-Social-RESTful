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
import excepciones.InformacionInvalida;
import interfaces.OperacionesUsuario;
import bd.Conexion;

public class OperacionesB implements OperacionesUsuario{

	@Override
	public List<Usuario> getUsuarios(String filter) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Usuarios WHERE NOMBRE LIKE '%" + filter + "%';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <Usuario> lista = new ArrayList <Usuario>();
		while(rs.next()) {
			Usuario usuario = new Usuario(rs.getInt("ID"), 
					rs.getString("NOMBRE"), rs.getString("APELLIDO1"), 
					rs.getString("APELLIDO2"),
					rs.getInt("TELEFONO"), rs.getString("EMAIL"), 
					rs.getString("PAIS"));
			lista.add(usuario);
		}
		return lista;
	}

	@Override
	public Usuario crearUsuario(String nombre, String apellido1, 
			String apellido2, int telefono, String email,
			String pais) throws SQLException, InformacionInvalida {
		Conexion conn = new Conexion();
		// Check if the input arguments are valid
		if (nombre == null || apellido1 == null || 
				apellido2 == null || email == null
				|| nombre.isEmpty() || apellido1.isEmpty() || 
				apellido2.isEmpty() || email.isEmpty())
			throw new InformacionInvalida();
		
		// Check if the User is already in the DB
		String query = "SELECT ID FROM Usuarios WHERE EMAIL='" + email +"';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		if (rs.next())
			throw new InformacionInvalida();
		
		// Insert the new User into the DB
		query = " INSERT INTO Usuarios (NOMBRE, APELLIDO1, "
				+ "APELLIDO2, EMAIL, PAIS, TELEFONO)"
		        + " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		ps = conn.getConn().prepareStatement(query, 
				Statement.RETURN_GENERATED_KEYS);
		ps.setString (1, nombre);
		ps.setString (2, apellido1);
		ps.setString (3, apellido2);
		ps.setString (4, email);
		ps.setString (5, pais);
		ps.setInt (6, telefono);
		ps.executeUpdate();
		ResultSet thisId = ps.getGeneratedKeys();
		Usuario usuario = null;
		if (thisId.next()) {
			usuario = new Usuario(thisId.getInt(1), nombre, apellido1, 
					apellido2, telefono, email, pais);
		}
		return usuario;
	}

	@Override
	public Usuario infoUsuario(int id) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Usuarios "
					 + "WHERE ID='" + id + "';"; 
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		Usuario usuario = null;
		if (rs.next()) {
			usuario = new Usuario(rs.getInt("ID"), rs.getString("NOMBRE"), 
					rs.getString("APELLIDO1"), rs.getString("APELLIDO2"),
				rs.getInt("TELEFONO"), rs.getString("EMAIL"),
				rs.getString("PAIS"));
		}
		return usuario;
	}

	@Override
	public boolean borrarUsuario(String id) throws SQLException {
		Conexion conn = new Conexion();
		String query = "DELETE FROM Usuarios"
					 + " WHERE ID=" + id + ";";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		return ps.executeUpdate() == 1;
	}
	
	@Override
	public List<Usuario> getAmigos(String id, String filterBy, 
			String start, String end) throws SQLException {
		Conexion conn = new Conexion();
		// Column 1 friend of Column 2
		String query = getQueryC1(id, filterBy, 
				start, end);
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <Usuario> amigos = new ArrayList <Usuario>();
		while(rs.next()) {
			Usuario usuario = new Usuario(rs.getInt("ID"), 
					rs.getString("NOMBRE"), rs.getString("APELLIDO1"), 
					rs.getString("APELLIDO2"), rs.getInt("TELEFONO"), 
					rs.getString("EMAIL"), rs.getString("PAIS"));
			amigos.add(usuario);
		}
		// Column 2 friend of Column 1
		query = getQueryC2(id, filterBy, 
				start, end);
		st = conn.getConn().createStatement();
		rs = st.executeQuery(query);
		while(rs.next()) {
			Usuario usuario = new Usuario(rs.getInt("ID"), 
					rs.getString("NOMBRE"), rs.getString("APELLIDO1"), 
					rs.getString("APELLIDO2"), rs.getInt("TELEFONO"), 
					rs.getString("EMAIL"), rs.getString("PAIS"));
			boolean encontrado = false;
			for (Usuario u : amigos) {
				if (u.equals(usuario)) {
					encontrado = true;
					break;
				}
			}
			if (!encontrado)
				amigos.add(usuario);
		}
		return amigos;
	}
	
	private String getQueryC1(String id, String filterBy, 
			String start, String end) {
		// No parameters -> use only id
		if (filterBy.isEmpty() && start.isEmpty()
				&& end.isEmpty()) {
			return "SELECT * FROM Usuarios join Relaciones_amistad"
					+ " ON (Usuarios.ID = Relaciones_amistad.ID_AMIGO2) "
					+ " WHERE Relaciones_amistad.ID_AMIGO1='" + id + "';";
		}
		else {
			if (start.isEmpty())
				start = "0";
			if (end.isEmpty())
				end = "100";
			return "SELECT * FROM Usuarios join Relaciones_amistad"
					+ " ON (Usuarios.ID = Relaciones_amistad.ID_AMIGO2) "
					+ " WHERE Relaciones_amistad.ID_AMIGO1='" + id + "' AND NOMBRE LIKE '%"
					+ filterBy +"%' ORDER BY ID LIMIT " + start + "," + end +";";
		}
	}
	
	private String getQueryC2(String id, String filterBy, 
			String start, String end) {
		// No parameters -> use id
		if (filterBy.isEmpty() && start.isEmpty()
				&& end.isEmpty()) {
			return "SELECT * FROM Usuarios join Relaciones_amistad"
					+ " ON (Usuarios.ID = Relaciones_amistad.ID_AMIGO1) "
					+ " WHERE Relaciones_amistad.ID_AMIGO2='" + id + "';";
		}
		else {
			if (start.isEmpty())
				start = "0";
			if (end.isEmpty())
				end = "100";
			return "SELECT * FROM Usuarios join Relaciones_amistad"
					+ " ON (Usuarios.ID = Relaciones_amistad.ID_AMIGO1) "
					+ " WHERE Relaciones_amistad.ID_AMIGO2='" + id + "' AND NOMBRE LIKE '%"
					+ filterBy +"%' ORDER BY ID LIMIT " + start + "," + end +";";
		}
	}
	
	@Override
	public void nuevoAmigo(int idU, int idA) throws SQLException {
		Conexion conn = new Conexion();
		String idUsuario = Integer.toString(idU);
		String idAmigo = Integer.toString(idA);
		String query = "INSERT INTO Relaciones_amistad (ID_AMIGO1, ID_AMIGO2)"
					 + "VALUES (?, ?)";
		PreparedStatement ps;
		  ps = conn.getConn().prepareStatement(query);
	      ps.setString (1, idUsuario);
	      ps.setString (2, idAmigo);
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

	//TODO: La fecha tiene que transformarse en formato legible para la APP
	@Override
	public List<MensajeMuro> getMensajesMuro(int id) throws SQLException {
		Conexion conn = new Conexion();	
		String query = "SELECT * FROM Mensajes_muro WHERE ID_USUARIO='" + id + "';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <MensajeMuro> mensajes = new ArrayList <MensajeMuro>();
		while(rs.next()) {
			MensajeMuro mensaje = new MensajeMuro(rs.getInt("ID"), 
					rs.getInt("ID_USUARIO"), rs.getString("CUERPO"), 
					rs.getDate("FECHA"));
			mensajes.add(mensaje);
		}
		return mensajes;
	}

	//TODO: La fecha tiene que transformarse en formato SQL
	@Override
	public void publicarMensajeMuro(int idMsj, int idU, String cuerpo, 
			Date fecha) throws SQLException {
		Conexion conn = new Conexion();
		String query = "INSERT INTO Mensajes_muro (ID, ID_USUARIO, CUERPO, FECHA)"
					 + "VALUES (?, ?, ?, ?)";	
		PreparedStatement ps;
		  ps = conn.getConn().prepareStatement(query);
	      ps.setInt (1, idMsj);
	      ps.setInt (2, idU);
	      ps.setString (3, cuerpo);
	      ps.setDate (4, fecha);
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
	public void enviarMensajePrivado(Usuario origen, Usuario destino, 
			MensajePrivado msj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MensajePrivado getMensajePrivado(Usuario usuario, 
			MensajePrivado msj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrarMensajePrivado(Usuario usuario, 
			MensajePrivado msj) {
		// TODO Auto-generated method stub
		
	}

}
