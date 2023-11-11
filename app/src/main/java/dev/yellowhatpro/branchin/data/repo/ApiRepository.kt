package dev.yellowhatpro.branchin.data.repo

import dev.yellowhatpro.branchin.data.AuthResponse
import dev.yellowhatpro.branchin.data.CreateMessageBody
import dev.yellowhatpro.branchin.data.LoginBody
import dev.yellowhatpro.branchin.data.Message
import retrofit2.Response

interface ApiRepository {
    suspend fun login(loginBody: LoginBody) : Response<AuthResponse>
    suspend fun createMessage(authToken: String, createMessageBody: CreateMessageBody) : Response<Message>
    suspend fun getAllMessages(authToken: String) : Response<List<Message>>
}