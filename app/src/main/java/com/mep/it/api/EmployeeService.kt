package com.mep.it.api

import com.mep.it.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeService {

    @GET("getEmployeeList.php")
     suspend fun getEmployeesData() : Response<EmployeeResponse>

}