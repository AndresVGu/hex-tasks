# 📋 Tasks Application - Hexagonal Architecture

Una aplicación de **gestión de tareas** construida con **Spring Boot 3** y **Arquitectura Hexagonal**, implementando buenas prácticas de clean architecture y un diseño completamente desacoplado.

## 📖 Descripción

Esta es una aplicación REST API que permite crear, leer, actualizar y eliminar tareas. Implementa la **Arquitectura Hexagonal** (también conocida como "Puertos y Adaptadores") para lograr un código limpio, testeable e independiente de frameworks externos.

## 🏗️ Arquitectura Hexagonal

La arquitectura hexagonal divide la aplicación en tres capas principales:

### 1. **Domain** (Núcleo - Dominio de Negocio)
```
domain/
├── models/          # Entidades de negocio puras (Task, AdditionalTaskInfo)
└── ports/           # Interfaces que definen contratos
    ├── in/          # Casos de uso (puertos de entrada)
    └── out/         # Adaptadores de persistencia (puertos de salida)
```
- **Independiente** de cualquier framework
- Contiene la **lógica de negocio pura**
- Define contratos mediante interfaces

### 2. **Application** (Casos de Uso)
```
application/
├── services/        # Orquestadores de casos de uso
└── usecases/        # Implementaciones de los casos de uso
```
- Orquesta la **lógica de negocio**
- No depende de frameworks externos
- Implementa los puertos definidos en domain

### 3. **Infrastructure** (Adaptadores)
```
infrastructure/
├── adapters/        # Adaptadores para servicios externos
├── config/          # Configuración de Spring
├── controllers/     # Controladores REST
├── entities/        # Entidades JPA
└── repositories/    # Adaptadores de persistencia
```
- Integración con **Spring Boot**
- Acceso a base de datos
- Exposición de endpoints REST

## 📁 Estructura del Proyecto

```
tasks/
├── src/
│   ├── main/
│   │   ├── java/com/hexagonal/tasks/
│   │   │   ├── TasksApplication.java          # Punto de entrada
│   │   │   ├── domain/
│   │   │   │   ├── models/
│   │   │   │   │   ├── Task.java
│   │   │   │   │   └── AdditionalTaskInfo.java
│   │   │   │   └── ports/
│   │   │   │       ├── in/                    # Casos de uso (puertos entrada)
│   │   │   │       │   ├── CreateTaskUseCase.java
│   │   │   │       │   ├── DeleteTaskUseCase.java
│   │   │   │       │   ├── GetAdditionalTaskInfoUseCase.java
│   │   │   │       │   ├── RetrieveTaskUseCase.java
│   │   │   │       │   └── UpdateTaskUseCase.java
│   │   │   │       └── out/                   # Puertos salida
│   │   │   │           └── TaskRepositoryPort.java
│   │   │   ├── application/
│   │   │   │   ├── services/
│   │   │   │   │   └── TaskService.java
│   │   │   │   └── usecases/
│   │   │   │       ├── CreateTaskUseCaseImpl.java
│   │   │   │       ├── DeleteTaskUseCaseImpl.java
│   │   │   │       ├── GetAdditionalTaskInfoUseCaseImpl.java
│   │   │   │       ├── RetrieveTaskUseCaseImpl.java
│   │   │   │       └── UpdateTaskUseCaseImpl.java
│   │   │   └── infrastructure/
│   │   │       ├── adapters/
│   │   │       │   └── ExternalServiceAdapter.java
│   │   │       ├── config/
│   │   │       │   └── ApplicationConfig.java
│   │   │       ├── controllers/
│   │   │       │   └── TaskController.java
│   │   │       ├── entities/
│   │   │       │   └── TaskEntity.java
│   │   │       └── repositories/
│   │   │           ├── JpaTaskRepository.java
│   │   │           └── JpaTaskRepositoryAdapter.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/hexagonal/tasks/
│           └── TasksApplicationTests.java
├── pom.xml                                    # Dependencias Maven
├── mvnw                                       # Maven Wrapper
├── mvnw.cmd                                   # Maven Wrapper (Windows)
└── README.md                                  # Este archivo
```

## 🚀 Requisitos Previos

- **Java 21** o superior
- **Maven 3.6+** (o usar mvnw incluido)
- **MySQL 8.0+**
- **Git** (opcional)

## 📦 Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/tasks.git
cd tasks
```

### 2. Crear la base de datos MySQL
```sql
CREATE DATABASE tasks_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar la conexión a base de datos
Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tasks_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

### 4. Compilar y ejecutar
```bash
# Compilar el proyecto
./mvnw clean package -DskipTests

# Ejecutar la aplicación
./mvnw spring-boot:run
```

O ejecutar directamente el JAR:
```bash
java -jar target/tasks-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en `http://localhost:8080`

## 📡 Endpoints de la API REST

### Base URL: `/api/tasks`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/tasks` | Crear una nueva tarea |
| `GET` | `/api/tasks` | Obtener todas las tareas |
| `GET` | `/api/tasks/{id}` | Obtener una tarea por ID |
| `PUT` | `/api/tasks/{id}` | Actualizar una tarea |
| `DELETE` | `/api/tasks/{id}` | Eliminar una tarea |
| `GET` | `/api/tasks/{id}/additionalInfo` | Obtener información adicional de una tarea |

### Ejemplos de Uso

#### 1. Crear una tarea
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Lista de tareas",
    "description": "Crear aplicación usando arquitectura hexagonal",
    "completed": false
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "title": "Lista de tareas",
  "description": "Crear aplicación usando arquitectura hexagonal",
  "creationDate": "2026-04-27T14:30:45.123456",
  "deletedAt": null,
  "completed": false
}
```

#### 2. Obtener todas las tareas
```bash
curl -X GET http://localhost:8080/api/tasks
```

#### 3. Obtener una tarea por ID
```bash
curl -X GET http://localhost:8080/api/tasks/1
```

#### 4. Actualizar una tarea
```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Lista de tareas actualizada",
    "description": "Descripción modificada",
    "completed": true
  }'
```

#### 5. Eliminar una tarea
```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

#### 6. Obtener información adicional
```bash
curl -X GET http://localhost:8080/api/tasks/1/additionalInfo
```

## 🗄️ Estructura de la Base de Datos

### Tabla: `task_entity`

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | BIGINT | Identificador único (PK, AUTO_INCREMENT) |
| `title` | VARCHAR(255) | Título de la tarea |
| `description` | LONGTEXT | Descripción detallada |
| `creation_date` | DATETIME | Fecha de creación (se asigna automáticamente) |
| `deleted_at` | DATETIME | Fecha de eliminación (soft delete) |
| `completed` | BOOLEAN | Estado de completitud |

## 🛠️ Tecnologías Utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.14** - Framework web
- **Spring Data JPA** - Persistencia de datos
- **Hibernate** - ORM
- **MySQL 8** - Base de datos relacional
- **Maven** - Gestor de dependencias y construcción
- **Jakarta Persistence** - Especificación de persistencia

## 📋 Dependencias Principales

```xml
<!-- Spring Boot Web Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL Connector -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>

<!-- Spring Boot Test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## 💡 Características Implementadas

✅ **CRUD Completo** - Crear, leer, actualizar y eliminar tareas

✅ **Arquitectura Hexagonal** - Separación clara de responsabilidades

✅ **Casos de Uso Independientes** - Cada caso de uso es una clase separada

✅ **Puertos y Adaptadores** - Desacoplamiento de la infraestructura

✅ **Persistencia con JPA/Hibernate** - Acceso a base de datos

✅ **API REST** - Endpoints RESTful

✅ **Inyección de Dependencias** - Configuración automática mediante Spring

✅ **Validación de Nulabilidad** - Control de creationDate automático

## 🔧 Configuración Adicional

### Habilitar logs de SQL (ya incluido)
```properties
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```

### DDL Auto (actualización automática de esquema)
```properties
spring.jpa.hibernate.ddl-auto=update
```

Opciones disponibles: `create-drop`, `create`, `update`, `validate`, `none`

## 📝 Notas Importantes

- **CreationDate automático**: Al crear una tarea sin especificar `creationDate`, se asigna automáticamente la fecha y hora actual del servidor.

- **Soft Delete**: El campo `deletedAt` permite implementar borrado lógico (la tarea no se elimina físicamente).

- **Desacoplamiento**: El dominio no tiene ninguna dependencia con Spring o JPA, permitiendo testear la lógica de negocio sin infraestructura.

## 🧪 Testing

```bash
# Ejecutar todas las pruebas
./mvnw test

# Ejecutar con cobertura
./mvnw test -Drepository=true
```

## 🤝 Contribuciones

Las contribuciones son bienvenidas.

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles.

## 👨‍💻 Autor

**Andrés** - [GitHub](https://github.com/tu-usuario)

## 📞 Contacto

Para preguntas o sugerencias, por favor abre un [issue](https://github.com/tu-usuario/tasks/issues) en el repositorio.

---

**Última actualización:** Abril 2026

