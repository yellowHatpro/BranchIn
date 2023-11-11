package dev.yellowhatpro.branchin.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yellowhatpro.branchin.data.Message
import dev.yellowhatpro.branchin.ui.theme.Pink80
import dev.yellowhatpro.branchin.ui.theme.Purple80
import dev.yellowhatpro.branchin.utils.Utils.formatDateTime

@Composable
fun ChatMessageBubble(isFromMe: Boolean, message: Message) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .align(if (isFromMe) Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (isFromMe) 48f else 0f,
                        bottomEnd = if (isFromMe) 0f else 48f
                    )
                )
                .background(if (isFromMe) Pink80 else Purple80)
                .padding(16.dp)
        ) {
            Column {
                Text(text = message.body)
                Text(text = formatDateTime(message.timestamp), fontSize = 12.sp)
            }
        }
    }
}