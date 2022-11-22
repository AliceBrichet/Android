package fia3.alice.tp1_android

import fia3.alice.tp1_android.model.MovieDetailled
import fia3.alice.tp1_android.model.SerieDetailled
import fia3.alice.tp1_android.model.TmdbActorResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmParMotCle(@Query("api_key") apiKey: String, @Query("query") motCle: String, @Query("language") language: String) : TmdbMovieResult

    @GET("search/tv")
    suspend fun getSerieParMotCle(@Query("api_key") apiKey: String, @Query("query") motCle: String, @Query("language") language: String) : TmdbSerieResult

    @GET("search/person")
    suspend fun getActorParMotCle(@Query("api_key") apiKey: String, @Query("query") motCle: String, @Query("language") language: String) : TmdbActorResult

    @GET("trending/movie/week")
    suspend fun getFilmTrendingWeek(@Query("api_key") apiKey: String, @Query("language") language: String) : TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun getSerieTrendingWeek(@Query("api_key") apiKey: String, @Query("language") language: String) : TmdbSerieResult

    @GET("trending/person/week")
    suspend fun getActorTrendingWeek(@Query("api_key") apiKey: String, @Query("language") language: String) : TmdbActorResult

    @GET("movie/{id}")
    suspend fun getDetailledMovie(@Path("id") id: String, @Query("api_key") apiKey: String, @Query("append_to_response") append_to_response: String, @Query("language") language: String) : MovieDetailled

    @GET("tv/{id}")
    suspend fun getDetailledSerie(@Path("id") id: String, @Query("api_key") apiKey: String, @Query("append_to_response") append_to_response: String, @Query("language") language: String) : SerieDetailled
}