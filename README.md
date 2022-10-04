# FilmJoy

FilmJoy is an Android App that uses [ The Movie Database (TMDB) API](https://www.themoviedb.org/about) as content source to populate a list of the most popular and best rated tv shows available and show their details.

## Functionality ##

The app has two fragment layouts:
* The first layout is the home where you can navigate a list of Tv shows and select one.
* The second layout populates the details of the Tv Show selected.
* Only supports portrait mode for Screen orientation.
* Only supports Light Mode.
* Only English Language Supported.

### Upcoming versions:

* Support for Dark Mode.
* Support for Offline Mode.
* Support for Horizontal and vertical Screen orientation.
* Support for Spanish Language.

## Architecture & Technologies used ##

* Design pattern based on MVVM + Clean Architecture.
* Use of [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency injection.
* Use of [Retrofit](https://square.github.io/retrofit/) to consume API.
* Use of [Room](https://developer.android.com/training/data-storage/room) to save data locally (Images pending).
* Use of [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) to navigate between layouts.

---

<a href="https://www.flaticon.com/free-icons/movie" title="movie icons">Movie icons created by IconKanan - Flaticon</a>