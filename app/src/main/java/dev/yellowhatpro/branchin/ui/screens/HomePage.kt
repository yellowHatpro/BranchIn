package dev.yellowhatpro.branchin.ui.screens

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import dev.yellowhatpro.branchin.ui.MainViewModel


@Composable
fun HomePage(viewModel: MainViewModel, navController: NavHostController) {

    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()

    Surface {
        if (isUserLoggedIn) {
            ThreadsPage(viewModel = viewModel) { threadId, userId ->
                navController.navigate("messages/$threadId/$userId")
            }
        } else LoginScreen(viewModel = viewModel)
    }
}