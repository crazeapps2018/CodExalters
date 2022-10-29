package com.mep.it.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mep.it.api.EmployeeService
import com.mep.it.model.EmployeeResponse
import com.mep.it.util.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class MainRepository @Inject constructor(private val employeeService: EmployeeService) {

    private val _empLiveData = MutableLiveData<NetworkResult<EmployeeResponse>>()
    val empLiveData: LiveData<NetworkResult<EmployeeResponse>>
        get() = _empLiveData

    suspend fun getEmployee() {
        _empLiveData.postValue(NetworkResult.Loading())
        val response = employeeService.getEmployeesData()
        if (response.isSuccessful && response.body() != null) {
            _empLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _empLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _empLiveData.postValue(NetworkResult.Error("Something went wrong"))

        }

    }

}