package keldmar.example.com.inject.module

import keldmar.example.com.presentation.ui.activity.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import keldmar.example.com.presentation.ui.activity.second.SecondActivity

@Module
abstract class ActivityModule{

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity


    @ContributesAndroidInjector
    abstract fun provideSecondActivity(): SecondActivity

}