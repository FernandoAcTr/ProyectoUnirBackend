# Proyecto UNIR Backend

## Herramientas Necesarias

- [Java JDK 17](https://openjdk.org/projects/jdk/17/): Entorno de ejecución para aplicaciones Java.
- [Atlas](https://atlasgo.io/): Necesario para la gestión y ejecución de migraciones de base de datos.
- [Task](https://taskfile.dev/#/installation): Opcional. Facilita la ejecución de comandos.
- [MySQL](https://dev.mysql.com/downloads/mysql/): Base de datos relacional.
- [Maven](https://maven.apache.org/download.cgi): Herramienta de gestión de proyectos Java.

## Directorios Principales

El proyecto está dividido en dos directorios principales:

- `bussiness`: Contiene los microservicios que manejan la lógica de negocio.
- `infrastructure`: Contiene la configuración y componentes de infraestructura necesarios para soportar los microservicios.

## Microservicios

### Auth

El microservicio de `auth` se encarga de la autenticación y autorización de los usuarios. Proporciona endpoints para el registro, inicio de sesión y gestión de tokens.

### Products

El microservicio de `products` maneja la gestión de productos. Permite crear, actualizar, eliminar y listar productos disponibles en el sistema.

### Orders

El microservicio de `orders` se encarga de la gestión de pedidos. Permite crear, actualizar, eliminar y listar pedidos realizados por los usuarios.

## Setup Proyecto

### Base de Datos

El proyecto utiliza una base de datos Mysql, para esto debes tener instalado de manera local el servidor de base de datos Mysql.

#### Crear Base de Datos

Navega al directorio `infrastructure/database` y ejecuta el siguiente comando:

```bash
atlas migrate apply --dir "file://migrations" --url "tu_url_de_conexion_a_mysql"
```

O si tienes `Task` instalado. Crea un archivo .env en el directorio `infrastructure/database` y configura la url de conexión a Mysql. Puedes revisar el archivo .env.example para ver un ejemplo. Una vez configurada la url de conexión, ejecuta el siguiente comando:

```bash
task migrate 
```

Nota: La base de datos debe existir previamente, vacía. Tú decides el nombre que se reflejará en tu cadena de conexión.

### Configuración de microservicios

Los microservicios tienen cada uno un archivo de configuración `.properties` que deben de configurarse de manera independiente. Se encuentran dentro de la carpeta `src/main/resources` de cada microservicio.
Puedes modificar los valores de las propiedades para que se ajusten a tu entorno, en los 3 lo que usualmente se modifica es la base de datos, usuario y contraseña. Los archivos forman parte del repositorio de git, por lo que no deben incluirse las modificaciones a estos. 

Nota: Si no quieres modificar los archivos originales `applicacion.properties`, puedes clonarlos en archivos de nombre `application-local.properties`, los cuales son ignorados por git, deberás indicar este perfil al ejecutar el microservicio. Un perfil se puede indicar como argumento `-Dspring.profiles.active`  de `java jar`. Ejemplo.

```bash
java -jar -Dspring.profiles.active=local target/auth-0.0.1-SNAPSHOT.jar
```

## Ejecutar Microservicios

Una vez configurada la base de datos y los archivos de configuración de los microservicios, puedes ejecutar cada uno de ellos de manera independiente. Pero asegurate de tener los microservicios eurekaserver y apigateway corriendo para que los microservicios se registren y puedan ser consumidos.

Api Gateway por defecto en el proyecto está configurado para correr en el puerto 8085, por lo que todas las peticiones a los microservicios deben ser realizadas a través de este. Ejemplo: 

```bash
http://localhost:8085/auth/login
```

Api Gateway, junto con Eureka se encargarán de resolver las peticiones a los microservicios registrados.

Para ejecutar los microservicios, navega al directorio de cada uno de ellos y ejecuta el siguiente comando:

```bash
# Modo Desarrollo (con perfil local)
mvn spring-boot:run -Dspring-boot.run.profiles=local

# O usando Task
task dev
```

## Compilar Microservicios

Para compilar los microservicios, navega al directorio de cada uno de ellos y ejecuta el siguiente comando:

```bash
mvn clean package

# O usando Task
task build
```

## Ejecutar binarios

Una vez compilados los microservicios, navega al directorio `target` de cada uno de ellos y ejecuta el siguiente comando:

```bash
java -jar target/nombre-del-microservicio.jar

# O usando Task
task run
```