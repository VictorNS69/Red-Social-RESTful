# Red Social RESTful
Se trata de diseñar e implementar una API REST y un prototipo funcional de un servicio sencillo para una red social de tipo Facebook, donde los usuarios puedan interactuar entre ellos de diversas formas. Se asume que la autenticación y seguridad de la herramienta está realizada y por tanto no hay que implementarla.

En este servicio los usuarios publican mensajes en su página personal y pueden ser amigos de otros usuarios (relación de amistad recíproca) para poder ver los mensajes de éstos, a los que además pueden enviar mensajes privados (que no puede ver nadie más que los implicados en el envío, emisor y receptor). El servicio debe soportar a través de esa API las siguientes operaciones:
- Añadir un nuevo usuario a la red.
- Ver los datos básicos de un usuario.
- Cambiar datos básicos de nuestro perfil de usuario (excepto nombre de usuario).
- Obtener una lista de todos los usuarios existentes en la red social. Esta lista debe permitir ser filtrada por patrón de nombre (eg. Buscar todos los usuarios que contengan “Mar” en su nombre, “Mario”, “María”…etc).
- Publicar un nuevo mensaje en la página personal de un usuario.
- Eliminar un mensaje propio.
- Editar un mensaje propio.
- Obtener una lista de todos los mensajes de un usuario en su página personal.Además, esta lista debe permitir la opción de ser filtrada por fecha o limitar la cantidad de información obtenida por número de mensajes (e.g. los 10 primeros elementos, los elementos entre el 11 y el 20, etc).
- Añadir un nuevo amigo.
- Eliminar un amigo.
- Obtener una lista de todos nuestros amigos. Además, esta lista debe permitir la opción de ser filtrada por el patrón de nombre o limitar la cantidad de información obtenida por número de amigos (e.g. los 10 primeros elementos, los elementos entre el 11 y el 20, etc).
- Enviar un mensaje personal a otro usuario.
- Borrar nuestro perfil de la red social.
- Obtener una lista con los últimos mensajes de las páginas de nuestros amigos ordenados por fecha (similar a como lo muestra Facebook). Esta lista debe permitir la opción de ser filtrada por la búsqueda de contenido de texto (patrón) en el mensaje.
- Consultar fácilmente la descripción necesaria para una aplicación móvil que queremos realizar, que muestre los datos básicos de un usuario, su último mensaje en la página, el número de amigos y los 10 últimos mensajes de las páginas de sus amigos que se han actualizado.

## Autores
[Víctor Nieves Sánchez](https://twitter.com/VictorNS69)

Daniel Morgera Pérez

## Enunciado
Para ver el enunciado de la práctica pincha [aquí](/doc/Practica-RESTful-2019-enunciado.pdf).

## Requisitos
Herramientas:
- Tomcat 9
- Eclipse JEE
- MySQL
- Postman 

Librerias:
- Jersey 
- MySQL
- Gson
- Naming common

Todas las librerías usadas en este proyecto se encuentran [aquí](/upmsocial/WebContent/WEB-INF/lib).
## Como importar y exportar la base de datos
En esta práctica, utilizaremos un usuario llamado **root**, con contraseña **root1234**.

- Para **importar** una base de datos, seguir los siguientes pasos:

Si es la primera vez que se importa esta base de datos, introducir en la consola de MySQL:
```
mysql> CREATE DATABASE upmsocial;
```
Una vez creada la base de datos en MySQL, introducir el siguiente comando en el terminal estando en el [directorio](/upmsocial/src/bd) donde está el archivo _db.sql_:
```sh
$ mysql -u root -p upmsocial < db.sql
```

- Para **exportar** una base de datos, utilizar este comando en el terminal en el directorio donde se quiera guardar el db.sql:
```sh
$ mysqldump -u root -p upmsocial > db.sql
```
## Importar proyecto
Abrir Eclipse JEE. Seleccionar `Importar` y elegir `Importar war` llamando al proyecto como `upmsocial`.
## Activar el servidor localhost de Tomcat desde Eclipse
Desde Eclipse, seleccionar `Servidores` y crear un `Nuevo Servidor Tomcat v9.0` en `localhost`.

Además en `Añadir recursos` hay que añadir el proyecto `upmsocial`.

Una vez esté todo configurado, en el apartado `Servidores` nos aparecerá el servidor de `Tomcat v9.0 Server at localhost`, el cual debemos poner en ejecución.
## Ejemplos de uris y operaciones en postman
Esta API REST se ha probado en _Postman_, ya que es la manera más sencilla de comprobar su funcionamiento.

Una vez en _Postman_, debemos escribir la siguiente URI base:
```
http://localhost:8080/upmsocial/
```
y desde esta URI escribir cualquiera de las opciones mencionadas en la [memoria](/doc/Memoria-SOS.pdf).

Ten en cuenta que **solo estarán disponibles las URI y los métodos que indicamos en la memoria**.
### Nota:
Los JSON que se deseen emplear en las operaciones, tienen que seguir el formato que se muestra en las imágenes de la memoria.
## Ejecución del cliente
Para ejecutar el cliente, desde Eclipse debemos ejecutar el archivo (como aplicación de Java) `src/cliente/Main`.

Una vez ejecutado, podemos probar las distintas opciones, añadiendo los datos valores que deseemos.
## Memoria del proyecto
La [memoria](/doc/Memoria-SOS.pdf) incluye capturas de todas las operaciones disponibles, así como el formato de los JSON en las operaciones PUT y POST. Se recomienda echarle un vistazo antes de probar la API en _Postman_.
## Licencia
[Licencia](/LICENSE).
