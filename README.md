# JdbcSwingCrudProyect

## Descripción

`JdbcSwingCrudProyect` es una aplicación de escritorio desarrollada en Java que utiliza Swing para la interfaz gráfica y JDBC para interactuar con una base de datos MySQL. La aplicación permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una tabla de productos.

## Características

- Interfaz gráfica de usuario (GUI) desarrollada con Swing.
- Conexión a una base de datos MySQL utilizando JDBC.
- Operaciones CRUD para gestionar productos.
- Validaciones de entrada para asegurar datos válidos.
- Interacción con la base de datos para almacenar y recuperar datos de productos.

## Requisitos

- Java Development Kit (JDK) 8 o superior.
- MySQL Server.
- Biblioteca JDBC para MySQL.

## Instalación

1. **Clonar el repositorio:**

   ```sh
   git clone https://github.com/tu-usuario/JdbcSwingCrudProyect.git
   cd JdbcSwingCrudProyect
   ```

2. **Configurar la base de datos:**

   - Crea una base de datos MySQL llamada `product_db`.
   - Ejecuta el siguiente script SQL para crear la tabla `products`:

     ```sql
     CREATE DATABASE product_db;

     USE product_db;

     CREATE TABLE products (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         name VARCHAR(255) NOT NULL,
         price INT NOT NULL,
         quantity INT NOT NULL
     );
     ```

3. **Configurar la conexión a la base de datos:**

   - Abre el archivo `ProductRepositoryImp.java` y configura los parámetros de conexión a la base de datos (URL, usuario y contraseña).

     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/product_db";
     private static final String USER = "tu_usuario";
     private static final String PASSWORD = "tu_contraseña";
     ```

4. **Compilar y ejecutar la aplicación:**

   - Compila el proyecto utilizando Maven o tu herramienta de construcción preferida.
   - Ejecuta la aplicación principal `JdbcSwingCrudProyect`.

     ```sh
     java -cp target/JdbcSwingCrudProyect-1.0-SNAPSHOT.jar co.jmurillo.java.swing.jdbc.JdbcSwingCrudProyect
     ```

## Uso

1. **Agregar un nuevo producto:**
   - Completa los campos de nombre, precio y cantidad.
   - Haz clic en el botón "Guardar".

2. **Editar un producto:**
   - Haz clic en una fila de la tabla para cargar los datos del producto en el formulario.
   - Modifica los campos necesarios y haz clic en "Guardar".

3. **Eliminar un producto:**
   - Haz clic en el botón "Eliminar" en la fila correspondiente del producto que deseas eliminar.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request con tus mejoras.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarme en [Jeferson Murillo](https://www.linkedin.com/in/jeferson-antonio-murillo-palacio-4a25671b3/).

---

¡Gracias por usar `JdbcSwingCrudProyect`!
