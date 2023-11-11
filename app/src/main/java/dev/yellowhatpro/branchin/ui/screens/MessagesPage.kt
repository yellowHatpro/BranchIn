package dev.yellowhatpro.branchin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.yellowhatpro.branchin.ui.MainViewModel
import dev.yellowhatpro.branchin.ui.composables.ChatMessageBubble
import dev.yellowhatpro.branchin.ui.composables.ChatTextBox
import dev.yellowhatpro.branchin.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesPage(
    viewModel: MainViewModel,
    navController: NavHostController,
    threadId: String,
    userId: String
) {
    val currentThreadMessages by viewModel.allMessages.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80),
                title = {
                    Text(text = "User ID: $userId")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(0.9f)
            ) {
                items(
                    currentThreadMessages.data?.let { messages ->
                        messages.filter { message ->
                            message.thread_id.toString() == threadId
                        }
                            .sortedBy { message ->
                                message.timestamp
                            }
                    } ?: listOf()
                ) { message ->
                    ChatMessageBubble(isFromMe = message.agent_id != null,
                        message = message)
                }
            }
            ChatTextBox(
                viewModel,
                threadId
            )
        }
    }
}

