package cliente;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import datos.Usuario;

import java.util.Scanner;


public class Main {

	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/upmsocial/").build();
    }
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("Bienvenido al cliente.");
		WebTarget target = null;
		try {
			ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	        target = client.target(getBaseURI());
		} catch (Exception e) {
			System.out.println("No se ha podido conectar al servidor.\n Saliendo.");
			e.printStackTrace();
			System.exit(0);
		}
		OperacionesC ops = new Operaciones(target);
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
			// TODO: Añadir los que falten
			System.out.println("\t69- Salir.");
			try {
				Scanner in = new Scanner(System.in);
				option = in.nextInt();
				Response r;
				String valor = "";
				switch (option) {
					case 1:
						r = ops.listaUsuarios();
						System.out.println("Estado: " + r.getStatus());
				        valor = r.readEntity(String.class);
				        System.out.println("Entidad: " + valor);
				        r.close();
						break;
					case 2:
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
						break;
					case 3:
						Scanner in3 = new Scanner(System.in);
						System.out.println("Escribe un id.");
						int id = in3.nextInt();
						r = ops.infoUsuario(id);
						System.out.println("Estado: " + r.getStatus());
				        valor = r.readEntity(String.class);
				        System.out.println("Entidad: " + valor);
						r.close();
						break;
					case 4:
						Scanner in4 = new Scanner(System.in);
						System.out.println("Escribe un id.");
						id = in.nextInt();
						System.out.println("Escribe un nombre.");
						nombre = in4.nextLine();
						System.out.println("Escribe un apellido.");
						apellido1 = in4.nextLine();
						System.out.println("Escribe otro apellido.");
						apellido2 = in4.nextLine();
						System.out.println("Escribe un email.");
						email = in4.nextLine();
						System.out.println("Escribe un pais.");
						pais = in4.nextLine();
						System.out.println("Escribe un numero de telefono.");
						telefono = in4.nextInt();
						usuario = new Usuario(nombre, apellido1, apellido2, 
								telefono, email, pais);
						r = ops.modificaUsuario(id, usuario);
						System.out.println("Estado: " + r.getStatus());
				        valor = r.readEntity(String.class);
				        System.out.println("Entidad: " + valor);
						r.close();
						break;
					case 5:
						Scanner in5 = new Scanner(System.in);
						System.out.println("Escribe un id.");
						id = in5.nextInt();
						r = ops.eliminaUsuario(id);
						System.out.println("Estado: " + r.getStatus());
				        valor = r.readEntity(String.class);
				        System.out.println("Entidad: " + valor);
						r.close();
						break;
					case 6:
						System.out.println("Escribe un id.");
						Scanner in6 = new Scanner(System.in);
						id = in6.nextInt();
						r = ops.amigosUsuario(id);
						System.out.println("Estado: " + r.getStatus());
				        valor = r.readEntity(String.class);
				        System.out.println("Entidad: " + valor);
				        r.close();
						break;
					case 7:
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
						break;
					// TODO: Continuar
					case 69:
						System.out.println("Saliendo.");
						System.exit(0);
					default:
						System.err.println("Opción introducida no válida!\n");
						continue;
				}
			} catch (Exception e) {
				System.err.println("Opción introducida no válida!\n");
			}		
		}
	}

}
