package dev.yellowhatpro.branchin.data.repo

import dev.yellowhatpro.branchin.data.AuthResponse
import dev.yellowhatpro.branchin.data.CreateMessageBody
import dev.yellowhatpro.branchin.data.LoginBody
import dev.yellowhatpro.branchin.data.Message
import dev.yellowhatpro.branchin.data.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepository {

    override suspend fun login(loginBody: LoginBody): Response<AuthResponse> = apiService.login(loginBody)

    override suspend fun createMessage(
        authToken: String,
        createMessageBody: CreateMessageBody
    ): Response<Message> = apiService.createMessage(authToken, createMessageBody)

    override suspend fun getAllMessages(authToken: String): Response<List<Message>> =
        apiService.getAllMessages(authToken)
}