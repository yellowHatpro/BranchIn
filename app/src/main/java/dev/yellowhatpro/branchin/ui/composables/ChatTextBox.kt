package dev.yellowhatpro.branchin.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.yellowhatpro.branchin.ui.MainViewModel
import dev.yellowhatpro.branchin.ui.theme.Purple40
import dev.yellowhatpro.branchin.ui.theme.Purple80
import dev.yellowhatpro.branchin.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextBox(
                viewModel: MainViewModel,
                threadId: String) {
    val scope = rememberCoroutineScope()
    var input by rememberSaveable { mutableStateOf("") }
    Row(
        modifier = Modifier
            .background(Purple80)
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {

        TextField(
            modifier = Modifier
                .weight(1f) ,
            value = input,
            onValueChange = { newText -> input = newText.trimStart{it =='0'} },
            label = { Text("Message") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Purple80.copy(alpha = 0.5f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 5
        )
        IconButton(
            onClick = {
                scope.launch {
                    if (input.isNotBlank()) {
                        viewModel.createMessage(threadId, input.trim())
                        input = ""
                    }
                }
            }, modifier = Modifier
                .background(if (input.isNotBlank()) Purple40 else PurpleGrey80, shape = CircleShape)
                .padding(4.dp)
        ) {
            Icon(imageVector = Icons.Rounded.Send,
                contentDescription = "Send Message", tint = Color.White)
        }
    }
}