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

### Creamos las interfaces in
- Dentro de la carpeta ports/in UpdateTaskUseCase, RetrieveTaskUseCase, DeleteTaskUseCase, GetAdditionalTaskInfoUseCase, CreateTaskUseCase
