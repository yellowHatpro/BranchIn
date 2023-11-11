package dev.yellowhatpro.branchin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.yellowhatpro.branchin.utils.SharedPrefManager

@HiltAndroidApp
class BranchInApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.createInstance(this)
    }
}