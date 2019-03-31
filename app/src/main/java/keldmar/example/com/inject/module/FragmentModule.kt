package keldmar.example.com.inject.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import keldmar.example.com.presentation.ui.fragment.first.FirstFragment
import keldmar.example.com.presentation.ui.fragment.second.SecondFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun provideFirstFragment(): FirstFragment

    @ContributesAndroidInjector
    abstract fun provideSecondFragment(): SecondFragment

}