# Personal Blog

Este proyecto es un ejercicio práctico basado en la guía de [roadmap.sh](https://roadmap.sh/projects/personal-blog), donde se desarrolló un blog personal utilizando una plantilla HTML proporcionada por [TemplateMo](https://templatemo.com/tm-553-xtra-blog). Se implementó un backend robusto con **Spring Boot**, integrando funcionalidades de seguridad, gestión de usuarios, y persistencia de datos en la nube.

## Características del proyecto

- **Backend**: Implementado con **Spring Boot**.
- **Frontend**: Basado en **Thymeleaf**, adaptando la plantilla HTML proporcionada.
- **Autenticación y autorización**:
    - Implementación de **Spring Security** con dos roles:
        - `USER`: Usuario normal con permisos limitados.
        - `ADMIN`: Usuario administrador con permisos extendidos.
- **Base de datos**: PostgreSQL alojado en la nube con **Supabase**.
- **Puerto predeterminado**: El programa se ejecuta en el puerto `8080`.

## Tecnologías utilizadas

### Backend:
- Java (Spring Boot)
- Spring Security
- Spring Data JPA
- PostgreSQL (Supabase)

### Frontend:
- Thymeleaf
- HTML y CSS (adaptación de la plantilla [Xtra Blog](https://templatemo.com/tm-553-xtra-blog))

### Base de datos:
- PostgreSQL
- Conexión gestionada mediante JDBC

### Seguridad:
- Spring Security con autenticación basada en roles.

## Instalación y configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/AndresGuillermo07/PersonalBlog-SpringBoot.git
   cd Personal-Blog
   mvn clean install
   mvn spring-boot:run

## Acceder a la aplicación:

- URL: http://localhost:8080

### Credenciales iniciales:
- Usuario USER: normal user | Contraseña: user123
- Usuario ADMIN: andres guillermo | Contraseña: andres123

