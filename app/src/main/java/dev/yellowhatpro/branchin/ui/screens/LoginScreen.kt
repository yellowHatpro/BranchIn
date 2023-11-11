package dev.yellowhatpro.branchin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.yellowhatpro.branchin.ui.MainViewModel
import dev.yellowhatpro.branchin.ui.theme.Purple40
import dev.yellowhatpro.branchin.utils.Utils.INTERNET_ERROR
import dev.yellowhatpro.branchin.utils.Utils.LOGIN_ERROR
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: MainViewModel) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    val errorState by viewModel.errorState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "BranchIn",
            color = Purple40,
            fontSize = 40.sp,
            fontWeight = FontWeight.Black)
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Please check username details again",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (isError)
                    Icon(Icons.Rounded.Warning,"error",
                        tint = MaterialTheme.colorScheme.error)
            },
            )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Please check password again",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            trailingIcon = {
                if (isError)
                    Icon(Icons.Rounded.Warning,"error",
                        tint = MaterialTheme.colorScheme.error)
            },
        )
        Button(onClick = {
            scope.launch {
                if (username.isNotBlank() and password.isNotBlank()){
                    isError = false
                    viewModel.login(username, password)
                } else isError = true
            }
        }) {
            Text(text = "Login")
        }
        when(errorState.response){
            LOGIN_ERROR -> Text(text = "Login Failure, Please try again")
            INTERNET_ERROR -> Text(text = "Please check your internet")
            else -> Unit
        }
    }
}