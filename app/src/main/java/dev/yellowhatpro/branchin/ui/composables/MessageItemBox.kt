package dev.yellowhatpro.branchin.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yellowhatpro.branchin.data.Message
import dev.yellowhatpro.branchin.ui.theme.Purple80
import dev.yellowhatpro.branchin.utils.Utils.formatDateTime

@Composable
fun MessageItemBox(
    modifier : Modifier = Modifier,
    message: Message) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(2.dp, Color.DarkGray, shape = RoundedCornerShape(10.dp))
            .background(Purple80, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        message.apply {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "User ID: $user_id", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "Thread ID: $thread_id", fontSize = 14.sp)
            }
            Text(text = body)
            Text(
                text = formatDateTime(timestamp),
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}