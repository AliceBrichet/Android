package fia3.alice.tp1_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val apikey = "790ef716933cdaa27cbf7166275f3d82"
    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovies(motCle: String) {
        viewModelScope.launch {
            service.getFilmParMotCle(apikey, motCle).results
        }
    }
}