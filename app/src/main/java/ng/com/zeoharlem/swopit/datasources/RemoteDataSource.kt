package ng.com.zeoharlem.swopit.datasources

import ng.com.zeoharlem.swopit.models.PopularResponse
import ng.com.zeoharlem.swopit.services.MovieApiServices
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val movieApiServices: MovieApiServices) {

    suspend fun getPopularMovies(queryMap: Map<String, String>): Response<PopularResponse>{
        return movieApiServices.getPopularMovies(queryMap)
    }
}