# Aplicación Spring Boot: Gestión de alumnos

Este proyecto es una aplicación de Spring Boot diseñada para gestionar entidades `alumno` (alumnos). Demuestra el uso de Spring Boot, para construir un servicio RESTful.

## Características

- Obtener todos los registros de `alumno` que esten con estado activos.
- Agregar nuevos registros de `alumno` con validaciones en los atributos.
- Construido con Java 17 y Spring Boot.
- Sigue una arquitectura por capas (controladores, servicios y repositorios).

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **R2DBC** (Uso de H2 como base de datos en memoria para pruebas)
- **Hibernate Reactive**
- **Maven**
- **Lombok**
- **JUNIT**
- **mockito**

## Prerrequisitos

- Java 17
- Maven 3.8+

## Instalación y Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/Codeted023/alumnoService.git
   ```
2. **Construir el proyecto**:
   ```bash
   mvn clean install
   ```
4. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```
5. **Acceder a la aplicación**:
   - URL base de la API: `http://localhost:8080`

## Endpoints de la API

### `GET /alumnos`

Obtiene todos los registros de `alumno` en estado activo.

#### Respuesta:

```json
[
  {
    "id": 1,
    "nombre": "Andre",
    "apellido": "Gonzales",
    "estado": "activo",
    "edad": 22
  },
  {
    "id": 2,
    "nombre": "Ana",
    "apellido": "Alas",
    "estado": "activo",
    "edad": 20
  }
]
```

### `POST /alumnos`

Agrega un nuevo registro de `alumno`.

#### Cuerpo de la solicitud:

```json
{
  "id": 1,
  "nombre": "Andre",
  "apellido": "Gonzales",
  "estado": "activo",
  "edad": 22
}
```

#### Respuesta:
En caso de realizarse correctamente la grabación devuelve una respuesta vacía con estado 201.

## Estructura del Proyecto

```
src
├── main
│   ├── java
│   │   └── com.dfp.student_service
│   │       ├── controller
│   │       │   └── AlumnoController.java
│   │       ├── exception
│   │       │   └── GlobalExceptionHandler.java
│   │       ├── model
│   │       │   └── Alumno.java
│   │       ├── repository
│   │       │   └── AlumnoRepository.java
│   │       ├── service
│   │       │   ├── AlumnoService.java
│   │       │   ├── impl
│   │       │   │   └── AlumnoServiceImpl.java
│   │       │   └── DataSetupService.java
│   │       ├── utils
│   │       │   └── Constants.java
│   │       └── StudentServiceApplication.java
│   └── resources
│       ├── h2
│       │   └── init.sql
│       └── application.properties
└── test
    └── java
        └── com.dfp.student_service
            ├── controller
            │   └── AlumnoControllerTest.java
            ├── service
            │   ├── AlumnoServiceImplTest.java
            │   └── DataSetupServiceTest.java
            └── StudentServiceApplicationTests.java
```