package com.tnt.beenalone.network.api

import com.tnt.beenalone.network.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("user/save")
    suspend fun save(@Body body: Map<String, String>): Response<UserResponse>

    @POST("user/update")
    suspend fun update(@Body body: Map<String, String>): Response<UserResponse>

    @GET("user/rank")
    suspend fun getRank(): Response<List<UserResponse>>
}