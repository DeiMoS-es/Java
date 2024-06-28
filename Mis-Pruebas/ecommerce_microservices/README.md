# Proyecto Ecommerce basado en microservicios.

## Primeros pasos:
- Comenzaremos creando un proyecto de spring sin ninguna dependencia. Utilizaremos la web: https://start.spring.io/
- Le daremos como valores a los campos:
  - Group: com.ecommerce_microservicios
  - Artifac: ecommerce
  - Name: microservices_ecommerce
- Una vez creado, este será el "proyecto padre" en el cual añadiremos los demás microservicios.
- De este proyecto padre solo nos quedaremos con los archivos .gitignore y pom.xml, eliminando el resto.

## Creación de microservicios:
- Continuamos con la creación del microservicio de inventario.
  - Creamos el microservicio de inventario con las siguientes dependencias:
    - Spring Boot DevTools
    - Lombok
    - Spring Configuration Processor
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
  - Creamos el miscroservicio de productos con las siguientes dependencias:
    - Spring Boot DevTools
    - Lombok
    - Spring Configuration Processor
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
  - Creamos el microservicio de órdenes (pedidos) con las siguientes dependencias:
    - Spring Boot DevTools
    - Lombok
    - Spring Configuration Processor
    - Spring Web
    - Spring Data JPA
    - MySQL Driver
> [!WARNING] Para implementar de forma correcta una arquitectura de microservicios, cada microservicio debería de estar conectado a su propia BBDD. Para este caso lo conectaremos todo a la misma BBDD.
## Configuración de los microservicios:
- En el pom.xml del proyecto padre:
  - Eliminamos la etiqueta: <name>ecommerce_microservices</name>
  - Modificamos la etiqueta: 
    - '<properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      </properties>'
  - Eliminamos las dependencias y eliminamos la etiqueta plugin (ojo, en singular).
  - Añadimos los modulos (servicios) creados anteriormente:
    - '<modules>
        <module>inventory-service</module>
        <module>order-service</module>
        <module>products-service</module>
      </modules>'
- Continuamos la configuración de los microservicios restante como indica el READEME.md.
- Una vez terminado recargamos el proyecto en nuestro IDE.