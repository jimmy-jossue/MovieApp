# MovieApp
Aplicación móvil para la plataforma Android que consulta la API [TheMovieDB](https://developers.themoviedb.org/) para obtener informacion de peliculas recientes o populares.

 _Esta aplicación se creo para practicar el consumo de **APIs** con la librería **Retrofit**_
 
<br/>

## Contenido
1. [Información general](#información-general)
2. [Construido con](#construido-con)
3. [Interfaz de usuario](#interfaz-de-usuario)
4. [Demo](#demo)

</br>

## Información general
La aplicación es un ejemplo de tipo **master-detail** que cuenta con dos pantallas.

La primera en la que se muestra una lista de las películas que se obtienen a consultar la API publica [TheMovieDB](https://developers.themoviedb.org/), al seleccionar uns película se abrirá la segunda pantalla en la que se vera la información de la película elegida.
Al momento de consultar los  datos desde la api de [TheMovieDB](https://developers.themoviedb.org/) la aplicacion los almacena en una base de datos local realizada con Room, lo que permite que funcione offline (no obstante, se requiere que la primera vez que se use se cuente con coneccion a internet).

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
La aplicacion se divide pricipalmente en tres capas

<br/>

### Organización de los directorios
```
  |--- core
  |--- data
  |     |--- local
  |     |     |--- dao
  |     |     |--- entities
  |     |     |--- database
  |     |     |
  |     |--- network
  |     |     |--- models
  |     |     |--- api
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

## Interfaz de usuario
Por sobre todo se buscó que la interfaz gráfica de la aplicación fuera sencilla y minimalista, además que cuenta con tema claro y oscuro.

### Tema claro
<p float="center">
  <img src="/art/pokemon_list_light.png" width="22%" hspace="1%" />
  <img src="/art/pokemon_about_light.png" width="22%" hspace="1%" /> 
  <img src="/art/empty_list_light.png" width="22%" hspace="1%" />
  <img src="/art/no_internet_light.png" width="22%" hspace="1%" />
</p>

### Tema oscuro
<p float="center">
  <img src="/art/pokemon_list_dark.png" width="22%" hspace="1%" />
  <img src="/art/pokemon_about_dark.png" width="22%" hspace="1%" /> 
  <img src="/art/empty_list_dark.png" width="22%" hspace="1%" />
  <img src="/art/no_internet_dark.png" width="22%" hspace="1%" />
</p>

<br/>

## Demo
<p float="center">
  <img src="/art/search.gif" width="22%" hspace="1%" />
  <img src="/art/filter.gif" width="22%" hspace="1%" /> 
  <img src="/art/details.gif" width="22%" hspace="1%" />
</p>
