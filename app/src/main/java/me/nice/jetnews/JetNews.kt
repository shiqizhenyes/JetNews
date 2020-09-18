package me.nice.jetnews

import android.app.Application
import android.os.Parcelable
import me.nice.jetnews.data.AppContainer
import me.nice.jetnews.data.AppContainerImpl

class JetNews: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }

}