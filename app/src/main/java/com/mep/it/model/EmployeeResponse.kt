package com.mep.it.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class EmployeeResponse {

    @SerializedName("statusCode")
    @Expose
     var statusCode: Int? = null

    @SerializedName("message")
    @Expose
     var message: String? = null

    @SerializedName("data")
    @Expose
     var data: List<Datum>? = null

    inner class Datum : java.io.Serializable{
        @SerializedName("emp_Id")
        @Expose
         var empId: String? = null

        @SerializedName("name")
        @Expose
         var name: String? = null

        @SerializedName("technology")
        @Expose
         var technology: String? = null

        @SerializedName("address")
        @Expose
         var address: String? = null

        @SerializedName("mobile_no")
        @Expose
         var mobileNo: String? = null

        @SerializedName("lat")
        @Expose
         var lat: String? = null

        @SerializedName("lng")
        @Expose
         var lng: String? = null

        @SerializedName("image")
        @Expose
         var image: String? = null
    }
}