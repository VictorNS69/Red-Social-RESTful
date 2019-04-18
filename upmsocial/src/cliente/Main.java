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

	/** Prepare the data to call Operaciones.listaUsuarios
	 */
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
	
	/** Prepare the data to call Operaciones.anadirUsuario
	 */
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

	/** Prepare the data to call Operaciones.infoUsuario
	 */
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

	/** Prepare the data to call Operaciones.modificaUsuario
	 */
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

	/** Prepare the data to call Operaciones.eliminaUsuario
	 */
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

	/** Prepare the data to call Operaciones.amigosUsuario
	 */
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
	
	/** Prepare the data to call Operaciones.anadirAmigoUsuario
	 */
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

	/** Prepare the data to call Operaciones.borrarAmigoUsuario
	 */
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

	/** Prepare the data to call Operaciones.mensajesMuroUsuario
	 */
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
	
	/** Prepare the data to call Operaciones.publicarMensajeMuro
	 */
	@SuppressWarnings("resource")
	private static void publicarMensajeMuroUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe un id.");
		Scanner in9 = new Scanner(System.in);
		int idU = in9.nextInt();
		System.out.println("Escribe el texto del mensaje.");
		Scanner in = new Scanner(System.in);
		String msg = in.nextLine();
		r = ops.publicarMensajeMuro(idU, msg);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
	}
	
	/** Prepare the data to call Operaciones.mensajeMuroUsuario
	 */
	@SuppressWarnings("resource")
	private static void mensajeMuroUsuario() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe el id del usuario.");
		Scanner in9 = new Scanner(System.in);
		int idU = in9.nextInt();
		System.out.println("Escribe el id del mensaje.");
		Scanner in = new Scanner(System.in);
		int idM = in.nextInt();
		r = ops.mensajeMuroUsuario(idU, idM);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}
	
	/** Prepare the data to call Operaciones.editarMensajeMuro
	 */
	@SuppressWarnings("resource")
	private static void editarMensajeMuro() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe el id del usuario.");
		Scanner in9 = new Scanner(System.in);
		int idU = in9.nextInt();
		System.out.println("Escribe el id del mensaje.");
		int idM = in9.nextInt();
		System.out.println("Escribe el texto del mensaje.");
		Scanner in = new Scanner(System.in);
		String msg = in.nextLine();
		r = ops.editarMensajeMuro(idU, idM, msg);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
	}
	
	/** Prepare the data to call Operaciones.borrarMensajeMuro
	 */
	@SuppressWarnings("resource")
	private static void borrarMensajeMuro() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe el id del usuario.");
		Scanner in9 = new Scanner(System.in);
		int idU = in9.nextInt();
		System.out.println("Escribe el id del mensaje.");
		Scanner in = new Scanner(System.in);
		int idM = in.nextInt();
		r = ops.borrarMensajeMuro(idU, idM);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}
	
	/** Prepare the data to call Operaciones.mensajesPrivadosUsuario
	 */
	@SuppressWarnings("resource")
	private static void listaMensajes() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe un id.");
		Scanner in9 = new Scanner(System.in);
		int id = in9.nextInt();
		r = ops.mensajesPrivadosUsuario(id);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}
	
	/** Prepare the data to call Operaciones.enviarMensaje
	 */
	@SuppressWarnings("resource")
	private static void enviarMensaje() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe un id de origen.");
		Scanner in9 = new Scanner(System.in);
		int idO = in9.nextInt();
		System.out.println("Escribe un id de destino.");
		Scanner in2 = new Scanner(System.in);
		int idD = in2.nextInt();
		System.out.println("Escribe el texto del mensaje.");
		Scanner in = new Scanner(System.in);
		String msg = in.nextLine();
		r = ops.enviarMensaje(idO, idD, msg);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
	}
	
	/** Prepare the data to call Operaciones.muroAmigos
	 */
	@SuppressWarnings("resource")
	private static void muroAmigos() {
		Response r;
		String valor = "";
		InterfazOperacionesC ops = new Operaciones(target);
		System.out.println("Escribe un id.");
		Scanner in9 = new Scanner(System.in);
		int id = in9.nextInt();
		r = ops.muroAmigos(id);
		System.out.println("Estado: " + r.getStatus());
        valor = r.readEntity(String.class);
        System.out.println("Entidad: " + valor);
        r.close();
	}
	
	/** Main program (executable).
	 * The program depends on the option you chose. 
	 * It can test all the functionalities from our API REST
	 */
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
			System.out.println("\t10- Publica un nuevo mensaje en el muro de un usuario.");
			System.out.println("\t11- Obtener un mensaje en el muro concreto de un usuario.");
			System.out.println("\t12- Editar un mensaje en el muro de un usuario.");
			System.out.println("\t13- Borrar un mensaje en el muro de un usuario.");
			System.out.println("\t14- Listar los mensajes privados de un usuario.");
			System.out.println("\t15- Enviar un mensaje privado.");
			System.out.println("\t16- Obtener un mensaje privado concreto.");
			System.out.println("\t17- Ver muro de amigos de un usuario.");
			// TODO: 20- la función de info para el movil
			System.out.println("\t19- Salir.");
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
					case 10:
						publicarMensajeMuroUsuario();
						break;
					case 11:
						mensajeMuroUsuario();
						break;
					case 12:
						editarMensajeMuro();
						break;
					case 13:
						borrarMensajeMuro();// TODO: test
						break;
					case 14:
						listaMensajes();// TODO: test
						break;
					case 15:
						enviarMensaje();// TODO: test
						break;
					// TODO: Continuar con las que faltan (15 y 16)
					case 17:
						muroAmigos();
						break;
					// TODO: Continuar con las que faltan (18 y 19)
					case 20:
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
