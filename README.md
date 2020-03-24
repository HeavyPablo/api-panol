# api-panol
API para el proyecto de pañol.

## Comenzando
Para iniciar el proyecto una vez clonado:
- Abrir el proyecto en un IDE e instalar las dependencias (normalmente se instalan solas). Si estan en **IntelliJ**, 
cuando abran el proyecto abajo les saldran 2 ventanas de advertencia (una por las dependencias y otra por el firewall)
a una le dan a **"fix"** y luego a **"automatic install"**, a la otra le dan a **"install ... etc etc"**.
- Una vez instalas las dependencias, deben ir a **SQL DEVELOPER** y crear un usuario en **system** y crear una **BD** con ese usuario.
- Para conectarse a la **BD**, deben ir al proyecto y abrir el archivo **aplication.properties** que esta en `api-panol\src\main\resources`
y modificar los siguientes campos:
```
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=STIM
spring.datasource.password=123456
```
en **username** el usuario, y en **password** la contraseña del usuario.
- Para correr el proyecto, deben ejecutar por consola
```
mvnw spring-boot:run
```
Cuando corran el comando les creara las tablas y todas las weas de relaciones etc etc. jajaj.

***Solo estan disponible de las tablas ALUMNOS, CARRERAS y ESCUELAS para las siguientes operaciones:***
- **obtener todos**
- **obtener por id**
- **crear**
- **actualizar**
