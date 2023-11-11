package dev.yellowhatpro.branchin.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yellowhatpro.branchin.data.CreateMessageBody
import dev.yellowhatpro.branchin.data.LoginBody
import dev.yellowhatpro.branchin.data.Message
import dev.yellowhatpro.branchin.data.repo.ApiRepository
import dev.yellowhatpro.branchin.utils.Utils.INTERNET_ERROR
import dev.yellowhatpro.branchin.utils.Utils.LOGIN_ERROR
import dev.yellowhatpro.branchin.utils.Resource
import dev.yellowhatpro.branchin.utils.SharedPrefManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val sharedPrefManager = SharedPrefManager.instance

    private val _errorState = MutableStateFlow<Resource<String>>(Resource.loading())
    val errorState: StateFlow<Resource<String>> = _errorState

    private val _allMessages = MutableStateFlow<Resource<List<Message>>>(Resource.loading())
    val allMessages: StateFlow<Resource<List<Message>>> = _allMessages

    private val _isUserLoggedIn = MutableStateFlow(!sharedPrefManager.accessToken.isNullOrBlank())
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    suspend fun login(username: String, password: String) {
        _errorState.value = Resource.loading()
        try {
            val loginBody = LoginBody(username, password)
            val response = repository.login(loginBody)
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let { res ->
                    sharedPrefManager.accessToken = res.auth_token
                    _errorState.value = Resource.success(res.auth_token, response.message())
                    _isUserLoggedIn.value = true
                }
            } else {
                _errorState.value = Resource.failure(LOGIN_ERROR)
            }
        } catch (e: Exception) {
            _errorState.value = Resource.failure(INTERNET_ERROR)
        }
    }

    suspend fun getAllMessages() {
        _errorState.value = Resource.loading()
        try {
            if (sharedPrefManager.accessToken.isNullOrEmpty())
                throw Exception("Authentication Error. Please try to login again.")

            sharedPrefManager.accessToken?.let { token ->
                val response = repository.getAllMessages(token)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let { res ->
                        _allMessages.value = Resource.success(res, response.message())
                    }
                } else _allMessages.value = Resource.failure(response.message())
            }
        } catch (e: Exception) {
            _allMessages.value = Resource.failure(e.message.toString())
        }
    }

    suspend fun createMessage(threadId: String, messageBody: String) {
        _errorState.value = Resource.loading()
        try {
            val createMessageBody = CreateMessageBody(threadId, messageBody)
            sharedPrefManager.accessToken?.let { token ->
                val response = repository.createMessage(token, createMessageBody)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let { res ->
                        val allMessages = _allMessages.value.data ?: listOf()
                        val newMessages = allMessages.plus(res)
                        _allMessages.value = Resource.success(newMessages, response.message())
                        _errorState.value = Resource.success("No error", "Success")
                    }
                } else {
                    _errorState.value = Resource.failure(response.message())
                }
            }
        } catch (e: Exception) {
            _errorState.value = Resource.failure(e.message.toString())
        }
    }
}