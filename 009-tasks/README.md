# Proyecto demo de Arquitectura Hexagonal en Java

Este proyecto es el primero utilizando arquitectura hexagonal

## Pasos:
### Carpetas:
- Empezamos creando las distintas capas que existen en este modelo (domain, application, infrastructure)
- Dentro de domain, creamos models, ports. Dentro de ports diferenciamos entre inputs y outputs.
- Dentro de application, creamos services y usecases
- Dentro de infrastructure, creamos adapters, config, controllers, entities, repositories

### Creamos los modelos
- Dentro de domain/models, creamos AditionalTaskInfo y Tasks

### Creamos las interfaces in/out
- Dentro de la carpeta ports/in UpdateTaskUseCase, RetrieveTaskUseCase, DeleteTaskUseCase, GetAdditionalTaskInfoUseCase, CreateTaskUseCase
- Dentro de la carpeta ports/out ExternalServicePort, TaskRepositoryPort
- Con estos pasos ya tendríamos finalizada la capa de dominio

### Continuamos con la capa de aplicación, en esta capa implementaremos la lógica de negocio
#### Creamos el services (taskService) y el usecases creamos las implementaciones de ports/in 
#### Implementamos todos los usecases, con sus respectivos métodos definidos en las interfaces

### Continuamos con la capa de infraestructura
#### Empezamos creando las clases TaskEntity, los repositorios, el controllador, la configuracion y los adaptadores
