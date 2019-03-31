package keldmar.example.com.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import keldmar.example.com.inject.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}