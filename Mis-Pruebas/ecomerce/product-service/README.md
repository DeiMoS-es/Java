# Primeros pasos:
- En la web de: https://start.spring.io/ creamos el proyecto con las siguientes dependencias:
  - Soring Boot DevTools
  - Spring Web
  - Spring Data JPA
  - MySQL Driver
  - Spring Boot Actuator
  - Lombok
  - Validation
- Además le damos los siguientes nombres en:
  - Group: com.ecomerce
  - Artifact: product-service
  - Name: product-service
  - Description: Demo project for Spring Boot
  - Package name: com.ecomerce.productservice
## Una vez creado el proyecto:
- Lo importamos en nuestro IDE de preferencia.
- Creamos la base de datos, yo he optado por MySQL.
- En el archivo application.properties configuramos la conexión a la base de datos.
- Creamos la entidad Product con los siguientes atributos:
  - id
  - name
  - description
  - stock
  - price
- Y ya continuamos creando los repositorios, servicios y controladores.
## Si queremos probar a arrancar el proyecto:
- Podemos hacerlo desde el IDE o desde la terminal con el comando: mvn spring-boot:run
## Siguiendo los pasos de Test Driven Development:
- Una vez creada la entidad Product, creamos el test de la entidad.
- Y seguimos creando los test de los repositorios, servicios y controladores.
### Hay que diferenciar cuando queremos realizar test usando mocks y cuando no.
- Cuando los realizamos sin mocks, es porque ya tenemos implementados nuestros métodos y queremos comprobar que funcionan correctamente.
- Usaremos mocks para seguir los pasos de TDD, ya que aún no hemos implementado los métodos.