package dev.yellowhatpro.branchin.data

data class Message(
    val agent_id: Any?,
    val body: String,
    val id: Int,
    val thread_id: Int,
    val timestamp: String,
    val user_id: String
)