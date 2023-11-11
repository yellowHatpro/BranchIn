package dev.yellowhatpro.branchin.data.api

import dev.yellowhatpro.branchin.data.AuthResponse
import dev.yellowhatpro.branchin.data.CreateMessageBody
import dev.yellowhatpro.branchin.data.LoginBody
import dev.yellowhatpro.branchin.data.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api/login")
    suspend fun login(@Body loginBody: LoginBody): Response<AuthResponse>

    @POST("api/messages")
    suspend fun createMessage(
        @Header("X-Branch-Auth-Token") authToken: String,
        @Body createMessageBody: CreateMessageBody
    ): Response<Message>

    @GET("api/messages")
    suspend fun getAllMessages(@Header("X-Branch-Auth-Token") authToken: String): Response<List<Message>>

    @POST("api/reset")
    suspend fun reset(@Header("X-Branch-Auth-Token") authToken: String)
}