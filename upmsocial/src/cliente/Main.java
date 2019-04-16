package cliente;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import datos.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	private static WebTarget target;
		
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/upmsocial/").build();
    }

	@SuppressWarnings("resource")
	private static void listaUsuarios() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		r = ops.listaUsuarios();
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}

	@SuppressWarnings("resource")
	private static void anadirUsuario() {		
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		Scanner in2 = new Scanner(System.in);
		System.out.println("Escribe un nombre.");
		String nombre = in2.nextLine();
		System.out.println("Escribe un apellido.");
		String apellido1 = in2.nextLine();
		System.out.println("Escribe otro apellido.");
		String apellido2 = in2.nextLine();
		System.out.println("Escribe un email.");
		String email = in2.nextLine();
		System.out.println("Escribe un pais.");
		String pais = in2.nextLine();
		System.out.println("Escribe un numero de telefono.");
		int telefono = in2.nextInt();
		Usuario usuario = new Usuario(nombre, apellido1, 
				apellido2, telefono, email, pais);
		r = ops.anadirUsuario(usuario);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}

	@SuppressWarnings("resource")
	private static void infoUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		Scanner in3 = new Scanner(System.in);
		System.out.println("Escribe un id.");
		int id = in3.nextInt();
		r = ops.infoUsuario(id);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
		r.close();
	}

	@SuppressWarnings("resource")
	private static void modificaUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		Scanner in4 = new Scanner(System.in);
		System.out.println("Escribe un id.");
		Scanner in = new Scanner(System.in);
		int id = in.nextInt();
		System.out.println("Escribe un nombre.");
		String nombre = in4.nextLine();
		System.out.println("Escribe un apellido.");
		String apellido1 = in4.nextLine();
		System.out.println("Escribe otro apellido.");
		String apellido2 = in4.nextLine();
		System.out.println("Escribe un email.");
		String email = in4.nextLine();
		System.out.println("Escribe un pais.");
		String pais = in4.nextLine();
		System.out.println("Escribe un numero de telefono.");
		int telefono = in4.nextInt();
		Usuario usuario = new Usuario(nombre, apellido1, apellido2, 
				telefono, email, pais);
		r = ops.modificaUsuario(id, usuario);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
		r.close();
	}

	@SuppressWarnings("resource")
	private static void eliminaUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		Scanner in5 = new Scanner(System.in);
		System.out.println("Escribe un id.");
		int id = in5.nextInt();
		r = ops.eliminaUsuario(id);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
		r.close();
	}

	@SuppressWarnings("resource")
	private static void amigosUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe un id.");
		Scanner in6 = new Scanner(System.in);
		int id = in6.nextInt();
		try {
			r = ops.amigosUsuario(id);
			System.out.println("Estado: " + r.getStatus());
	        valor = r.readEntity(String.class);
	        System.out.println("Entidad: " + valor);
	        r.close();
		} catch (NumberFormatException e) {
			System.err.println("Opción introducida no válida.");
		}
	}

	@SuppressWarnings("resource")
	private static void anadirAmigoUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		Scanner in7 = new Scanner(System.in);
		System.out.println("Escribe el id del usuario.");
		int idU = in7.nextInt();
		System.out.println("Escribe el id del amigo.");
		int idA = in7.nextInt();
		r = ops.anadirAmigoUsuario(idU, idA);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}

	@SuppressWarnings("resource")
	private static void borrarAmigoUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		Scanner in8 = new Scanner(System.in);
		System.out.println("Escribe el id del usuario.");
		int idU = in8.nextInt();
		System.out.println("Escribe el id del amigo.");
		int idA = in8.nextInt();
		r = ops.borrarAmigoUsuario(idU, idA);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
		r.close();
	}

	@SuppressWarnings("resource")
	private static void mensajesMuroUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe un id.");
		Scanner in9 = new Scanner(System.in);
		int id = in9.nextInt();
		r = ops.mensajesMuroUsuario(id);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Bienvenido al cliente.");
		try {
			ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	        target = (client.target(getBaseURI()));
		} catch (Exception e) {
			System.err.println("No se ha podido conectar al servidor.\n Saliendo.");
			System.exit(1);
		}
		int option = -1;
		while (true) {
			System.out.print("\n");
			System.out.println("Elige una opción:");
			System.out.println("\t1- Listar a todos los usuarios del sistema.");
			System.out.println("\t2- Añadir un nuevo usuario al sistema.");
			System.out.println("\t3- Obtener la información de un usuario concreto.");
			System.out.println("\t4- Modificar la información de un usuario concreto.");
			System.out.println("\t5- Eliminar a un usuario concreto.");
			System.out.println("\t6- Obtener los amigos de un usuario concreto.");
			System.out.println("\t7- Añadir un amigo a un usuario concreto.");
			System.out.println("\t8- Eliminar un amigo a un usuario concreto.");
			System.out.println("\t9- Listar los mensajes en el muro de un usuario concreto.");
			// TODO: Añadir los que falten
			System.out.println("\t69- Salir.");
			try {
				Scanner in = new Scanner(System.in);
				option = in.nextInt();
				
				switch (option) {
					case 1:
						listaUsuarios();
						break;
					case 2:
						anadirUsuario();
						break;
					case 3:
						infoUsuario();
						break;
					case 4:
						modificaUsuario();
						break;
					case 5:
						eliminaUsuario();
						break;
					case 6:
						amigosUsuario();
						break;
					case 7:
						anadirAmigoUsuario();
						break;
					case 8:
						borrarAmigoUsuario();
						break;
					case 9:
						mensajesMuroUsuario();
				        break;
					// TODO: Continuar
					case 69:
						System.out.println("Saliendo.");
						System.exit(0);
					default:
						System.err.println("Opción introducida no válida");
						continue;
				}
			} 
			catch (InputMismatchException e) {
				System.err.println("Opción introducida no válida.");
			}
			catch (Exception e) {
				System.err.println("No se ha podido conectar al servidor."
						+ "\nCompruebe el estado del servidor.");
				System.out.println("Saliendo.");
				System.exit(1);
			}		
		}
	}

}