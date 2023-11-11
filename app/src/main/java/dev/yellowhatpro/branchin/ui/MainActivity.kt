package dev.yellowhatpro.branchin.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.yellowhatpro.branchin.ui.screens.HomePage
import dev.yellowhatpro.branchin.ui.screens.MessagesPage
import dev.yellowhatpro.branchin.ui.theme.BranchInTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val navController = rememberNavController()

            BranchInTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomePage(viewModel, navController)
                        }
                        composable(
                            "messages/{threadId}/{userId}",
                            arguments = listOf(navArgument("threadId") {
                                type = NavType.StringType
                            },
                                navArgument("userId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val userIdArg = backStackEntry.arguments?.getString("userId")
                            val threadIdArg = backStackEntry.arguments?.getString("threadId")
                            threadIdArg?.let { threadId->
                                userIdArg?.let { userId ->
                                    MessagesPage(viewModel = viewModel,
                                        navController = navController,
                                        threadId = threadId,
                                        userId = userId)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}