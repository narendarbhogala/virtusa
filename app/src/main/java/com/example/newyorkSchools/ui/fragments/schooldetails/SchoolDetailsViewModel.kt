package com.example.newyorkSchools.ui.fragments.schooldetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.a20230314_ramyashanbhag_nycschools.data.model.School
import com.example.a20230314_ramyashanbhag_nycschools.network.remote.Repository
import com.example.newyorkSchools.ui.fragments.BaseViewModel
import com.example.newyorkSchools.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application) {
    private val _schoolsDetailsList: SingleLiveEvent<List<School>?> =
        SingleLiveEvent()
    val schoolsDetailsList: LiveData<List<School>?> = _schoolsDetailsList

    fun fetchSchoolDetails(dbn: String) = viewModelScope.launch {
        showLoading()
        repository.getSchoolsDetails(dbn).collect {
            hideLoading()
            _schoolsDetailsList.value = it.data
        }
    }
}