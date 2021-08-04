package ng.com.zeoharlem.swopit.services

import ng.com.zeoharlem.swopit.models.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApiServices {

    @GET("/movie/popular")
    suspend fun getPopularMovies(@QueryMap queryMap: Map<String, String>): Response<PopularResponse>
}