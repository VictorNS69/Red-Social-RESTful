package operacionesBackend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import bd.Conexion;
import datos.InfoMovil;
import datos.MensajeMuro;
import datos.MensajePrivado;
import datos.Usuario;
import excepciones.InformacionInvalida;
import interfaces.OperacionesUsuario;

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
		query = "SELECT * FROM Mensajes_muro WHERE ID_USUARIO='" + id + "' ORDER BY FECHA LIMIT 1;";
		st = conn.getConn().createStatement();
		ResultSet rs2 = st.executeQuery(query);
		MensajeMuro msj = null;
		if (rs2.next())
			msj = new MensajeMuro(rs2.getInt("ID"), rs2.getInt("ID_USUARIO"), rs2.getString("CUERPO_MENSAJE"), rs2.getDate("FECHA"));
		Usuario usuario = null;
		if (rs.next()) {
			usuario = new Usuario(rs.getInt("ID"), rs.getString("NOMBRE"), 
					rs.getString("APELLIDO1"), rs.getString("APELLIDO2"),
					rs.getInt("TELEFONO"), rs.getString("EMAIL"),
					rs.getString("PAIS"), msj);
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
	public Usuario editarUsuario(String id, Usuario u) throws SQLException, 
			InformacionInvalida{
		Conexion conn = new Conexion();
		if (u.getNombre().isEmpty() || u.getApellido1().isEmpty() || u.getApellido2().isEmpty() 
				|| u.getPais().isEmpty() || u.getEmail().isEmpty())
			throw new InformacionInvalida();
		
		// User exist in the DB
		String query = "SELECT * FROM Usuarios WHERE ID='" + id +"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if (!rs.next())
			throw new NotFoundException();
		
		// Email is not in the DB
		query = "SELECT * FROM Usuarios WHERE EMAIL LIKE '" + u.getEmail() + "';";
		ps = conn.getConn().prepareStatement(query);
		rs = ps.executeQuery();
		if (rs.next())
			throw new InformacionInvalida();
		
		query = "UPDATE Usuarios SET NOMBRE='"+ u.getNombre() + "', APELLIDO1='" + 
			u.getApellido1() + "', APELLIDO2='" + u.getApellido2() + 
			"', TELEFONO='" + u.getTelefono() + "', EMAIL='" + u.getEmail() 
			+ "', PAIS='" + u.getPais() + "' WHERE ID='" + id + "';";
		ps = conn.getConn().prepareStatement(query);
		ps.executeUpdate();
		return u;
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
			if (start.equals("0"))
				start = "0";
			else {
				int realStart = 0;
				realStart = Integer.valueOf(start) - 1;
				start = String.valueOf(realStart);
			}
			if (end.isEmpty())
				end = "100";
			return "SELECT * FROM Usuarios join Relaciones_amistad"
					+ " ON (Usuarios.ID = Relaciones_amistad.ID_AMIGO2) "
					+ " WHERE Relaciones_amistad.ID_AMIGO1='" + id + "' AND NOMBRE LIKE '%"
					+ filterBy +"%' LIMIT " + start + "," + end +";";
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
			if (start.equals("0"))
				start = "0";
			else {
				int realStart = 0;
				realStart = Integer.valueOf(start) - 1;
				start = String.valueOf(realStart);
			}
			if (end.isEmpty())
				end = "100";
			return "SELECT * FROM Usuarios join Relaciones_amistad"
					+ " ON (Usuarios.ID = Relaciones_amistad.ID_AMIGO1) "
					+ " WHERE Relaciones_amistad.ID_AMIGO2='" + id + "' AND NOMBRE LIKE '%"
					+ filterBy +"%' LIMIT " + start + "," + end +";";
		}
	}
	
	@Override
	public void nuevoAmigo(String idU, String idA) throws SQLException, InformacionInvalida {
		Conexion conn = new Conexion();
		//Check if the two users are not the same
		if (idU.equals(idA)) 
			throw new InformacionInvalida();
		
		//Check if user is not in the DB
		String query = "SELECT * FROM Usuarios WHERE ID='" + idU +"';";
		String query2 = "SELECT * FROM Usuarios WHERE ID='" + idA +"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		PreparedStatement ps2 = conn.getConn().prepareStatement(query2);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = ps2.executeQuery();
		if (!rs.next() || !rs2.next())
			throw new NotFoundException();
		
		// Check if the relation exist 
		query = "SELECT * FROM Relaciones_amistad WHERE ID_AMIGO1='" + idU 
				+"' AND ID_AMIGO2='" + idA + "';";
		ps = conn.getConn().prepareStatement(query);
		rs = ps.executeQuery();
		if (rs.next())
			throw new InformacionInvalida();
		query = "SELECT * FROM Relaciones_amistad WHERE ID_AMIGO1='" + idA 
				+"' AND ID_AMIGO2='" + idU + "';";
		ps = conn.getConn().prepareStatement(query);
		rs = ps.executeQuery();
		if (rs.next())
			throw new InformacionInvalida();
		
		query = "INSERT INTO Relaciones_amistad (ID_AMIGO1, ID_AMIGO2)"
					 + "VALUES (?, ?);";
		ps = conn.getConn().prepareStatement(query);
	    ps.setString (1, idU);
	    ps.setString (2, idA);
	    ps.executeUpdate();
	}
	
	@Override
	public boolean borrarAmigo(String idU, String idA) throws SQLException {
		Conexion conn = new Conexion();	
		boolean enc = false;
		String query = "DELETE FROM Relaciones_amistad WHERE ID_AMIGO1 = '"+idU+"' AND ID_AMIGO2 = '"+idA+"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		if (ps.executeUpdate(query) == 1) {
			enc = true;
		} else {
			query = "DELETE FROM Relaciones_amistad WHERE ID_AMIGO2 = '"+idU+"' AND ID_AMIGO1 = '"+idA+"';";
			ps = conn.getConn().prepareStatement(query);
			if (ps.executeUpdate(query) == 1) {
				enc = true;
			}
		}
		return enc;
	}

	@Override
	public List<MensajeMuro> getMensajesMuro(String id, String filter) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Mensajes_muro WHERE ID_USUARIO = '"+id+"' AND CUERPO_MENSAJE LIKE '%" + filter + "%';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <MensajeMuro> lista = new ArrayList <MensajeMuro>();
		while(rs.next()) {
			MensajeMuro msj = new MensajeMuro(rs.getInt("ID"), 
					rs.getInt("ID_USUARIO"), rs.getString("CUERPO_MENSAJE"), 
					rs.getDate("FECHA"));
			lista.add(msj);
		}
		return lista;
	}

	@Override
	public MensajeMuro publicarMensajeMuro(String idU, String cuerpo) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Usuarios WHERE ID='" + idU +"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if (!rs.next())
			throw new NotFoundException();

		query = "INSERT INTO Mensajes_muro (ID_USUARIO, CUERPO_MENSAJE, FECHA) VALUES (?, ?, CURRENT_TIMESTAMP);";	
		ps = conn.getConn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, (int)Integer.valueOf(idU));
		ps.setString(2, cuerpo);
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		MensajeMuro msj = null;
		if (rs.next()) 
			msj = new MensajeMuro((int)Integer.valueOf(rs.getString(1)), (int)Integer.valueOf(idU), cuerpo);
		return msj;
	}

	@Override
	public MensajeMuro getMensajeMuro(String idU, String idM) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Mensajes_muro WHERE ID='" + idM + "' AND ID_USUARIO='" + idU + "';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		if (!rs.next())
			throw new NotFoundException();
		
		return new MensajeMuro(rs.getInt("ID"), 
					rs.getInt("ID_USUARIO"), rs.getString("CUERPO_MENSAJE"), 
					rs.getDate("FECHA"));
	}

	@Override
	public void editarMensajeMuro(String idU, String idM, String cuerpo) throws SQLException {
		Conexion conn = new Conexion();
		// Message exist in the DB
		String query = "SELECT * FROM Mensajes_muro WHERE ID='" + idM +"' AND ID_USUARIO='" + idU +"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if (!rs.next())
			throw new NotFoundException();
		
		query = "UPDATE Mensajes_muro SET CUERPO_MENSAJE='"+ cuerpo + "' WHERE ID='" + idM + "';";
		ps = conn.getConn().prepareStatement(query);
		ps.executeUpdate();		
	}

	@Override
	public boolean borrarMensajeMuro(String idM, String idU) throws SQLException {
		Conexion conn = new Conexion();
		String query = "DELETE FROM Mensajes_muro"
					 + " WHERE ID='" + idM + "' AND ID_USUARIO='" + idU +"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		return ps.executeUpdate() == 1;		
	}

	@Override
	public List<MensajePrivado> getMensajesPrivados(String id, String filter) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Mensajes_privados WHERE ID_ORIGEN = '"+id+"' AND CUERPO_MENSAJE LIKE '%" + filter + "%';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <MensajePrivado> lista = new ArrayList <MensajePrivado>();
		while(rs.next()) {
			MensajePrivado msj = new MensajePrivado(rs.getInt("ID"), 
					rs.getInt("ID_ORIGEN"), rs.getInt("ID_DESTINO"), 
					rs.getString("CUERPO_MENSAJE"), rs.getDate("FECHA"));
			lista.add(msj);
		}
		return lista;
	}

	@Override
	public MensajePrivado enviarMensajePrivado(String origen, String destino, String cuerpo) throws SQLException, InformacionInvalida {
		Conexion conn = new Conexion();
		if (origen.equals(destino))
			throw new InformacionInvalida();
		
		String query = "SELECT * FROM Usuarios WHERE ID='" + origen +"';";
		String query2 = "SELECT * FROM Usuarios WHERE ID='" + destino +"';";
		PreparedStatement ps = conn.getConn().prepareStatement(query);
		PreparedStatement ps2 = conn.getConn().prepareStatement(query2);
		ResultSet rs = ps.executeQuery();
		ResultSet rs2 = ps2.executeQuery();
		if (!rs.next() || !rs2.next())
			throw new NotFoundException();

		query = "INSERT INTO Mensajes_privados (ID_ORIGEN, ID_DESTINO, CUERPO_MENSAJE, FECHA) VALUES (?, ?, ?, CURRENT_TIMESTAMP);";	
		ps = conn.getConn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, (int)Integer.valueOf(origen));
		ps.setInt(2, (int)Integer.valueOf(destino));
		ps.setString(3, cuerpo);
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		MensajePrivado msj = null;
		if (rs.next()) 
			msj = new MensajePrivado((int)Integer.valueOf(rs.getString(1)), (int)Integer.valueOf(origen), (int)Integer.valueOf(destino), cuerpo);
		return msj;		
	}

	@Override
	public MensajePrivado getMensajePrivado(String idU, String idM) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Mensajes_privados WHERE ID='" + idM + "' AND ID_ORIGEN='" + idU + "';";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		if (!rs.next())
			throw new NotFoundException();
		
		return new MensajePrivado(rs.getInt("ID"), 
					rs.getInt("ID_ORIGEN"), rs.getInt("ID_DESTINO"), rs.getString("CUERPO_MENSAJE"), 
					rs.getDate("FECHA"));
	}

	@Override
	public List<MensajeMuro> getMensajesMuroAmigos(String id, String filter) throws SQLException {
		Conexion conn = new Conexion();
		String query = "SELECT * FROM Mensajes_muro JOIN Relaciones_amistad " + 
				"ON ((Mensajes_muro.ID_USUARIO=Relaciones_amistad.ID_AMIGO1 " + 
				"OR Mensajes_muro.ID_USUARIO=Relaciones_amistad.ID_AMIGO2) " + 
				"AND Mensajes_muro.ID_USUARIO!=" + id + ") " + 
				"WHERE ID_AMIGO1=" + id + " OR ID_AMIGO2="+ id + " AND CUERPO_MENSAJE LIKE '%" + filter + "%' " + 
				"ORDER BY FECHA DESC;";
		Statement st = conn.getConn().createStatement();
		ResultSet rs = st.executeQuery(query);
		List <MensajeMuro> lista = new ArrayList <MensajeMuro>();
		while(rs.next()) {
			MensajeMuro msj = new MensajeMuro(rs.getInt("ID"), 
					rs.getInt("ID_USUARIO"), rs.getString("CUERPO_MENSAJE"), 
					rs.getDate("FECHA"));
			lista.add(msj);
		}
		return lista;
	}

	@Override
	public InfoMovil infoMovil(String id) throws SQLException {
		Conexion conn = new Conexion();
		Statement st;
		InfoMovil info;
		List <MensajeMuro> ultimosMsj = new ArrayList <MensajeMuro>();
		String queryUsuario = "SELECT * FROM Usuarios WHERE ID =" + id + ";";
		st = conn.getConn().createStatement();
		ResultSet infoUsuario = st.executeQuery(queryUsuario);
		String queryAmigos = "SELECT COUNT(*) AS 'amigos' FROM Relaciones_amistad WHERE ID_AMIGO1="+ id + " OR ID_AMIGO2=" + id + ";";
		st = conn.getConn().createStatement();
		ResultSet infoAmigos = st.executeQuery(queryAmigos);
		String queryMensajes = "SELECT * FROM Mensajes_muro JOIN Relaciones_amistad\n" + 
				"ON ((Mensajes_muro.ID_USUARIO=Relaciones_amistad.ID_AMIGO1\n" + 
				"OR Mensajes_muro.ID_USUARIO=Relaciones_amistad.ID_AMIGO2)\n" + 
				"AND Mensajes_muro.ID_USUARIO!=6)\n" + 
				"WHERE ID_AMIGO1=" + id + " OR ID_AMIGO2=" + id + " \n" + 
				"ORDER BY FECHA DESC LIMIT 10;";
		st = conn.getConn().createStatement();
		ResultSet infoMensajes = st.executeQuery(queryMensajes);
		String location = "localhost:8080/upmsocial/usuarios/";
		while(infoMensajes.next()) {
			MensajeMuro msj = new MensajeMuro(infoMensajes.getInt("ID"), 
					infoMensajes.getInt("ID_USUARIO"), infoMensajes.getString("CUERPO_MENSAJE"), 
					infoMensajes.getDate("FECHA"), location + infoMensajes.getInt("ID_USUARIO") + "/muro_personal/" + infoMensajes.getInt("ID"));
			ultimosMsj.add(msj);
		}
		if (infoUsuario.next() && infoAmigos.next()) {
			info = new InfoMovil(infoUsuario.getString("NOMBRE"), 
					infoUsuario.getString("APELLIDO1"), infoUsuario.getString("APELLIDO2"),
					infoUsuario.getInt("TELEFONO"), infoUsuario.getString("EMAIL"),
					infoUsuario.getString("PAIS"), infoAmigos.getInt("amigos"), ultimosMsj);
		} else throw new NotFoundException();
		
		return info;
	}

}










