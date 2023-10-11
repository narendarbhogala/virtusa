package com.example.newyorkSchools.ui.fragments.schoolslist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.a20230314_ramyashanbhag_nycschools.data.model.SchoolsResponse
import com.example.a20230314_ramyashanbhag_nycschools.network.remote.Repository
import com.example.newyorkSchools.ui.fragments.BaseViewModel
import com.example.newyorkSchools.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsListViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application) {

    private val _schoolsList: SingleLiveEvent<List<SchoolsResponse>?> =
        SingleLiveEvent()
    val schoolsList: LiveData<List<SchoolsResponse>?> = _schoolsList

    fun fetchSchoolsList() = viewModelScope.launch {
        showLoading()
        repository.getSchoolsList().collect {
            hideLoading()
            _schoolsList.value = it.data
        }
    }

}