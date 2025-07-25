package com.rtamaaa.inventoritoko.api

import com.rtamaaa.inventoritoko.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/login.php")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("api/register.php")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @GET("api/product.php")
    suspend fun getProducts(): Response<ProductResponse>
}
