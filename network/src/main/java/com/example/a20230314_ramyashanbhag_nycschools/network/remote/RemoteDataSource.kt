package com.example.a20230314_ramyashanbhag_nycschools.network.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val remoteAPIService: RemoteAPIService
) {
    suspend fun getSchoolsList() = remoteAPIService.getSchoolsList()
    suspend fun getSchoolsDetails(dbn: String) = remoteAPIService.getSchoolsDetails(dbn)
}