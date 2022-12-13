package com.gerry.justotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerry.justotest.api.ApiResponseStatus
import com.gerry.justotest.model.Person
import com.gerry.justotest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _personList = MutableLiveData<List<Person>>()
    val personList: LiveData<List<Person>> get() = _personList
    private val _status = MutableLiveData<ApiResponseStatus<List<Person>>>()
    val status: LiveData<ApiResponseStatus<List<Person>>> get() = _status

    init {
        dowloadPerson()
    }

    private fun dowloadPerson() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(repository.getPerson())
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Person>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _personList.value = apiResponseStatus.data!!
        }
        _status.value = apiResponseStatus
    }
}