package fia3.alice.tp1_android

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmParMotCle(@Query("api_key") apiKey: String, @Query("query") motCle: String) : TmdbResult
}