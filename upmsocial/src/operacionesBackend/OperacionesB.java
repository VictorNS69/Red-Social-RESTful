package operacionesBackend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
		List <Usuario> lista = new ArrayList();
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
	public void crearUsuario(int id, String nombre, String apellido1, String apellido2, long telefono, String email,
			String pais) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> getAmigos(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nuevoAmigo(Usuario usuario, Usuario amigo) {
		// TODO Auto-generated method stub
		
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
