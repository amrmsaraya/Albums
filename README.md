# Albums

Sample application to show case modern android development architecture and design patterns
using [JSON Placeholder](https://jsonplaceholder.typicode.com) fake API.

> [!NOTE]
> It's better to test the application using the release build as it has a significant performance
> over the debug build especially in compose lazy lists.

## Features

- View random user
- List user's albums
- View album details and photos
- Previewing, zooming and sharing photos
- Light / Dark theme support

## Libraries

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI Framework
- [Material You](https://m3.material.io) - Design System
- [Splash Screen](https://developer.android.com/reference/android/window/SplashScreen) - Newly
  introduced splash screen API
- [Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) - Asynchronous Programming
- [Moshi](https://github.com/square/moshi) - JSON Library
- [Retrofit](https://github.com/square/retrofit) - HTTP Client
- [Hilt](http://google.github.io/hilt/) - Dependency Injection
- [Coil](https://github.com/coil-kt/coil) - Image Loading

## Architecture and Design Patterns

- [Clean Architecture](https://koenig-media.raywenderlich.com/uploads/2019/02/Clean-Architecture-Bob-650x454.png)
    - :app - Holds the Application class and MainActivity
    - :buildSrc - Central point for project configurations
    - :common - Provide common utilities
    - :network - Provide a configured ready to use http client
    - :data - Contains DTOs, data sources and repositories implementations
    - :domain - Business layer that contains repositories interfaces, entities and use cases
    - :presentation - Theming, navigation and screens ui implementation

- [MVI](https://miro.medium.com/max/5152/1*iFis87B9sIfpsgQeFkgu8Q.png) - Model-View-Intent