package dev.yellowhatpro.branchin.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SP_NAME, ACCESS_MODE)

    private fun setString(key: String?, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    var accessToken: String?
        get() = sharedPreferences.getString(
            ACCESS_TOKEN, ""
        )
        set(accessToken) = setString(ACCESS_TOKEN, accessToken)


    companion object {
        private const val SP_NAME = "BRANCH_IN"
        private const val ACCESS_MODE = Context.MODE_PRIVATE
        const val ACCESS_TOKEN = "ACCESS_TOKEN"

        lateinit var instance: SharedPrefManager
        fun createInstance(context: Context): SharedPrefManager {
            if (!::instance.isInitialized){
                instance = SharedPrefManager(context)
            }
            return instance
        }
    }
}