package com.mep.it.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mep.it.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel()
     {


    val empLiveData get() = mainRepository.empLiveData

     fun getEmployee() {
        viewModelScope.launch {
            mainRepository.getEmployee()
        }
    }




}