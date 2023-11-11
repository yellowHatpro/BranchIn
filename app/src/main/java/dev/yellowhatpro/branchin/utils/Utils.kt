package dev.yellowhatpro.branchin.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    //Constants
    const val API_BASE_URL = "https://android-messaging.branch.co/"
    const val LOGIN_ERROR = "LOGIN_ERROR"
    const val INTERNET_ERROR = "INTERNET_ERROR"

    //Helper Functions
    fun formatDateTime(dateTime: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val date: Date? = inputFormat.parse(dateTime)
        val outputFormat = SimpleDateFormat("h:mm a dd-MM-yyyy", Locale.ENGLISH)
        return date?.let {
            outputFormat.format(it)
        } ?: "Unknown Time"
    }
}