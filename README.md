# MovieApp
Aplicación móvil para la plataforma Android que consulta la API [TheMovieDB](https://developers.themoviedb.org/) para obtener informacion de peliculas recientes o populares.

 
<br/>

## Contenido
1. [Información general](#información-general)
2. [Construido con](#construido-con)
3. [Estructura del proyecto](#estructura-del-proyecto)

</br>

## Información general
La aplicación es un ejemplo de tipo **master-detail** que cuenta con dos pantallas.

La primera en la que se muestra una lista de las películas que se obtienen a consultar la API publica [TheMovieDB](https://developers.themoviedb.org/), al seleccionar uns película se abrirá la segunda pantalla en la que se vera la información de la película elegida.

Al momento de consultar los  datos desde la api de [TheMovieDB](https://developers.themoviedb.org/) la aplicacion los almacena en una base de datos local realizada con Room, lo que permite que funcione offline (no obstante, se requiere que la primera vez que se use se cuente con conexión a internet).


<br/>

## Construido con
- [MVVM pattern](#construido-con): es el lenguaje de programación recomendado por la comunidad de **Android developers** para desarrollar aplicaciones moviles para la pratadorma Android.
- [Kotlin](https://kotlinlang.org/docs/android-overview.html): es el lenguaje de programación recomendado por la comunidad de **Android developers** para desarrollar aplicaciones moviles para la pratadorma Android.
- [Retrofit](https://square.github.io/retrofit/): es una librería que simplifica las tareas de llamadas de red a una API.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - librería que simplifica la tarea de trabajar con bases de datos en Android.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android): es una librería que proporciona una forma estándar de implementar la inyección de dependencias de una manera mas fácil.
- [Glide](https://bumptech.github.io/glide/): es una librería que permite la carga rapida de imagenes desde la red mediante url.
- [Jetpack Navigation Component](https://developer.android.com/guide/navigation): la navegación se refiere a las interacciones que permiten a los usuarios navegar, entrar y salir de las diferentes secciones de contenido dentro de su aplicación.

<br/>

## Estructura del proyecto
Para este proyecto se implemento el **Patrón de arquitectura MVVM** además de usar algunas practicas de la **arquitectura limpia**, para así desacoplar lo más posible la _lógica de dominio_ de la _ui_ y la _persistensi de datos_. La aplicacion se divide pricipalmente en tres capas: **data**, **domain** y **ui**.

Además estan los ditrectorios **core** (elementos centrales de la aplicacion en general), **utils** (utilidades necesarias para algunos procesos de la aplicación) y **di** (contiene los modulos de Hilt de proveedores de instancias que no pueden ser injectadas por constructor). 

### Capa data
La capa de data es la encargada de manejar la persistencia de los datos de la aplicación, dado que la aplicacion debe obtener datos desde Una API y funcionar offline, se decidio dividir la capa de datos en tres directorios:
- **Network**: donde se colocó el servivio para consultar la API y los Modelos de datos en donde almacenaremos la información obtenida desde la API. 
- **Local**: contendra el servicio de la base de datos local con Room, las _entidades_ en donde se almacenaran los datos y los _Dao's_ para acceder a ellos.
- **Repository**: desde aquí se decidira cuando se debe _consultar la API_ y cuando se obtendrán los datos desde la _base de datos local_, además se encargará de mapear los _modelos_ y _entidades_ a el tipo de datos que necesita el dominio.

### Capa domain
Se encargará de manejar la lógica de negocio de la aplicación. Dentro de esta capa se encuentran los directorios:
- **Usecases**: contiene los **casos de uso** referentes a la lógica de negocio de la applicación, ejemplo: **Obtener películas**, **Obtener videos de pelicula**. Estos casos de uso no deben saber desde donde se obtienen los datos, solo los obtienen mediante el repositorio. 
- **models**: contiene los modelos de dominio en el que almacenara la información que retornará el **repository** (_**Nota:** este modelo de dominio puede que tenga los mismos atributos que los modelos obtenidos desde la API o las entidades de la base de datos local, pero se decidio que fuera un objeto diferente para mantener lo mas desacoplado el dominio de la capa de datos_).

### Capa ui
Esta capa contiene todas las clases referentes a la interfaz de usuario y como se presentan los datos en pantalla.
de la app:
- **movielist**: contiene el fragment y el viewModel para mostrar la lista de peliculas al usuario.
- **moviedetails**: contiene el fragment y el viewModel necesario para mostrar los detalled de la pelicula seleccionada en la lista de películas.

Cada viewModel tiene una referencia a el caso de uso que se necesita para optener la información que se le mostrará al usuario.

<br/>

### Organización de los directorios
```
  |--- core
  |--- data
  |     |--- network
  |     |     |--- models
  |     |     |--- api
  |     |     |
  |     |--- local
  |     |     |--- dao
  |     |     |--- entities
  |     |     |--- database
  |     |     |
  |     |--- repository
  |     |
  |--- di
  |--- domain
  |     |--- models
  |     |--- usecases
  |     |
  |--- ui
  |     |--- movielist
  |     |--- moviedetail
  |     |
  |--- utils

```

<br/>
<br/>
<br/>


