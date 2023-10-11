package com.example.a20230314_ramyashanbhag_nycschools.network.remote


import com.example.a20230314_ramyashanbhag_nycschools.network.utils.NetworkResult
import com.example.a20230314_ramyashanbhag_nycschools.data.model.School
import com.example.a20230314_ramyashanbhag_nycschools.data.model.SchoolsResponse
import com.example.a20230314_ramyashanbhag_nycschools.network.model.BaseApiResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getSchoolsList(): Flow<NetworkResult<List<SchoolsResponse>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getSchoolsList() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSchoolsDetails(dbn: String): Flow<NetworkResult<List<School>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getSchoolsDetails(dbn) })
        }.flowOn(Dispatchers.IO)
    }
}