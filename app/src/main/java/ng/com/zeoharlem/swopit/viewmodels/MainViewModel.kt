package ng.com.zeoharlem.swopit.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ng.com.zeoharlem.swopit.datasources.repository.Repository
import ng.com.zeoharlem.swopit.models.PopularResponse
import ng.com.zeoharlem.swopit.util.NetworkResults
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application): AndroidViewModel(application) {

    var popularResponse: MutableLiveData<NetworkResults<PopularResponse>>   = MutableLiveData()

    fun getPopularMovies(queryMap: Map<String, String>) = viewModelScope.launch {
        getPopularMoviesCall(queryMap)
    }

    private suspend fun getPopularMoviesCall(queryMap: Map<String, String>) {
        if(hasIntenetConnection()){
            try {
                val response            = repository.remoteData.getPopularMovies(queryMap)
                popularResponse.value   = handlePopularMovieResponse(response)
            }
            catch (exception: Exception){
                Log.e("MainViewModel", "getPopularMoviesCall: "+exception.message)
                popularResponse.value   = NetworkResults.Error("Data Not Found")
            }
        }
        else{
            popularResponse.value   = NetworkResults.Error("No Intenet Connection")
        }
    }

    private fun handlePopularMovieResponse(response: Response<PopularResponse>): NetworkResults<PopularResponse>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResults.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResults.Error("Api Key Not COrrect")
            }
            response.body()!!.populars.isNullOrEmpty() -> {
                Log.e("MainViewModel", "getPopularMoviesCall: "+response.raw().toString())
                return NetworkResults.Error("No data found")
            }
            response.isSuccessful -> {
                val popularMovies   = response.body()
                return NetworkResults.Success(popularMovies!!)
            }
            else -> {
                return NetworkResults.Error(response.message())
            }
        }
    }

    private fun hasIntenetConnection(): Boolean {
        val connectivityManager: ConnectivityManager    = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork   = connectivityManager.activeNetwork ?: return false
        val capabilities    = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}