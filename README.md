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

## Extras
**Material Complementario:** https://drive.google.com/open?id=1F_XmjsXmWdA4tbXcJ7YbM9_iUbmCyAkY

## Funcionalidades

### Lista de URLs API (en construccion..)
```
localhost:8080/alumno                 //GetAll, PostCrear
localhost:8080/alumno/{id}            //GetById, PostAcualizar

localhost:8080/carrera                //GetAll, PostCrear
localhost:8080/carrera/saveAll        //PostCrear por lotes
localhost:8080/carrera/{id}           //GetById, PostAcualizar

localhost:8080/categoria              //GetAll, PostCrear
localhost:8080/categoria/saveAll      //PostCrear por lotes
localhost:8080/categoria/{id}         //GetById, PostAcualizar

localhost:8080/coordinador            //GetAll, PostCrear
localhost:8080/coordinador/{id}       //GetById, PostAcualizar

localhost:8080/director               //GetAll, PostCrear
localhost:8080/director/{id}          //GetById, PostAcualizar

localhost:8080/docente                //GetAll, PostCrear
localhost:8080/docente/{id}           //GetById, PostAcualizar

localhost:8080/escuela                //GetAll, PostCrear
localhost:8080/escuela/saveAll        //PostCrear por lotes
localhost:8080/escuela/{id}           //GetById, PostAcualizar

localhost:8080/panolero               //GetAll, PostCrear
localhost:8080/panolero/{id}          //GetById, PostAcualizar

localhost:8080/producto               //GetAll, PostCrear
localhost:8080/producto/{id}          //GetById, PostAcualizar

localhost:8080/solicitud              //GetAll, PostCrear
localhost:8080/solicitud/{id}         //GetById, PostAcualizar

localhost:8080/subcategoria           //GetAll, PostCrear
localhost:8080/subcategoria/saveAll   //PostCrear por lotes
localhost:8080/subcategoria/{id}      //GetById, PostAcualizar

localhost:8080/usuario                  //GetAll, PostCrear
localhost:8080/usuario/{id}             //GetById, PostAcualizar
localhost:8080/usuario?rut={rut}        //GetByRut
localhost:8080/usuario/perfil/{perfil}  //GetUsuarioPerfil
localhost:8080/usuario/estado/{estado}  //GetUsuarioEstado
...
```

### Body para crear y/o editar (en construccion..)
**Escuela**
```
{
  "nombre": "..."
}
```
```
[
  {
    "nombre": "..."
  },
  {
    "nombre": "..."
  }, etc...
]
```

**Carrera**
```
{
  "nombre": "...",
  "escuela": "...",     --> El ID de la escuela
  "tipo": "..."         --> "tecnico" o "ingeniera"
}
```
```
[
  {
    "nombre": "...",
    "escuela": "...",     --> El ID de la escuela
    "tipo": "..."         --> "tecnico" o "ingeniera"
  },
  {
    "nombre": "...",
    "escuela": "...",     --> El ID de la escuela
    "tipo": "..."         --> "tecnico" o "ingeniera"
  }, etc...
]
```

**Alumno**
```
{
  "rut": "...", 
  "apellidoPaterno": "...", 
  "apellidoMaterno": "...", 
  "nombre": "...", 
  "telefono": "...", 
  "correoAlumno": "...", 
  "carrera": "..."    --> El ID de la carrera
}
```

**Docente**
```
{
  "rut": "...", 
  "apellidoPaterno": "...", 
  "apellidoMaterno": "...", 
  "nombre": "...", 
  "telefono": "...", 
  "correoDocente": "...", 
  "escuela": "..."    --> El ID de la escuela
}
```

**Coordinador**
```
{
  "rut": "...", 
  "apellidoPaterno": "...", 
  "apellidoMaterno": "...", 
  "nombre": "...", 
  "telefono": "...", 
  "correoCoordinador": "...", 
  "escuela": "..."    --> El ID de la escuela
}
```

**Director**
```
{
  "rut": "...", 
  "apellidoPaterno": "...", 
  "apellidoMaterno": "...", 
  "nombre": "...", 
  "telefono": "...", 
  "correoDirector": "...", 
  "escuela": "..."    --> El ID de la escuela
}
```

**Panolero**
```
{
  "rut": "...", 
  "apellidoPaterno": "...", 
  "apellidoMaterno": "...", 
  "nombre": "...", 
  "telefono": "...", 
  "correoPanolero": "..."
}
```

**Usuario**
```
{
  "username": "...",
  "password": "...",
  "perfil": "..."     --> El perfil (ALUMNO, DOCENTE, COORDINADOR, DIRECTO, PANOLERO)
  ---   ---   ---   ---   ---   ---   ---   --
  "rut": "...",       --> Dependendiendo del perfil: Si el perfil es alumno, debe incluir los datos para crear un alumno.
  "apellidoPaterno": "...", 
  "apellidoMaterno": "...", 
  ...
}
```

**Categoria**
```
{
  "nombre": "..."
}
```
```
[
  {
    "nombre": "..."
  },
  {
    "nombre": "..."
  }, etc...
]
```

**Subcategoria**
```
{
  "nombre": "...",
  "categoria": "..."   --> ID de la categoria
}
```
```
[
  {
    "nombre": "...",
    "categoria": "..."   --> ID de la categoria
  },
  {
    "nombre": "...",
    "categoria": "..."   --> ID de la categoria
  }, etc...
]
```

**Solicitud**
```
{
	"solicitud": [
		{
			"comentario": "...",
			"tipo": "...",
			"diasSolicitados": "..." -> dias solicitados si es solicitud especial
			"solicitante": "...",   -> username del usuario qe pide materiales
			"responsable": "..." -> rut del panolero responsable de esta solicitud
		}
	],
	"productos": [
		{
			"id": "..." --> ID del producto
		},
		{
			"id": "..."
		}, etc....
	]
}
```

## Crear productos (con imagen)
En el Postman, se debe hacer mediante `form-data`, En una fila poner la clave `file` y cambiar el tipo de dato a `File`, y en el contenido seleccionar el archivo de imagen. En la siguiente fila poner la clave `body` con el tipo de dato `Text`, y en el contenido, el JSON con los demas datos del producto. Por ejemplo:
```
-----------------------------------------------------------------------------------------
| file			| "seleccionar archivo"						|
-----------------------------------------------------------------------------------------
| body			| {								|
|			|     "nombre": "...",						|
|			|     "descripcion": "...",					|
|			|     "cantidad": "...",					|
|			|     "subcategoria": "..."   --> ID de la subcategoria		|
|			|   }								|
-----------------------------------------------------------------------------------------
```

## Sistema de solicitud de productos

Para solicitar un producto, la solicitud debe ser enviada con el estado `entregada` dentro del JSON, además debe de contener los productos. Por ejemplo:
```
{
	"solicitud": [
		{
			"estado": "entregada"
		}
	],
	"productos": [
		{
			"id": "1"
		},
		{
			"id": "2"
		}
	]
}
```
Solo con esto basta para que se busquen los productos y queden en estado de `enuso` del stock total. 

Por otro lado, si se envia la solicitud con estado `completada` con sus respectivos productos, entonces el `ESTADO` del producto volverá a ser `disponible`.
