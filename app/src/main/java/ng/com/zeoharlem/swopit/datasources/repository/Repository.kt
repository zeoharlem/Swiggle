package ng.com.zeoharlem.swopit.datasources.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ng.com.zeoharlem.swopit.datasources.RemoteDataSource
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource: RemoteDataSource) {
    val remoteData  = remoteDataSource
}