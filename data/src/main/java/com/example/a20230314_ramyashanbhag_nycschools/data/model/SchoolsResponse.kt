package com.example.a20230314_ramyashanbhag_nycschools.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SchoolsResponse(
    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("school_name")
    val schoolName: String? = null
) : Parcelable
