# DWF_MELENDEZ_SERRANO_VASQUEZ_VENTURA

CRUD y Webservice implementado con Jakarta Server Faces

### Características del proyecto
- **CRUD:** Crear, Leer, Actualizar y Eliminar para reqgistrar y gestionar videojuegos y ventas de videojuegos.
- **Interfaz de usuario:** Desarrollada con Jakarta Server Faces para una experiencia amigable.
- **Webservice:** Permite buscar ventas y videojuegos de manera sencilla mediante un ID
  
### Requisitos
- **IntelliJ IDEA**
- **Payara Server:** Versión 5.2022.21
- **MySQL**
- **JDK:** Java Development Kit versión 11

### Pasos para ejecutar el proyecto.

1. **Clonar el proyecto**
   ```sh
   git clone https://github.com/tu_usuario/DWF_MELENDEZ_SERRANO_VASQUEZ_VENTURA.git
   cd DWF_MELENDEZ_SERRANO_VASQUEZ_VENTURA
   
2. Abrir la carpeta del proyecto con *IntelliJ IDEA*
3. Ejecutar el archivo `db.sql` que se encuentra en `src/java/main/database` en un gestor de base de datos MySQL
4. Descargar las dependencias necesarias desde el archivo `pom.xml` 
5. Configurar el servidor *Payara 5.2022.21* para ejecutar la aplicación
6. De ser necesario, modificar las credenciales y el servidor de la base de datos desde la clase `DatabaseConnection` que se encuentra en la carpeta `src/java/main/database`
7. Finalmente, deployar y ejecutar la aplicación con *Payara Server*

### ¿Cómo consumir el webservice?

1. **Para consultar videojuegos y obtener resultados en formato JSON coloca la siguiente URL en tu navegador:**
`http://<server_host>:<server_port>/<context_path>/webservice/buscar/videojuegos/<videojuego_id>`
*Por ejemplo:*
`localhost:8080/DWF_MELENDEZ_SERRANO_VASQUEZ_VENTURA-1.0-SNAPSHOT/webservice/buscar/videojuegos/3`

2. **Para consultar ventas y obtener resultados en formato JSON coloca la siguiente URL en tu navegador:**
`http://<server_host>:<server_port>/<context_path>/webservice/buscar/ventas/<venta_id>`
*Por ejemplo:*
`localhost:8080/DWF_MELENDEZ_SERRANO_VASQUEZ_VENTURA-1.0-SNAPSHOT/webservice/buscar/ventas/6`

**NOTA:* Tambien puedes usar una herramienta como Postman o similares para consumir el webservice solamente ocupa las URL's mencionadas y realiza la petición **GET**
