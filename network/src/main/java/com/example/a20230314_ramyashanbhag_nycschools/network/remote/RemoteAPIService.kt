package com.example.a20230314_ramyashanbhag_nycschools.network.remote

import com.example.a20230314_ramyashanbhag_nycschools.network.utils.Constants
import com.example.a20230314_ramyashanbhag_nycschools.data.model.School
import com.example.a20230314_ramyashanbhag_nycschools.data.model.SchoolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteAPIService {
    @GET(Constants.SCHOOLS_LIST_ENDPOINT)
    suspend fun getSchoolsList(): Response<List<SchoolsResponse>>

    @GET(Constants.SCHOOL_DETAILS_ENDPOINT)
    suspend fun getSchoolsDetails(
        @Query("dbn") dbn: String
    ): Response<List<School>>
}
