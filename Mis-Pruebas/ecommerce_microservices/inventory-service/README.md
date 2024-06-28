# Configuración microservicio
- Modificamos el pom.xml con la información del proyecto padre.
  - Modificamos la etiqueta parent de la siguiente manera:
    - '<parent>
        <groupId>com.ecommerce_microservices</groupId>
        <artifactId>ecommerce_microservices</artifactId>
        <version>1.0.0-SNAPSHOT</version>
      </parent>'
- Continuamos configurando el microservicio en el application.properties.
  - Añadimos la configuración de la BBDD.
  - Añadimos la configuración de la aplicación.properties