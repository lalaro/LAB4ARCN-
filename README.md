# Laboratorio: Creación de un Microservicio "Hello World" con Spring Boot, Docker y Play with Docker

## Introducción
Este laboratorio guiará a través de los pasos necesarios para crear un microservicio básico "Hello World" utilizando Spring Boot, GitHub, GitHub Codespaces y Play with Docker.

---

## Paso 1: Configuración del Repositorio en GitHub

### Crear un Repositorio en GitHub:
1. Ve a [GitHub](https://github.com/) y accede a tu cuenta.
2. Haz clic en "New" para crear un nuevo repositorio.
3. Asigna un nombre al repositorio, por ejemplo: `LAB4ARCN-`.
4. Inicializa el repositorio con un archivo `README.md`.

---

## Paso 2: Configuración de GitHub Codespaces

### Crear un Codespace:
1. En la página principal del repositorio, haz clic en el botón **"Code"** y selecciona **"Create codespace on main"**.
2. Esto abrirá un entorno de desarrollo en la nube donde podrás editar y ejecutar tu proyecto.

### Configurar Codespace:
1. Crea un archivo con el nombre `.devcontainer/devcontainer.json`.
2. Pega la siguiente configuración:

    ```json
    {
        "name": "Java Dev Environment",
        "image": "mcr.microsoft.com/devcontainers/java:1-21",
        "features": {
            "ghcr.io/devcontainers/features/java:1": {
                "version": "none",
                "installMaven": "true",
                "mavenVersion": "3.8.6",
                "installGradle": "false"
            },
            "ghcr.io/devcontainers/features/docker-in-docker:1": {}
        },
        "customizations": {
            "vscode": {
                "extensions": [
                    "vscjava.vscode-java-pack",
                    "streetsidesoftware.code-spell-checker",
                    "pivotal.vscode-spring-boot",
                    "vmware.vscode-boot-dev-pack"
                ]
            }
        },
        "postCreateCommand": "mvn clean install"
    }
    ```

### Asegúrate de la instalación de Java y Maven:
Ejecuta los siguientes comandos en la terminal para verificar las versiones:

```bash
java -version
mvn -version
```

---

## Paso 3: Configuración del Proyecto Spring Boot

### Generar el Proyecto Spring Boot:
1. Asegúrate de que tienes la extensión **Spring Boot Extension Pack** instalada.
2. Dirígete a la barra **Command Palette** con `Ctrl+Shift+P`.
3. Busca y selecciona: **Spring Initializr: Create a Maven Project**.
4. Configura el proyecto con las siguientes opciones:
   - **Language:** Java
   - **Spring Boot:** Última versión estable
   - **Group:** `com.eci.arcn`
   - **Artifact:** `microservice-helloworld`
   - **Name:** `microservice-helloworld`
   - **Packaging:** Jar
   - **Java Version:** 17
   - **Dependencia:** Spring Web
5. Haz clic en **"Generate"** para descargar el proyecto.

> **Nota:** Una vez creado el proyecto, mueve los archivos a la raíz del workspace.

---

## Paso 4: Implementación del Servicio "Hello World"

### Crear el Controlador:
En **GitHub Codespaces**, navega a `src/main/java/com/eci/arcn/microservice-helloworld` y crea una nueva clase llamada **`HelloWorldController.java`** con el siguiente contenido:

```java
package com.eci.arcn.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

### Configurar la Aplicación:
Asegúrate de que la clase principal del proyecto esté anotada con `@SpringBootApplication`.

### Ejecutar la Aplicación:
Utiliza el terminal integrado en Codespaces para ejecutar tu aplicación con el siguiente comando:

```bash
mvn spring-boot:run
```

![image1.jpeg](src/main/resources/image1.jpeg)


Verifica que la aplicación se ejecuta correctamente ejecutando:

```bash
curl http://localhost:8080/hello
```

![image6.jpeg](src/main/resources/image6.jpeg)

---

## Paso 5: Crear y Subir la Imagen Docker

### Crear el Dockerfile:
En la raíz del proyecto, crea un archivo llamado **Dockerfile** con el siguiente contenido:

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/microservice-helloworld.jar microservice-helloworld.jar
ENTRYPOINT ["java", "-jar", "/microservice-helloworld.jar"]
```

### Construir la Imagen Docker:
Ejecuta el siguiente comando para compilar el proyecto y generar el archivo JAR:

```bash
mvn clean package
```

Construye la imagen Docker con el comando:

```bash
docker build -t microservice-helloworld .
```

### Subir la Imagen a Docker Hub:
1. Asegúrate de tener/crear una cuenta en [Docker Hub](https://hub.docker.com/).
2. Etiqueta la imagen con tu nombre de usuario de Docker Hub:

    ```bash
    docker tag microservice-helloworld lalaro/pruebarcn
    ```

3. Cierra sesión en Docker Hub en caso de estar autenticado:

    ```bash
    docker logout
    ```

4. Inicia sesión en Docker Hub:

    ```bash
    docker login -u lalaro
    ```

5. Sube la imagen a Docker Hub:

    ```bash
    docker push lalaro/pruebarcn
    ```

![image2.jpeg](src/main/resources/image2.jpeg)
![image3.jpeg](src/main/resources/image3.jpeg)
![image4.jpeg](src/main/resources/image4.jpeg)
![image5.jpeg](src/main/resources/image5.jpeg)

![image7.jpeg](src/main/resources/image7.jpeg)
---

## Paso 6: Ejecutar el Servicio en Play with Docker

### Acceder a Play with Docker:
1. Ve a [Play with Docker](https://labs.play-with-docker.com/) e inicia sesión con tu cuenta de Docker Hub.
2. Haz clic en **"Start"** para crear una nueva instancia de Docker.

### Ejecutar el Contenedor:
En Play with Docker, ejecuta el contenedor con el siguiente comando:

```bash
docker run -p 8080:8080 lalaro/pruebarcn
```
![image8.jpeg](src/main/resources/image8.jpeg)
### Probar el Servicio:
Accede al servicio desde el enlace proporcionado por Play with Docker, añadiendo `/hello` al final de la URL para ver el mensaje **"Hello, World!"**.

![image9.jpeg](src/main/resources/image9.jpeg)


## Construido con

* [Maven](https://maven.apache.org/) - Gestión de dependencias.
* [Java](https://www.java.com/es/) - Versionamiento en Java.
* [GitHub](https://docs.github.com/es) - Sistema de control de versiones distribuido.
* [IntelliJ](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado.
* [Docker](https://docs.docker.com/) - Tecnología en contenedores que permite crear y usar contenedores Linux.
* [Spring Boot](https://docs.spring.io/spring-boot/index.html) -Herramienta de código abierto para desarrollar aplicaciones web y microservicios en Java

## Contribuyendo

Por favor, lee [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) para detalles sobre nuestro código de conducta y el proceso para enviarnos solicitudes de cambios (*pull requests*).

## Versionado

Usamos [SemVer](http://semver.org/) para el versionado.

## Autores

* **Laura Valentina Rodríguez Ortegón** - *Lab4ARCN* - [Repositorio](https://github.com/lalaro/LAB4ARCN-.git)

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Reconocimientos

* Agradecimientos a la Escuela Colombiana de Ingeniería
* La documentación de Git Hub
