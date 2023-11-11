package dev.yellowhatpro.branchin.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yellowhatpro.branchin.ui.MainViewModel
import dev.yellowhatpro.branchin.ui.composables.MessageItemBox
import dev.yellowhatpro.branchin.ui.theme.Purple80
import dev.yellowhatpro.branchin.utils.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThreadsPage(viewModel: MainViewModel,
                handleNavigateToThread: (threadId: String?, userId: String?) -> Unit ) {
    val allMessages by viewModel.allMessages.collectAsState()
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "BranchIn Threads")
            },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(horizontal =  4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            LaunchedEffect(Unit) {
                scope.launch {
                    viewModel.getAllMessages()
                }
            }
            when (allMessages.status) {
                Resource.Status.SUCCESS -> {

                    LazyColumn {
                        items(allMessages.data?.sortedByDescending { message ->
                            message.timestamp
                        }?.distinctBy { message ->
                            message.thread_id
                        } ?: listOf()) { message ->
                            MessageItemBox(
                                modifier = Modifier.clickable {
                                    handleNavigateToThread(message.thread_id.toString(), message.user_id)
                                },
                                message
                            )
                        }
                    }
                }

                Resource.Status.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(40.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Resource.Status.FAILED -> {
                    NoInternetScreen(viewModel = viewModel)
                }
            }
        }
    }
}