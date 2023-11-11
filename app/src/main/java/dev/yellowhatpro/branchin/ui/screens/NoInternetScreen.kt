package dev.yellowhatpro.branchin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yellowhatpro.branchin.ui.MainViewModel
import kotlinx.coroutines.launch


@Composable
fun NoInternetScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledTonalIconButton(onClick = {
            scope.launch {
                viewModel.getAllMessages()
            }
        }) {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "no internet, please refresh your internet connection",
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = "No internet, please retry."
        )
    }
}
