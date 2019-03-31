package keldmar.example.com.inject

import android.app.Application
import keldmar.example.com.inject.module.ActivityModule
import keldmar.example.com.inject.module.AppModule
import keldmar.example.com.inject.module.FragmentModule
import keldmar.example.com.presentation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import keldmar.example.com.inject.module.NavigationModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ActivityModule::class,
        AppModule::class,
        NavigationModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}
