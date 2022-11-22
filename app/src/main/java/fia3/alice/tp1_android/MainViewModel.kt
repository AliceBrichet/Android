package fia3.alice.tp1_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fia3.alice.tp1_android.model.Actor
import fia3.alice.tp1_android.model.MovieDetailled
import fia3.alice.tp1_android.model.SerieDetailled
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val movie = MutableStateFlow<MovieDetailled?>(null)
    val serie = MutableStateFlow<SerieDetailled?>(null)
    val query = MutableStateFlow("")

    val apikey = "790ef716933cdaa27cbf7166275f3d82"
    val language = "fr"
    val append_to_response = "credits"
    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    init{
        getFilmTrendingWeek()
        getSerieTrendingWeek()
        getActorTrendingWeek()
    }

    fun searchMovies(motCle: String) {
        viewModelScope.launch {
            movies.value = service.getFilmParMotCle(apikey, motCle, language).results
        }
    }

    fun searchSeries(motCle: String) {
        viewModelScope.launch {
            series.value = service.getSerieParMotCle(apikey, motCle, language).results
        }
    }

    fun searchActors(motCle: String) {
        viewModelScope.launch {
            actors.value = service.getActorParMotCle(apikey, motCle, language).results
        }
    }

    fun getFilmTrendingWeek() {
        viewModelScope.launch {
            movies.value = service.getFilmTrendingWeek(apikey, language).results
        }
    }

    fun getSerieTrendingWeek() {
        viewModelScope.launch {
            series.value = service.getSerieTrendingWeek(apikey, language).results
        }
    }

    fun getActorTrendingWeek() {
        viewModelScope.launch {
            actors.value = service.getActorTrendingWeek(apikey, language).results
        }
    }

    fun getMovieDetails(id : String) {
        viewModelScope.launch {
            movie.value = service.getDetailledMovie(id, apikey, append_to_response, language)
        }
    }

    fun getSerieDetails(id : String) {
        viewModelScope.launch {
            serie.value = service.getDetailledSerie(id, apikey, append_to_response, language)
        }
    }



    fun editQuery(newValue :String) {
        query.value = newValue
    }

}